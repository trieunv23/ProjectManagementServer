package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.cache.Clients;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.entity.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server {
    private Map<String, DataOutputStream> clients = new HashMap<>();
    List<ClientData> clients_data = new CopyOnWriteArrayList<>() ;

    Clients c = new Clients() ;
    public void start(int port) throws IOException {
        try (ServerSocket server_socket = new ServerSocket(port)) {
            System.out.println("Server started in port " +  port + ".");
            Clients clients = new Clients();
            ClientDao.setMaxAllowedPacket();
            while (true) {
                Socket client_socket = server_socket.accept();
                new Thread(() -> {
                    ClientData client_data = null;
                    String code_delete_project = "@!.." ;
                    try {
                        DataInputStream dis = new DataInputStream(client_socket.getInputStream());
                        DataOutputStream dos = new DataOutputStream(client_socket.getOutputStream());
                        StreamObject so = new StreamObject(client_socket, dis, dos);
                        DataService ds = new DataService() ;
                        ClientStream cs = new ClientStream();
                        ProjectStream ps = new ProjectStream() ;
                        GuestStatus gs = new GuestStatus() ;
                        boolean should_exit = false;
                        while (!should_exit) {
                            String request = ds.receive(dis, TypeData.STRING) ;
                            switch (request) {
                                // Guest Interaction
                                case "@regester":
                                    gs.regester(so) ;
                                    break ;
                                case "@login":
                                    client_data = gs.login(so, this.clients);
                                    break;
                                case "@connected_successfully":
                                    clients.put(client_data.getId(), dos);
                                    clients.add(client_data.getId());
                                    break;
                                // Account Interaction
                                case "@contacts" :
                                    cs.getContacts(so);
                                    break ;
                                case "@search_client" :
                                    cs.searchClient(so);
                                    break ;
                                case "@interactors" :
                                    cs.getInteractors(so);
                                    break ;
                                case "@send_message" :
                                    cs.sendMessage(so, this.clients);
                                    break ;
                                case "@messages" :
                                    cs.getMessages(so);
                                    break ;
                                case "@interactor_just_sendMs":
                                    cs.getInforClient(so) ;
                                    break ;
                                case "@requests":
                                    cs.getRequests(so);
                                    break;
                                case "@feedbacks":
                                    cs.getFeedBacks(so);
                                    break;
                                case "@accept":
                                    ContactObject contact = new ContactObject(client_data.getId(), client_data.getFullname(), client_data.getEmail(), client_data.getPhonenumber(), client_data.getAvata(), client_data.getDay_of_birth(), client_data.getGender()) ;
                                    cs.accept(so, this.clients, contact);
                                    break;
                                case "@file":
                                    cs.dowloadFile(so);
                                    break;
                                case "@change_bsinf":
                                    cs.changeInformation(so);
                                    break ;
                                // Project Interaction
                                case "@create_object":
                                    ps.createProject(so) ;
                                    break ;
                                case "@projects" :
                                    ps.getProjects(so);
                                    break ;
                                case "@tasks" :
                                    // sf.getTasks(so);
                                    break ;
                                case "@task" :
                                    ps.getTask(so);
                                    break ;
                                case "@product" :
                                    ps.getProductPreview(so);
                                    break ;
                                case "@upload_product" :
                                    ps.updateProduct(so);
                                    break ;
                                case "@new_task" :
                                    ps.newTask(so);
                                    break ;
                                case "@new_project" :
                                    ps.newProject(so);
                                    break ;
                                case "@members" :
                                    // sf.getMembers(so);
                                    break ;
                                case "@project_control" :
                                    ps.getProjectControl(so);
                                    break ;
                                case "@add_members":
                                    ps.newMember(so);
                                    break ;
                                case "@reset_task":
                                    ps.resetTask(so);
                                    break ;
                                case "@reset_project":
                                    ps.resetProject(so);
                                    break ;
                                case "@request_add_contact":
                                    ps.sendRequest(so, this.clients);
                                    break ;
                                case "@send_feedbacks":
                                    ps.newFeedBack(so, this.clients);
                                    break ;
                                case "@forgot_password":
                                    gs.forgotPassword(so);
                                    break ;
                                case "@product_object":
                                    ps.downloadProduct(so) ;
                                    break ;
                                case "@delete_project_first" :
                                    String code = ps.deleteProjectFirst(so, client_data) ;
                                    code_delete_project = code ;
                                    break ;
                                case "@delete_project_last" :
                                    ps.deleteProjectLast(so, code_delete_project, client_data) ;
                                    break ;
                                case "@disconnect":
                                    boolean result = cs.disconnect(so) ;
                                    if (result) {
                                        clients.removeClientInfor(client_data.getId());
                                        clients.removeClientStream(client_data.getId());
                                        System.out.println("Client " + client_data.getFullname() + " has disconnected.");
                                        cs.closeStreams(so);
                                        should_exit = true ;
                                    }
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (BindException be) {
            System.out.println("Port " + port + " is already in use");
            throw new RuntimeException(be);
        } catch (IOException e) {
            System.out.println("Error");
            throw new RuntimeException(e);
        }
    }

    public Map<String, DataOutputStream> getMapClients() {
        return clients ;
    }

    public List<ClientData> getListClient () {
        return clients_data ;
    }

    public void addClientMap (String id, DataOutputStream dos) {
        clients.put(id, dos) ;
    }

    public void addListClient (ClientData client) {
        clients_data.add(client) ;
    }

    public void removeMapClient(String id) {
        removeMapClient(id);
    }

    public void removeClientOnList(String id) {
        for (ClientData client : clients_data) {
            if (client.getId().equals(id)) {
                clients_data.remove(client) ;
                return;
            }
        }
    }

}
