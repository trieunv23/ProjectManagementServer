package com.gui.projectmanagementserver.dao;

import com.gui.projectmanagementserver.database.JDBC_Client;
import com.gui.projectmanagementserver.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public static boolean setMaxAllowedPacket(){
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET GLOBAL max_allowed_packet = 67108864;";
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.executeUpdate();
            return true ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
    }

    public ContactObject getClientById(String client_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getClientById(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id);

            ResultSet contact = ps.executeQuery();
            while(contact.next()) {
                String id = contact.getString("id") ;
                String fullname = contact.getString("fullname") ;
                byte[] avata = contact.getBytes("avata") ;
                String gender = contact.getString("gender") ;
                String day_of_birth = contact.getString("day_of_birth") ;
                String email = contact.getString("email") ;
                String phonenumber = contact.getString("phonenumber") ;

                ContactObject co = new ContactObject(id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                return co ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<ContactObject> getContacts(String client_id) {
        List<ContactObject> list_contact = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getContact(?) " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id);

            ResultSet contacts = ps.executeQuery();
            while(contacts.next()) {
                String id = contacts.getString("id") ;
                String fullname = contacts.getString("fullname") ;
                byte[] avata = contacts.getBytes("avata") ;
                String gender = contacts.getString("gender") ;
                String day_of_birth = contacts.getString("day_of_birth") ;
                String email = contacts.getString("email") ;
                String phonenumber = contacts.getString("phonenumber") ;

                ContactObject co = new ContactObject(id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                list_contact.add(co) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return list_contact ;
    }

    public ContactObject getClientByEmail(String client_email) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getClientByEmail(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_email);

            ResultSet contact = ps.executeQuery();
            while(contact.next()) {
                String id = contact.getString("id") ;
                String fullname = contact.getString("fullname") ;
                byte[] avata = contact.getBytes("avata") ;
                String gender = contact.getString("gender") ;
                String day_of_birth = contact.getString("day_of_birth") ;
                String email = contact.getString("email") ;
                String phonenumber = contact.getString("phonenumber") ;

                ContactObject co = new ContactObject(id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                return co ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<ContactObject> getListInteractor(String client_id) {
        List<ContactObject> list_contact = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getInteractor(?) ;" ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id);

            ResultSet contacts = ps.executeQuery();
            while(contacts.next()) {
                String id = contacts.getString("id") ;
                String fullname = contacts.getString("fullname") ;
                byte[] avata = contacts.getBytes("avata") ;
                String gender = contacts.getString("gender") ;
                String day_of_birth = contacts.getString("day_of_birth") ;
                String email = contacts.getString("email") ;
                String phonenumber = contacts.getString("phonenumber") ;

                ContactObject co = new ContactObject(id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                list_contact.add(co) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return list_contact ;
    }

    public String sendRequestAddContact(String rq_sender, String rq_receive) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "SET @req_id := contactRequest(?, ?) ;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, rq_sender);
            ps.setString(2, rq_receive);
            ps.execute();

            sql = "SELECT * FROM requestcontacts WHERE requestcontacts.request_id = @req_id ;";
            ps = connection.prepareStatement(sql);
            ResultSet request_infor = ps.executeQuery();

            while (request_infor.next()) {
                String request_id = request_infor.getString("request_id") ;
                return request_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("The request already exists!") ;
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public void getInforRequest() {

    }

    public RequestAddContact getRequestContact(String request_id_in) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getRequestContact(?) ;" ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, request_id_in);
            ResultSet request_infor = ps.executeQuery();
            while(request_infor.next()) {
                String request_id = request_infor.getString("request_id") ;
                String request_date = request_infor.getString("request_date") ;
                String client_id = request_infor.getString("client_id") ;
                String fullname = request_infor.getString("fullname") ;
                byte[] avata = request_infor.getBytes("avata") ;
                String gender = request_infor.getString("gender") ;
                String day_of_birth = request_infor.getString("day_of_birth") ;
                String email = request_infor.getString("email") ;
                String phonenumber = request_infor.getString("phonenumber") ;

                ContactObject co = new ContactObject(client_id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                RequestAddContact rac = new RequestAddContact(co, request_id, request_date) ;
                return rac;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<RequestAddContact> getRequestContacts(String client_id_sender) {
        List<RequestAddContact> requests = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getRequestAddContactPreview(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id_sender);
            ResultSet request_infor = ps.executeQuery();
            while(request_infor.next()) {
                String request_id = request_infor.getString("request_id") ;
                String request_date = request_infor.getString("request_date") ;
                String client_id = request_infor.getString("client_id") ;
                String fullname = request_infor.getString("fullname") ;
                byte[] avata = request_infor.getBytes("avata") ;
                String gender = request_infor.getString("gender") ;
                String day_of_birth = request_infor.getString("day_of_birth") ;
                String email = request_infor.getString("email") ;
                String phonenumber = request_infor.getString("phonenumber") ;

                ContactObject co = new ContactObject(client_id, fullname, email, phonenumber, avata, day_of_birth, gender) ;
                RequestAddContact rac = new RequestAddContact(co, request_id, request_date) ;
                requests.add(rac) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return requests ;
    }

    public boolean accept(String client_id1, String client_id2) {
        List<RequestAddContact> requests = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL connectContact(?, ?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id1);
            ps.setString(2, client_id2);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return false ;
    }

    public void deleteRequest(String request_id) {
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL deleteRequest(?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, request_id);
            ps.executeUpdate() ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
