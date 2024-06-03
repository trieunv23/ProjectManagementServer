package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.dao.AccountClientDao;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.dao.MessageDao;
import com.gui.projectmanagementserver.dao.ProjectDao;
import com.gui.projectmanagementserver.entity.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ClientStream {
    DataService ds = new DataService();
    ProjectDao ps = new ProjectDao() ;
    ClientDao cd = new ClientDao() ;
    MessageDao md  = new MessageDao() ;

    AccountClientDao acd = new AccountClientDao() ;

    public void getInforClient(StreamObject so) {
        String client_id = ds.receive(so.getDis(), TypeData.STRING) ;
        ContactObject client = cd.getClientById(client_id) ;
        ds.send(so.getDos(), "@interactor_just_sendMs");
        ds.send(so.getDos(), client) ;
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

    public void changeInformation(StreamObject so) {
        BasicInformation bi = ds.receive(so.getDis(), TypeData.BASIC_INFORMATION) ;
        boolean result = acd.changeBasicInformation(bi) ;
        ds.send(so.getDos(), "@result_change_infor");
        ds.send(so.getDos(), result);
    }

    public void dowloadFile(StreamObject so) {
        String file_id = ds.receive(so.getDis(), TypeData.STRING) ;
        FileObject fo = md.getFile(file_id) ;
        ds.send(so.getDos(), "@file_return");
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
