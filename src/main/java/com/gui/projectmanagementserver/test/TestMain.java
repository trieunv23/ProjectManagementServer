package com.gui.projectmanagementserver.test;

import com.gui.projectmanagementserver.converter.JsonConverter;
import com.gui.projectmanagementserver.dao.ClientDao;
import com.gui.projectmanagementserver.dao.ProjectDao;
import com.gui.projectmanagementserver.entity.*;

import java.util.List;

public class TestMain {
    public static void main(String[] args) {
        ProjectDao pd = new ProjectDao();
        // CreateProjectObject cpo = new CreateProjectObject("Test111111", "Haha", "2024-03-12", "200đô", "50d64b3e") ;
        // ProjectObject po = pd.createProject(cpo) ;
        // System.out.println(po.getId());
        // CreateTaskProject cto = new CreateTaskProject("Element", "8e8ca6b4-fdee-11", null, "Lập Trình OOP", "Tạo lớp Interface", "50d64b3e", "2024-05-20", "50d64b3e", null) ;
        // TaskObject to = pd.createTask(cto) ;
        // System.out.println(to);
        // CreateProductObject cpo = new CreateProductObject("Sản Phẩm 1", new byte[0], "File.txt",  23000, "50d64b3e") ;
        // ProductObject po = pd.createProduct(cpo) ;
        // System.out.println(po);

        ClientDao cd = new ClientDao();

        // List<RequestAddContact> list = cd.getRequestContact("50d64b3e") ;
        // for (RequestAddContact r : list) {
        //     System.out.println(r.getRequest_id());
        // }
        // String json = JsonConverter.convertObjectToJson(list) ;
        // System.out.println(json);
        // List<RequestAddContact> list2 = JsonConverter.convertJsonToObject(json, )
        List<FeedBackObject> requests = pd.getFeedBacks("21c739bb") ;
        for (FeedBackObject f : requests) {
            System.out.println(f.getFeedback());
        }
    }
}
