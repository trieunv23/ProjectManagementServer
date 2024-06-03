package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.dao.MessageDao;
import com.gui.projectmanagementserver.dao.ProjectDao;
import com.gui.projectmanagementserver.entity.*;
import com.gui.projectmanagementserver.functions.SendEmail;

import java.io.DataOutputStream;
import java.util.List;
import java.util.Map;

public class ProjectStream {

    DataService ds = new DataService();
    ProjectDao ps = new ProjectDao() ;
    ClientDao cd = new ClientDao() ;
    MessageDao md  = new MessageDao() ;

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

    public void newFeedBack(StreamObject so, Map<String, DataOutputStream> clients) {
        CreateFeedBack cfb = ds.receive(so.getDis(), TypeData.CREATE_FEEDBACK) ;
        String feedback_id = ps.newFeedBack(cfb) ;
        FeedBackObject fo = ps.getFeedBack(feedback_id) ;

        if (clients.get(fo.getProduct_creator()) != null) {
            ds.send(clients.get(fo.getProduct_creator()), "@new_feedback");
            ds.send(clients.get(fo.getProduct_creator()), fo);
        }
    }

    public void downloadProduct(StreamObject so) {
        String product_id = ds.receive(so.getDis(), TypeData.STRING) ;
        FileObject fo = ps.dowloadProduct(product_id) ;
        ds.send(so.getDos(), "@product_object");
        ds.send(so.getDos(), fo);
    }

    public String deleteProjectFirst(StreamObject so, ClientData client_data) {
        SendEmail se = new SendEmail() ;
        String code = se.sendCodeToEmail(client_data.getEmail()) ;
        return code ;
    }

    public void deleteProjectLast(StreamObject so, String code_just_send, ClientData clientData) {
        String code = ds.receive(so.getDis(), TypeData.STRING) ;
        String project_id = ds.receive(so.getDis(), TypeData.STRING) ;
        ds.send(so.getDos(), "@result_delete_project");
        if (code.equals(code_just_send)) {
            ds.send(so.getDos(), "@code_is_correct");
            if (ps.isManager(clientData.getId(), project_id)) {
                ds.send(so.getDos(), "@role_valid");
                boolean result_delete = ps.deleteProject(project_id) ;
                if (result_delete) {
                    ds.send(so.getDos(), "@delete_success");
                    ds.send(so.getDos(), project_id);
                } else {
                    ds.send(so.getDos(), "@delete_unsuccess");
                }
            } else {
                ds.send(so.getDos(), "@role_invalid");
            }
        } else {
            ds.send(so.getDos(), "@code_is_incorrect");
        }
    }

}
