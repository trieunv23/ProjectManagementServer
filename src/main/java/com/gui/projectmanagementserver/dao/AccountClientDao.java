package com.gui.projectmanagementserver.dao;

import com.gui.projectmanagementserver.database.JDBC_Client;
import com.gui.projectmanagementserver.entity.*;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountClientDao {

    public boolean regester(RegesterObject regester) {
        System.out.println("1233333");
        Connection connection = JDBC_Client.getConnection();
        String sql = "CALL regester(?, ?, ?, ?, ?, ?, ?) ;" ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, regester.getUsername());
            ps.setString(2, getMd5(regester.getPasswowrd()));
            ps.setString(3, regester.getF_name().concat(regester.getL_name()));
            ps.setString(4, regester.getDay_of_birth());
            ps.setString(5, regester.getGender());
            ps.setString(6, regester.getEmail());
            ps.setString(7, regester.getPhonenumber());
            int result = ps.executeUpdate();
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

    public ClientData login (LoginObject login_object) {
        ClientData client_data = null ;
        Connection connection = JDBC_Client.getConnection();
        String sql = "CALL login(?, ?);" ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, login_object.getUsername());
            ps.setString(2, login_object.getPassword());
            ResultSet result_set = ps.executeQuery();
            while (result_set.next()) {
                String id = result_set.getString("id") ;
                String fullname = result_set.getString("fullname") ;
                String day_of_birth = result_set.getString("day_of_birth") ;
                String gender = result_set.getString("gender") ;
                byte[] avata = result_set.getBytes("avata") ;
                String email = result_set.getString("email") ;
                String phonenumber = result_set.getString("phonenumber") ;
                client_data = new ClientData(id, avata, fullname, day_of_birth, gender, email, phonenumber) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return client_data ;
    }

    public boolean changeBasicInformation(BasicInformation bi) {
        Connection connection = JDBC_Client.getConnection();
        String sql = "CALL changeBasicInformation(?, ?, ?, ?, ?) ;" ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, bi.getClient_id());
            ps.setBytes(2, bi.getAvata());
            ps.setString(3, bi.getFullname());
            ps.setString(4, bi.getDay_of_birth());
            ps.setString(5, bi.getGender());
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

    public ClientTemp getClientTempByUsername(String username) {
        Connection connection = JDBC_Client.getConnection();
        String sql = "SELECT * FROM login_information \n" +
                "JOIN contact_infor ON contact_infor.client_id = login_information.client_id \n" +
                "WHERE login_information.username = ? ; " ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet result = ps.executeQuery() ;
            if (result.next()) {
                String client_id = result.getString("client_id") ;
                String email = result.getString("email") ;
                ClientTemp ct = new ClientTemp(client_id, username, email) ;
                return ct ;
            } else {
                return null ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null ;
        } finally {
            closeConnection(connection);
        }
    }

    public boolean changePassword(String client_id, String new_password) {
        Connection connection = JDBC_Client.getConnection();
        String sql = "CALL changePassword(?, ?)" ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, client_id);
            ps.setString(2, new_password);
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

    // public void

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMd5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
