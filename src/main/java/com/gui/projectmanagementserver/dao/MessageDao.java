package com.gui.projectmanagementserver.dao;

import com.gui.projectmanagementserver.database.JDBC_Client;
import com.gui.projectmanagementserver.entity.FileObject;
import com.gui.projectmanagementserver.entity.ImageObject;
import com.gui.projectmanagementserver.entity.Message;
import com.gui.projectmanagementserver.entity.MessageSend;
import com.mysql.cj.protocol.MessageSender;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {

    public Message sendMessage(MessageSend ms) {
        Connection connection = JDBC_Client.getConnection() ;
        try {
            String sql = "SET @message_id := messageUpload(?, ?, ?, ?, ?, ?, ?, ?) ; " ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, ms.getSender_id());
            ps.setString(2, ms.getReceiver_id());
            ps.setString(3, ms.getType_message());
            ps.setString(4, ms.getMessage());
            if (ms.getImage() == null) {
                ps.setBytes(5, null);
            } else {
                ps.setBytes(5, ms.getImage().getImage_data());
            }

            if (ms.getFile() == null) {
                ps.setBytes(6, null);
                ps.setString(7, null);
                ps.setInt(8, -1);
            } else {
                ps.setBytes(6, ms.getFile().getFile_data());
                ps.setString(7, ms.getFile().getFile_name());
                ps.setInt(8, ms.getFile().getFile_size());
            }

            ps.execute();
            sql = "SELECT * FROM messages WHERE message_id = @message_id ;" ;
            ps = connection.prepareStatement(sql) ;
            ResultSet message = ps.executeQuery() ;
            while (message.next()) {
                String message_id = message.getString("message_id") ;
                String sender_id = message.getString("sender_id") ;
                String receive_id = message.getString("receive_id") ;
                String type_message = message.getString("type_message") ;
                String m = message.getString("message") ;
                String image = message.getString("image") ;
                String file_id = message.getString("file_id") ;
                String day_send = message.getString("day_send") ;
                ImageObject image_object = null ;
                FileObject file_object = null ;
                if (type_message.equals("Image")) {
                    message = null ;
                    image_object = getImage(image) ;
                } else if (type_message.equals("File")) {
                    message = null ;
                    file_object = getFile(file_id) ;
                }
                Message msg = new Message(message_id, sender_id, receive_id, type_message, m, image_object, file_object, day_send) ;
                return msg ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return null ;
    }

    public List<Message> getMessages(String client_id, String interactor_id) {
        List<Message> list_message = new ArrayList<>() ;
        Connection connection = JDBC_Client.getConnection();
        try {
            String sql = "CALL getMessages(?, ?) ;" ;
            PreparedStatement ps = connection.prepareStatement(sql) ;
            ps.setString(1, client_id);
            ps.setString(2, interactor_id);

            ResultSet messages = ps.executeQuery();
            while(messages.next()) {
                String message_id = messages.getString("message_id") ;
                String sender_id = messages.getString("sender_id") ;
                String receive_id = messages.getString("receive_id") ;
                String type_message = messages.getString("type_message") ;
                String message = messages.getString("message") ;
                String image = messages.getString("image") ;
                String file_id = messages.getString("file_id") ;
                String day_send = messages.getString("day_send") ;
                ImageObject image_object = null ;
                FileObject file_object = null ;
                if (type_message.equals("Image")) {
                    message = null ;
                    image_object = getImage(image) ;
                } else if (type_message.equals("File")) {
                    message = null ;
                    file_object = getFile(file_id) ;
                }
                Message m = new Message(message_id, sender_id, receive_id, type_message, message, image_object, file_object, day_send) ;
                list_message.add(m) ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection(connection);
        }
        return list_message ;
    }

    public ImageObject getImage(String img_id) {
        if (img_id != null) {
            Connection connection = JDBC_Client.getConnection();
            try {
                String sql = "CALL getImage(?) ;" ;
                PreparedStatement ps = connection.prepareStatement(sql) ;
                ps.setString(1, img_id) ;

                ResultSet image = ps.executeQuery();
                while(image.next()) {
                    String image_id = image.getString("image_id") ;
                    byte[] image_data = image.getBytes("image_data") ;
                    int image_size = image.getInt("file_image_size");
                    ImageObject io = new ImageObject(image_id, image_data, image_size) ;
                    return io ;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection(connection);
            }
            return null ;
        }
        return null ;
    }

    public FileObject getFile(String file_id_in) {
        if (file_id_in != null) {
            Connection connection = JDBC_Client.getConnection();
            try {
                String sql = "CALL getFile(?) ; " ;
                PreparedStatement ps = connection.prepareStatement(sql) ;
                ps.setString(1, file_id_in) ;

                ResultSet file = ps.executeQuery();
                while(file.next()) {
                    String file_id = file.getString("file_id") ;
                    byte[] file_data = file.getBytes("file_data") ;
                    String file_name = file.getString("file_name") ;
                    int file_size = file.getInt("file_size");
                    FileObject f = new FileObject(file_id, file_name, file_data, file_size) ;
                    return f ;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                closeConnection(connection);
            }
            return null ;
        }
        return null ;
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
