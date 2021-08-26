package com.company;

import java.io.IOException;
import java.net.ServerSocket;
//启动服务
public class Main {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(BrokerServer.SERVICE_PORT);
        while(true){
            BrokerServer server = new BrokerServer(socket.accept());
            new Thread(server).start();
        }
    }
}
