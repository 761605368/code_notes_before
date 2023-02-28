package com.baidu.codenotesbefore.upd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * 组播
 * @author lxh
 * @date 2023/2/18 14:15
 */
public class Multicast {
//    发送端: 需要指定组播地址, 而不是端口号
//    组播地址: 224.0.0.0-239.255.255.255
//    预留地址: 224.0.0.0-224.0.0.255(计算机用了我们不能用)从224.0.1.0开始朝后
//    接收端: 有一组计算机, 指定IP

    public static void send(String sendMsg, String multicastAddress) throws IOException {
        if (null == sendMsg || null == multicastAddress) {
            return;
        }
        //1. 创建组播发送端的DatagramSocket对象
        DatagramSocket ds = new DatagramSocket();
        //2. 创建数据,并将数据使用DatagramPacket打包,InetAddress对象为组播地址
        byte[] bytes = sendMsg.getBytes();
        DatagramPacket dp = new DatagramPacket(
                bytes, bytes.length
                , InetAddress.getByName(multicastAddress)
                , 20000);
        //3. 调用DatagramSocket的sent方法发送数据
        ds.send(dp);
        //4. 释放资源
        ds.close();
    }

    public static void receive(String multicastAddress) throws IOException {
        if (null == multicastAddress) {
            return;
        }
        //1. 创建组播接收端的MulticastSocket对象, 指定接收数据的端口
        MulticastSocket ms = new MulticastSocket(30000);
        //2. 创建一个新箱子DatagramPacket, 用于接收数据
        byte[] bytes = new byte[2048];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //3. 使用MulticastSocket的joinGroup方法, 指定组播地址
        ms.joinGroup(InetAddress.getByName(multicastAddress));
        //4. 调用MulticastSocket的receive方法接收数据, 放入新箱子中
        ms.receive(dp);
        //5. 解析数据包, 将数据展示在控制台
        System.out.println(new String(bytes, 0, dp.getLength()));
        //6. 释放资源, 如果要一直接收, 写一个循环, 暂时不要释放资源
        ms.close();
    }

}
