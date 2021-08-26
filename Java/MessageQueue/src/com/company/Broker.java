package com.company;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName Broker
 * @Description TODO 处理中心类
 * @Author suxl
 * @Date 2021/8/26
 * @Version 1.0
 */
public class Broker {
    //队列储存消息的最大容量
    private final static int MAX_SIZE = 3;
    //保存消息的数据容器
    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);
    //生产消息
    public static void produce(String msg){
        if(messageQueue.offer(msg)){
            System.out.println("向队列添加了消息:"+msg);
        }else{
            System.out.println("队列已满");
        }
    }
    //消费消息
    public static String consume(){
        String msg = messageQueue.poll();
        if(msg != null){
            System.out.println("取出了队列的消息:"+msg+";队列剩余消息数:"+messageQueue.size());
        }else{
            System.out.println("队列是空的");
        }
        return msg;
    }
}
