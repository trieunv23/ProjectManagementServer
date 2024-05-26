package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.dao.AccountClientDao;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.dao.MessageDao;
import com.gui.projectmanagementserver.dao.ProjectDao;
import com.gui.projectmanagementserver.entity.*;
import com.gui.projectmanagementserver.functions.SendEmail;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StreamFunction {
    DataService ds = new DataService();
    ProjectDao ps = new ProjectDao() ;
    ClientDao cd = new ClientDao() ;
    MessageDao md  = new MessageDao() ;

    AccountClientDao acd = new AccountClientDao() ;
    public ClientData login(StreamObject so, Map<String, DataOutputStream> client_streams) {
        ClientData client_data = null ;
        LoginObject login_object = ds.receive(so.getDis(), TypeData.LOGIN_OBJECT) ;
        client_data = acd.login(login_object) ;
        if (client_data == null) {
            ds.send(so.getDos(), "@refuse");
        } /* else if (client_streams.containsKey(client_data.getId())) {
            ds.send(so.getDos(), "@refuse");
        } */
         else if (client_data != null){
            ds.send(so.getDos(), "@accept");
            ds.send(so.getDos(), client_data);
            System.out.println("Client " + client_data.getFullname() + " connected!");
            client_streams.put(client_data.getId(), so.getDos()) ;
        }
        return client_data;
    }

    public void regester(StreamObject so) {
        RegesterObject ro = ds.receive(so.getDis(), TypeData.REGESTER_OBJECT) ;
        SendEmail se = new SendEmail() ;
        String code_send = se.sendCodeToEmail(ro.getEmail()) ;
        String code_form_client = ds.receive(so.getDis(), TypeData.STRING) ;
        if (code_send.equals(code_form_client)) {
            boolean result = acd.regester(ro) ;
            if (result) {
                ds.send(so.getDos(), "@rgst_success") ;
            } else {
                ds.send(so.getDos(), "@rgst_unsuccessful") ;
            }
        } else {
            ds.send(so.getDos(), "@code_is_wrong") ;
        }
    }

    public boolean createProject(StreamObject so) {
        CreateProjectObject cpo = ds.receive(so.getDis(), TypeData.CREATE_PROJECT) ;
        ProjectObject po = ps.createProject(cpo) ;
        if (po == null) {
            return false ;
        }
        ds.send(so.getDos(), po);
        return true ;
    }

    public void getProjects(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<ProjectPreview> projects = ps.getListProject(client_id) ;
        ds.send(so.getDos(), projects);
    }

    public void getTasks(StreamObject so) {
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<TaskPreview> tasks = ps.getListTaskPreview(project_id) ;
        ds.send(so.getDos(), tasks);
    }

    public void getTask(StreamObject so) {
        String task_id = ds.receive(so.getDis(), TypeData.STRING) ;
        TaskObject task = ps.getTask(task_id) ;
        ProductPreview pp = ps.getProduct(task.getProduct_id()) ;
        ds.send(so.getDos(), "@task");
        ds.send(so.getDos(), task);
        ds.send(so.getDos(), pp);
    }

    public void resetTask(StreamObject so) {
        String task_id = ds.receive(so.getDis(), TypeData.STRING) ;
        TaskObject task = ps.getTask(task_id) ;
        ProductPreview pp = ps.getProduct(task.getProduct_id()) ;
        ds.send(so.getDos(), "@reset_task");
        ds.send(so.getDos(), task);
        ds.send(so.getDos(), pp);
    }

    public void getMembers(StreamObject so) {
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<ClientObject> members = ps.getMembers(project_id) ;
        ds.send(so.getDos(), members);
    }

    public void getInforClient(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        ContactObject client = cd.getClientById(client_id) ;
        ds.send(so.getDos(), "@interactor_just_sendMs");
        ds.send(so.getDos(), client) ;
    }

    public void getProductPreview(StreamObject so) {
        String product_id = ds.receive(so.getDis(), TypeData.STRING) ;
        ProductPreview pp = ps.getProduct(product_id) ;
        ds.send(so.getDos(), "@product");
        ds.send(so.getDos(), pp);
    }

    public void updateProduct(StreamObject so) {
        CreateProductObject cpo = ds.receive(so.getDis(), TypeData.UPLOAD_PRODUCT) ;
        ProductObject po = ps.createProduct(cpo) ;
        ds.send(so.getDos(), "@product_just_create");
        ds.send(so.getDos(), po);
    }

    public void newTask(StreamObject so) {
        CreateTaskProject ctp = ds.receive(so.getDis(), TypeData.CREATE_TASK) ;
        TaskObject task_object = ps.createTask(ctp) ;
        ds.send(so.getDos(), "@task_just_create");
        ds.send(so.getDos(), task_object);
    }

    public void newProject(StreamObject so) {
        CreateProjectObject cpo = ds.receive(so.getDis(), TypeData.CREATE_PROJECT) ;
        ProjectObject pp = ps.createProject(cpo) ;
        ds.send(so.getDos(), "@project_just_create");
        ds.send(so.getDos(), pp);
    }

    public void getContacts(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<ContactObject> contacts = cd.getContacts(client_id) ;
        ds.send(so.getDos(), "@contacts");
        ds.send(so.getDos(), contacts);
    }

    public void searchClient(StreamObject so) {
        String client_email = ds.receive(so.getDis(), TypeData.STRING) ;
        ContactObject co = cd.getClientByEmail(client_email) ;
        ds.send(so.getDos(), "@result_client");
        ds.send(so.getDos(), co);
    }

    public void getInteractors(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<ContactObject> interactors = cd.getListInteractor(client_id) ;
        ds.send(so.getDos(), interactors);
    }

    public void getClient(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        ContactObject client = cd.getClientById(client_id) ;
        ds.send(so.getDos(), client);
    }

    public void sendMessage(StreamObject so, Map<String, DataOutputStream> clients) {
        MessageSend ms = ds.receive(so.getDis(), TypeData.MESSAGE_SEND) ;
        Message message = md.sendMessage(ms) ;
        // Send to sender
        ds.send(so.getDos(), "@message_return");
        ds.send(so.getDos(), message) ;
        // Send to receiver
        if (clients.get(ms.getReceiver_id()) != null) {
            ds.send(clients.get(ms.getReceiver_id()), "@new_message");
            ds.send(clients.get(ms.getReceiver_id()), message);
        }
    }

    public void getMessages(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        String interactor_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<Message> messages = md.getMessages(client_id, interactor_id) ;
        ds.send(so.getDos(), "@messages");
        ds.send(so.getDos(), messages);
    }

    public void getProjectControl(StreamObject so) {
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<TaskPreview> tasks = ps.getListTaskPreview(project_id) ;
        List<ClientObject> members = ps.getMembers(project_id) ;
        ProjectControl pp = new ProjectControl(null, tasks, members) ;
        ds.send(so.getDos(), "@project_control");
        ds.send(so.getDos(), project_id);
        ds.send(so.getDos(), pp);
    }

    public void newMember(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        String client_id_new = ps.addMembers(client_id, project_id) ;
        ds.send(so.getDos(), "@new_member");
        ds.send(so.getDos(), client_id_new);
    }

    public void resetProject(StreamObject so) {
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<TaskPreview> tasks = ps.getListTaskPreview(project_id) ;
        List<ClientObject> members = ps.getMembers(project_id) ;
        ProjectControl pp = new ProjectControl(null, tasks, members) ;
        ds.send(so.getDos(), "@reset_project");
        ds.send(so.getDos(), project_id);
        ds.send(so.getDos(), pp);
    }

    public void sendRequest(StreamObject so, Map<String, DataOutputStream> clients) {
        String client_sender = ds.receive(so.getDis(), TypeData.STRING) ;
        String client_receive = ds.receive(so.getDis(), TypeData.STRING) ;
        String request_id = cd.sendRequestAddContact(client_sender, client_receive) ;
        RequestAddContact rac = cd.getRequestContact(request_id) ;

        if (clients.get(client_receive) != null) {
            ds.send(clients.get(client_receive), "@new_request");
            ds.send(clients.get(client_receive), rac);
        }
    }

    public void getRequests(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<RequestAddContact> request_list = cd.getRequestContacts(client_id) ;
        ds.send(so.getDos(), "@requests");
        ds.send(so.getDos(), request_list);
    }

    public void getFeedBacks(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        List<FeedBackObject> feebacks = ps.getFeedBacks(client_id) ;
        ds.send(so.getDos(), "@feedbacks") ;
        ds.send(so.getDos(), feebacks) ;
    }

    public void accept(StreamObject so, Map<String, DataOutputStream> clients, ContactObject client) {
        String request_id = ds.receive(so.getDis(), TypeData.STRING) ;
        String client_id1 = ds.receive(so.getDis(), TypeData.STRING) ;
        String client_id2 = ds.receive(so.getDis(), TypeData.STRING) ;
        boolean result = cd.accept(client_id1, client_id2) ;
        cd.deleteRequest(request_id) ;
        if (result) {
            ds.send(so.getDos(), "@accept_success");
            ds.send(so.getDos(), client_id2) ;
        }

        if (clients.get(client_id2) != null) {
            ds.send(clients.get(client_id2), "@new_contact");
            ds.send(clients.get(client_id2), client);
        }
    }

    public void newFeedBack(StreamObject so, Map<String, DataOutputStream> clients) {
        CreateFeedBack cfb = ds.receive(so.getDis(), TypeData.CREATE_FEEDBACK) ;
        String feedback_id = ps.newFeedBack(cfb) ;
        FeedBackObject fo = ps.getFeedBack(feedback_id) ;

        if (clients.get(fo.getProduct_creator()) != null) {
            ds.send(clients.get(fo.getProduct_creator()), "@new_feedback");
            ds.send(clients.get(fo.getProduct_creator()), fo);
        }
    }

    public void changeInformation(StreamObject so) {
        BasicInformation bi = ds.receive(so.getDis(), TypeData.BASIC_INFORMATION) ;
        boolean result = acd.changeBasicInformation(bi) ;
        ds.send(so.getDos(), "@result_change_infor");
        ds.send(so.getDos(), result);
    }

    public void forgotPassword(StreamObject so) {
        boolean is_valid = false ;
        String username = ds.receive(so.getDis(), TypeData.STRING) ;
        ClientTemp ct = acd.getClientTempByUsername(username) ;
        if (ct != null) {
            ds.send(so.getDos(), true);
            SendEmail se = new SendEmail();
            String code = se.sendCodeToEmail(ct.getEmail()) ;
            is_valid = false ;
            for (int i = 0 ; i < 5 ; i ++) {
                String code_form_client = ds.receive(so.getDis(), TypeData.STRING) ;
                if (code.equals(code_form_client)) {
                    ds.send(so.getDos(), true);
                    is_valid = true ;
                    break ;
                } else {
                    ds.send(so.getDos(), false);
                }
            }
            if (is_valid) {
                String new_password = ds.receive(so.getDis(), TypeData.STRING) ;
                if (!new_password.equals("")){
                    boolean result = acd.changePassword(ct.getClient_id(), new_password) ;
                    ds.send(so.getDos(), result);
                }
            }
        } else {
            ds.send(so.getDos(), false);
        }
    }

    public void dowloadFile(StreamObject so) {
        String file_id = ds.receive(so.getDis(), TypeData.STRING) ;
        FileObject fo = md.getFile(file_id) ;
        ds.send(so.getDos(), "@file_return");
        ds.send(so.getDos(), fo);
    }

    public void downloadProduct(StreamObject so) {
        String product_id = ds.receive(so.getDis(), TypeData.STRING) ;
        FileObject fo = ps.dowloadProduct(product_id) ;
        ds.send(so.getDos(), "@product_object");
        ds.send(so.getDos(), fo);
    }

    public boolean disconnect(StreamObject so) {
        ds.send(so.getDos(), "@disconnect");
        ds.send(so.getDos(), true);
        return true ;
    }

    public void closeStreams(StreamObject so) {
        try {
            if (so.getDos() != null) {
                so.getDos().close();
            }
            if (so.getDis() != null) {
                so.getDis().close();
            }
            if (so.getSocket() != null) {
                if (!so.getSocket().isClosed()) {
                    so.getSocket().close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
