package com.baidu.codenotes.upd;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.Charset;

/**
 * 广播
 * @author lxh
 * @date 2023/2/18 14:02
 */
public class Broadcast {


    public static void send(String sendMsg) throws IOException {
        if (null == sendMsg) {
            return;
        }
        System.out.println("发送数据:" + sendMsg);
        //1. 创建广播发送端的DatagramSocket对象
        DatagramSocket ds = new DatagramSocket(3000);
        //2. 创建数据, 并将数据使用DatagramPacket打包, InetAddress对象为广播地址
        byte[] bytes = sendMsg.getBytes(Charset.forName("utf-8"));
        DatagramPacket dp = new DatagramPacket(
                bytes
                , bytes.length
                , InetAddress.getByName("255.255.255.255")
                , 8000);
        //3. 调用DatagramSocket的sent方法发送数据
        ds.send(dp);


        //2. 创建一个新箱子DatagramPacket, 用于接收数据
        byte[] bytes2 = new byte[2048];
        DatagramPacket dp2 = new DatagramPacket(bytes2, bytes2.length);
        //3. 调用DatagramSocket的receive方法接收数据, 放入新箱子中
        ds.receive(dp2);
        //4. 解析数据包, 将数据展示在控制台
        System.out.println(new String(bytes2, 0, dp2.getLength()));

        //4. 释放资源
        ds.close();
    }


    public static void receive() throws IOException {
        //1. 创建接收端的DatagramSocket对象, 指定接收数据的端口
        DatagramSocket ds = new DatagramSocket(3000);
        //2. 创建一个新箱子DatagramPacket, 用于接收数据
        byte[] bytes = new byte[2048];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //3. 调用DatagramSocket的receive方法接收数据, 放入新箱子中
        ds.receive(dp);
        //4. 解析数据包, 将数据展示在控制台
        System.out.println(new String(bytes, 0, dp.getLength()));
        //5. 释放资源
        ds.close();
    }
}
