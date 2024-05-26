package com.gui.projectmanagementserver.cache;

import java.io.DataOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Clients {
    private static Map<String, DataOutputStream> client_streams = new ConcurrentHashMap<>() ;

    private static List<String> clients = new CopyOnWriteArrayList<>() ;

    public void put(String id, DataOutputStream dos) {
        client_streams.put(id, dos) ;
    }

    public void removeClientStream(String id) {
        client_streams.remove(id) ;
    }

    public void removeClientInfor(String id) {
        clients.remove(id) ;
    }

    public void add(String id) {
        clients.add(id) ;
    }

    public Map<String, DataOutputStream> getClientStreams(){
        return client_streams ;
    }

    public List<String> getListClientConnected() {
        return clients ;
    }
}
