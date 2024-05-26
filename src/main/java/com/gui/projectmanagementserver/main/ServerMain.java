package com.gui.projectmanagementserver.main;

import com.gui.projectmanagementserver.network.Server;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Server server = new Server() ;
        server.start(1102) ;
    }
}
