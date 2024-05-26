package com.gui.projectmanagementserver.network;

import com.gui.projectmanagementserver.converter.JsonConverter;
import com.gui.projectmanagementserver.security.AESEncryption;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class DataService {
    public void send(DataOutputStream dos, Object object) { // The client is not logged in
        try {
            if (dos == null) {
                throw new RuntimeException("DataOutputStream not found!");
            }
            String object_json = JsonConverter.convertObjectToJson(object) ;
            String object_json_crypto = AESEncryption.encrypt(object_json) ;
            byte[] object_json_crypto_byte = object_json_crypto.getBytes() ;
            int size = object_json_crypto_byte.length ;
            dos.writeInt(size);
            dos.flush();
            dos.write(object_json_crypto_byte, 0, size);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(Map<String, DataOutputStream> client_receives, String id_client, Object object) { // The client is logged in
        try (DataOutputStream dos = client_receives.get(id_client)){
            if (dos == null) {
                throw new RuntimeException("DataOutputStream not found!");
            }
            String object_json = JsonConverter.convertObjectToJson(object) ;
            String object_json_crypto = AESEncryption.encrypt(object_json) ;
            byte[] object_json_crypto_byte = object_json_crypto.getBytes() ;
            int size = object_json_crypto_byte.length ;
            dos.writeInt(size);
            dos.flush();
            dos.write(object_json_crypto_byte, 0, size);
            dos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T receive(DataInputStream dis, Type type) {
        try {
            if (dis == null) {
                throw new RuntimeException("DataInputStream not found!");
            }
            int size = dis.readInt() ;
            byte[] object_json_crypto_byte = new byte[size];
            dis.readFully(object_json_crypto_byte, 0, size);
            String object_json_crypto = new String(object_json_crypto_byte);
            String object_json = AESEncryption.decrypt(object_json_crypto);
            T object = JsonConverter.convertJsonToObject(object_json, type);
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
