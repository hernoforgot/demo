package com.company.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @ClassName MqClient
 * @Description TODO 与消息处理中心之间通信的客户端类
 * @Author suxl
 * @Date 2021/8/26
 * @Version 1.0
 */
public class MqClient {
    private final static int SERVER_PORT = 9990;
    //生产消息
    public static void produce(String msg) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),SERVER_PORT);
        try(
                PrintWriter out = new PrintWriter(socket.getOutputStream())
        ){
            out.println(msg);
            out.flush();
        }
    }
    //消费消息
    public static String consume() throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),SERVER_PORT);
        try(
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream())
        ){
            //先向消息队列发送字符串"CONSUME"表示消费
            out.println("CONSUME");
            out.flush();
            //再从消息队列中获取一条消息
            String msg = in.readLine();
            return  msg;
        }
    }
}
