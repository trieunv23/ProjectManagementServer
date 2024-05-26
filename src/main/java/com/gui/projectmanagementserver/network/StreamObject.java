package com.gui.projectmanagementserver.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class StreamObject {
    private Socket socket ;
    private DataInputStream dis ;
    private DataOutputStream dos ;

    public StreamObject(Socket socket, DataInputStream dis, DataOutputStream dos) {
        this.socket = socket;
        this.dis = dis;
        this.dos = dos;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
}
