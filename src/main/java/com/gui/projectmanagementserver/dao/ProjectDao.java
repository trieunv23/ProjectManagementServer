package com.gui.projectmanagementserver.dao;

import com.gui.projectmanagementserver.converter.LocalDateConversion;
import com.gui.projectmanagementserver.database.JDBC_Client;
import com.gui.projectmanagementserver.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao{
    public ProjectObject createProject(CreateProjectObject cpo) {
        if (cpo == null) {
            return null ;
        }
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET @project_id := createProject(?, ?, ?, ?, ?); ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpo.getName_project());
            ps.setString(2, cpo.getDescribe());
            ps.setDate(3, Date.valueOf(LocalDateConversion.stringToLocalDate(cpo.getExpected_end())));
            ps.setString(4, cpo.getBudget());
            ps.setString(5, cpo.getCreator());
            ps.execute();

            sql = "SELECT * FROM projects WHERE project_id = @project_id ;";
            ps = connection.prepareStatement(sql);
            ResultSet infor_project = ps.executeQuery();

            while (infor_project.next()) {
                String projec_id = infor_project.getString("project_id") ;
                String project_name = infor_project.getString("project_name") ;
                String description = infor_project.getString("description") ;
                String start_date = infor_project.getString("start_date") ;
                String end_date = infor_project.getString("end_date") ;
                String status = infor_project.getString("status") ;
                String budget = infor_project.getString("budget") ;
                String creator = infor_project.getString("creator") ;
                String manager = infor_project.getString("manager") ;
                List<MemberObject> member = new ArrayList<>();
                // MemberObject mo = new MemberObject(cpo.getCreator(), projec_id, "Manager") ;
                // member.add(mo) ;
                ProjectObject po = new ProjectObject(projec_id, project_name, description, start_date, end_date, status, budget, creator, manager, member) ;
                return po;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<ProjectPreview> getListProject(String client_id) {
        List<ProjectPreview> projects = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getPreviewProjects(?) ;" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client_id);
            ResultSet project_infor = ps.executeQuery() ;
            while (project_infor.next()) {
                String role = project_infor.getString("member_role") ;
                String project_id = project_infor.getString("project_id") ;
                String project_name = project_infor.getString("project_name") ;
                String start_date = project_infor.getString("start_date") ;
                String end_date = project_infor.getString("end_date") ;
                String status = project_infor.getString("status") ;
                String budget = project_infor.getString("budget") ;
                String creator_id = project_infor.getString("creator") ;
                String creator_name = project_infor.getString("creator_name") ;
                String manager_id = project_infor.getString("manager") ;
                String manager_name = project_infor.getString("manager_name") ;
                int num_members = project_infor.getInt("num_members") ;
                int num_tasks = project_infor.getInt("num_tasks") ;
                ClientPreview creator = new ClientPreview(creator_id, creator_name) ;
                ClientPreview manager = new ClientPreview(manager_id, manager_name) ;
                ProjectPreview pp = new ProjectPreview(role, project_id, project_name, start_date, end_date, status, budget, creator, manager, num_members, num_tasks) ;
                projects.add(pp) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return projects ;
    }

    public TaskObject createTask(CreateTaskProject ctp) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET @task_id := createTask(?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ctp.getClassify());
            ps.setString(2, ctp.getProject_id());
            ps.setString(3, ctp.getContainer_id());
            ps.setString(4, ctp.getTask_name());
            ps.setString(5, ctp.getJob_requirements());
            ps.setString(6, ctp.getUndertaker());
            ps.setDate(7, Date.valueOf(ctp.getDeadline()));
            ps.setString(8, ctp.getCreator());
            ps.setString(9, ctp.getProduct_id());
            ps.execute();

            sql = "SELECT tasks.task_id, tasks.request_date FROM tasks WHERE task_id = @task_id ;";
            ps = connection.prepareStatement(sql);
            ResultSet infor_task = ps.executeQuery();

            while (infor_task.next()) {
                String task_id = infor_task.getString("task_id") ;
                String request_date = infor_task.getString("request_date") ;
                TaskObject tp = new TaskObject(task_id, ctp.getClassify(), ctp.getProject_id(), ctp.getContainer_id(), ctp.getTask_name(), ctp.getJob_requirements(), ctp.getUndertaker(), request_date, ctp.getDeadline(), ctp.getCreator(), ctp.getProduct_id()) ;
                return tp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<TaskPreview> getListTaskPreview(String project_id) {
        List<TaskPreview> list_task = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getPreviewTasks(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, project_id);
            ResultSet tasks = ps.executeQuery() ;
            while (tasks.next()) {
                String task_id = tasks.getString("task_id") ;
                String classify = tasks.getString("classify") ;
                String container_id = tasks.getString("container_id") ;
                String task_name = tasks.getString("task_name") ;
                TaskPreview tp = new TaskPreview(task_id, classify, project_id, container_id, task_name) ;
                list_task.add(tp) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list_task ;
    }

    public TaskObject getTask(String task_id_in) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getTask(?)" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, task_id_in);
            ResultSet task = ps.executeQuery() ;
            if (task.next()) {
                String task_id = task.getString("task_id") ;
                String classify = task.getString("classify") ;
                String project_id = task.getString("project_id") ;
                String container_id = task.getString("container_id") ;
                String task_name = task.getString("task_name") ;
                String job_requirements = task.getString("job_requirements") ;
                String undertaker = task.getString("undertaker") ;
                String request_date = task.getString("request_date");
                String deadline = task.getString("deadline");
                String creator = task.getString("creator") ;
                String product_id = task.getString("product_id") ;
                TaskObject to = new TaskObject(task_id, classify, project_id, container_id, task_name, job_requirements, undertaker, request_date, deadline, creator, product_id) ;
                return to ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<ClientObject> getMembers(String project_id) {
        List<ClientObject> list_member = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getMembers(?) ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, project_id);
            ResultSet members = ps.executeQuery() ;
            while (members.next()) {
                String member_id = members.getString("member_id") ;
                String fullname = members.getString("fullname") ;
                String email = members.getString("email") ;
                String phonenumber = members.getString("phonenumber") ;
                byte[] avata = members.getBytes("avata") ;
                String role = members.getString("role") ;
                ClientObject co = new ClientObject(member_id, fullname, email, phonenumber, avata, project_id, role) ;
                list_member.add(co) ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return list_member ;
    }

    public ProductObject createProduct(CreateProductObject cpo) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET @product_id := createProduct(?, ?, ?, ?, ?, ?) ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cpo.getTask_id());
            ps.setString(2, cpo.getProduct_name());
            ps.setBytes(3, cpo.getFile_data());
            ps.setString(4, cpo.getFile_name());
            ps.setInt(5, cpo.getFile_size());
            ps.setString(6, cpo.getCreator());
            ps.execute();

            sql = "SELECT products.product_id, products.finish_day FROM products WHERE product_id = @product_id ;";
            ps = connection.prepareStatement(sql);
            ResultSet infor_product = ps.executeQuery();

            while (infor_product.next()) {
                String product_id = infor_product.getString("product_id") ;
                String finish_day = infor_product.getString("finish_day") ;
                ProductObject po = new ProductObject(product_id, cpo.getProduct_name(), cpo.getFile_data(), cpo.getFile_name(), cpo.getFile_size(), finish_day, cpo.getCreator()) ;
                return po;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public ProductPreview getProduct(String product_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getProductPreview(?); " ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product_id);
            ResultSet product_infor = ps.executeQuery() ;
            while (product_infor.next()) {
                String product_name = product_infor.getString("product_name") ;
                String file_name = product_infor.getString("file_name") ;
                int file_size = product_infor.getInt("file_size") ;
                String finish_day = product_infor.getString("finish_day") ;
                String creator = product_infor.getString("creator") ;
                ProductPreview pp = new ProductPreview(product_id, product_name, file_name, file_size, finish_day, creator) ;
                return pp ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public FileObject dowloadProduct(String product_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SELECT product_id, file_name, file_size, file_data FROM products WHERE products.product_id = ? ;" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product_id);
            ResultSet product_file = ps.executeQuery() ;
            while (product_file.next()) {
                String file_name = product_file.getString("file_name");
                int size = product_file.getInt("file_size");
                byte[] file_data = product_file.getBytes("file_data");
                FileObject fo = new FileObject("", file_name, file_data, size) ;
                return fo ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public String addMembers(String client_id, String project_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "Call newMember(?, ?);" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client_id);
            ps.setString(2, project_id);
            ps.executeUpdate();
            return client_id ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<FeedBackObject> getFeedBacks(String client_id_reveive) {
        List<FeedBackObject> feedback_list = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getFeedBacks(?)" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client_id_reveive);
            ResultSet feedbacks = ps.executeQuery() ;
            while (feedbacks.next()) {
                String client_id = feedbacks.getString("client_id") ;
                String fullname = feedbacks.getString("fullname") ;
                byte[] avata = feedbacks.getBytes("avata") ;
                String gender = feedbacks.getString("gender") ;
                String day_of_birth = feedbacks.getString("day_of_birth") ;
                String email = feedbacks.getString("email") ;
                String phonenumber = feedbacks.getString("phonenumber") ;
                String feedback_id = feedbacks.getString("feedback_id") ;
                String project_id = feedbacks.getString("project_id") ;
                String task_id = feedbacks.getString("task_id") ;
                String product_id = feedbacks.getString("product_id") ;
                String product_creator = feedbacks.getString("product_creator") ;
                String project_name = feedbacks.getString("project_name") ;
                String task_name = feedbacks.getString("task_name") ;
                String feedback = feedbacks.getString("feedback") ;
                String feedback_date = feedbacks.getString("feedback_date_send");
                ContactObject co = new ContactObject(client_id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                FeedBackObject fbo = new FeedBackObject( feedback_id, co, project_id, task_id, product_id, product_creator, project_name, task_name, feedback, feedback_date) ;
                feedback_list.add(fbo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return feedback_list ;
    }

    public String newFeedBack(CreateFeedBack cf) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET @id := newFeedBack(?, ?, ?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cf.getFeedback_sender());
            ps.setString(2, cf.getProject_id());
            ps.setString(3, cf.getTask_id());
            ps.setString(4, cf.getProduct_id());
            ps.setString(5, cf.getFeedback());
            ps.execute();

            sql = "SELECT @id;";
            ps = connection.prepareStatement(sql);
            ResultSet id_fb = ps.executeQuery();

            while (id_fb.next()) {
                String id = id_fb.getString("@id") ;
                return id ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public FeedBackObject getFeedBack(String feedback_id_in) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getFeedBack(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, feedback_id_in);
            ResultSet feedback = ps.executeQuery() ;
            while (feedback.next()) {
                String client_id = feedback.getString("client_id") ;
                String fullname = feedback.getString("fullname") ;
                byte[] avata = feedback.getBytes("avata") ;
                String gender = feedback.getString("gender") ;
                String day_of_birth = feedback.getString("day_of_birth") ;
                String email = feedback.getString("email") ;
                String phonenumber = feedback.getString("phonenumber") ;
                String feedback_id = feedback.getString("feedback_id") ;
                String project_id = feedback.getString("project_id") ;
                String task_id = feedback.getString("task_id") ;
                String product_id = feedback.getString("product_id") ;
                String product_creator = feedback.getString("product_creator") ;
                String project_name = feedback.getString("project_name") ;
                String task_name = feedback.getString("task_name") ;
                String feedback_data = feedback.getString("feedback") ;
                String feedback_date = feedback.getString("feedback_date_send");
                ContactObject co = new ContactObject(client_id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                FeedBackObject fbo = new FeedBackObject( feedback_id, co, project_id, task_id, product_id, product_creator, project_name, task_name, feedback_data, feedback_date) ;
                return fbo ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public boolean deleteProject(String project_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "DELETE FROM projects WHERE projects.project_id = ? ;" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, project_id);
            int result = ps.executeUpdate() ;
            if (result != 0) {
                return true ;
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            closeConnection(connection);
        }
    }

    public boolean isManager(String client_id, String project_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SELECT * FROM members WHERE members.member_id = ? AND members.project_id = ?" ;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client_id);
            ps.setString(2, project_id);
            ResultSet rs = ps.executeQuery() ;
            if (rs.next()) {
                String role = rs.getString("role") ;
                if (role.equals("Manager")) {
                    return true ;
                } else {
                    return false ;
                }
            } else {
                return false ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false ;
        } finally {
            closeConnection(connection);
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
