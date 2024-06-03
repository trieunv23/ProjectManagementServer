package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.dao.AccountClientDao;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.dao.MessageDao;
import com.gui.projectmanagementserver.dao.ProjectDao;
import com.gui.projectmanagementserver.entity.*;
import com.gui.projectmanagementserver.functions.SendEmail;

import java.io.DataOutputStream;
import java.util.Map;

public class GuestStatus {

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
        /* } else if (client_streams.containsKey(client_data.getId())) {
            ds.send(so.getDos(), "@refuse");
            */
        } else if (client_data != null){
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
}
