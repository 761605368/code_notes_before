package com.baidu.codenotesbefore.upd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 单播
 * @author lxh
 * @date 2023/2/18 14:24
 */
public class Unicast {

    public static void send(String sendMsg, String sendAddress, Integer sendPort) throws IOException {
        if (null == sendMsg || null == sendAddress || null == sendPort) {
            return;
        }
        //1. 创建发送端的DatagramSocket对象
        DatagramSocket ds = new DatagramSocket();

        //2. 创建数据, 并将数据使用DatagramPacket打包
        byte[] bytes = sendMsg.getBytes();
        InetAddress address = InetAddress.getByName(sendAddress);

        //DatagramPacket(字节数组,内容长度,InetAddress对象,端口号)
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length,address,sendPort);

        //3. 调用DatagramSocket的sent方法发送数据
        ds.send(dp);

        //4. 释放资源
        ds.close();
    }

    public static void receive(Integer receivePort) throws IOException {
        if (null == receivePort) {
            return;
        }
        //1. 创建接收端的DatagramSocket对象, 指定接收数据的端口
        DatagramSocket ds = new DatagramSocket(receivePort);
        //2. 创建一个新箱子DatagramPacket, 用于接收数据
        byte[] bytes = new byte[2048];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //3. 调用DatagramSocket的receive方法接收数据, 放入新箱子中
        System.out.println("--- 正在接收数据... ---");
        ds.receive(dp);
        System.out.println("--- 接收到数据... ---");
        //4. 解析数据包, 将数据展示在控制台, 参数(bytes数组,从0开始,要所有有效数据)
        System.out.println(new String(bytes, 0, dp.getLength()));
        //5. 释放资源
        ds.close();
    }


}
