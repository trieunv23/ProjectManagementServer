package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.cache.Clients;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.entity.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

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
                    try {
                        DataInputStream dis = new DataInputStream(client_socket.getInputStream());
                        DataOutputStream dos = new DataOutputStream(client_socket.getOutputStream());
                        StreamObject so = new StreamObject(client_socket, dis, dos);
                        DataService ds = new DataService() ;
                        StreamFunction sf = new StreamFunction();
                        boolean should_exit = false;
                        while (!should_exit) {
                            String request = ds.receive(dis, TypeData.STRING) ;
                            switch (request) {
                                case "@regester":
                                    sf.regester(so) ;
                                    break ;
                                case "@login":
                                    client_data = sf.login(so, this.clients);
                                    break;
                                case "@connected_successfully":
                                    clients.put(client_data.getId(), dos);
                                    clients.add(client_data.getId());
                                    break;
                                case "@create_object":
                                    sf.createProject(so) ;
                                    break ;
                                case "@projects" :
                                    sf.getProjects(so);
                                    break ;
                                case "@tasks" :
                                    // sf.getTasks(so);
                                    break ;
                                case "@task" :
                                    sf.getTask(so);
                                    break ;
                                case "@product" :
                                    sf.getProductPreview(so);
                                    break ;
                                case "@upload_product" :
                                    sf.updateProduct(so);
                                    break ;
                                case "@new_task" :
                                    sf.newTask(so);
                                    break ;
                                case "@new_project" :
                                    sf.newProject(so);
                                    break ;
                                case "@members" :
                                    // sf.getMembers(so);
                                    break ;
                                case "@contacts" :
                                    sf.getContacts(so);
                                    break ;
                                case "@search_client" :
                                    sf.searchClient(so);
                                    break ;
                                case "@interactors" :
                                    sf.getInteractors(so);
                                    break ;
                                case "@send_message" :
                                    sf.sendMessage(so, this.clients);
                                    break ;
                                case "@project_control" :
                                    sf.getProjectControl(so);
                                    break ;
                                case "@messages" :
                                    sf.getMessages(so);
                                    break ;
                                case "@interactor_just_sendMs":
                                    sf.getInforClient(so) ;
                                    break ;
                                case "@add_members":
                                    sf.newMember(so);
                                    break ;
                                case "@reset_task":
                                    sf.resetTask(so);
                                    break ;
                                case "@reset_project":
                                    sf.resetProject(so);
                                    break ;
                                case "@requests":
                                    sf.getRequests(so);
                                    break;
                                case "@feedbacks":
                                    sf.getFeedBacks(so);
                                    break;
                                case "@accept":
                                    ContactObject contact = new ContactObject(client_data.getId(), client_data.getFullname(), client_data.getEmail(), client_data.getPhonenumber(), client_data.getAvata(), client_data.getDay_of_birth(), client_data.getGender()) ;
                                    sf.accept(so, this.clients, contact);
                                    break;
                                case "@request_add_contact":
                                    sf.sendRequest(so, this.clients);
                                    break ;
                                case "@send_feedbacks":
                                    sf.newFeedBack(so, this.clients);
                                    break ;
                                case "@change_bsinf":
                                    sf.changeInformation(so);
                                    break ;
                                case "@forgot_password":
                                    sf.forgotPassword(so);
                                    break ;
                                case "@file":
                                    sf.dowloadFile(so);
                                    break;
                                case "@product_object":
                                    sf.downloadProduct(so) ;
                                    break ;
                                case "@disconnect":
                                    boolean result = sf.disconnect(so) ;
                                    if (result) {
                                        clients.removeClientInfor(client_data.getId());
                                        clients.removeClientStream(client_data.getId());
                                        System.out.println("Client " + client_data.getFullname() + " has disconnected.");
                                        sf.closeStreams(so);
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
