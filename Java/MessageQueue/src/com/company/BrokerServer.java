package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @ClassName BrokerServer
 * @Description TODO 把处理中心暴露给外部访问的服务类
 * @Author suxl
 * @Date 2021/8/26
 * @Version 1.0
 */

public class BrokerServer implements Runnable {
    public static int SERVICE_PORT = 9990;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream())
        ){
            while(true){
                String str = in.readLine();
                if(str==null){
                    continue;
                }
                System.out.println("接收到数据:"+str);
                if(str.equals("CONSUME")){
                    String msg = Broker.consume();
                    out.println(msg);
                    out.flush();
                }else{
                    Broker.produce(str);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}