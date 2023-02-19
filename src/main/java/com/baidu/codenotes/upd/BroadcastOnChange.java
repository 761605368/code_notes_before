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
public class BroadcastOnChange {

    public static void send(String sendMsg) throws IOException {
        send(sendMsg, "255.255.255.255", 0);
    }

    public static void send(String sendMsg, int receivePort) throws IOException {
        send(sendMsg, "255.255.255.255", receivePort);
    }

    public static void send(String sendMsg, String receiveAddress, int receivePort) throws IOException {
        send(sendMsg, new DatagramSocket(), receiveAddress, receivePort);
    }

    public static void send(String sendMsg, DatagramSocket sendDs, String receiveAddress, int receivePort) throws IOException {
        System.out.println("发送数据:" + sendMsg);

        if (null == sendMsg) {
            return;
        }

        //1. 创建广播发送端的DatagramSocket对象
        sendDs = sendDs == null ? new DatagramSocket() : sendDs;
        //2. 创建数据, 并将数据使用DatagramPacket打包, InetAddress对象为广播地址
        byte[] bytes = sendMsg.getBytes(Charset.forName("utf-8"));
        DatagramPacket dp = new DatagramPacket(
                bytes
                , bytes.length
                , InetAddress.getByName(receiveAddress)
                , receivePort);
        //3. 调用DatagramSocket的sent方法发送数据
        sendDs.send(dp);

        //4. 释放资源
        sendDs.close();
    }


    public static String receive(DatagramSocket ds) throws IOException {
        //1. 创建接收端的DatagramSocket对象, 指定接收数据的端口
        ds = ds == null ? new DatagramSocket() : ds;
        //2. 创建一个新箱子DatagramPacket, 用于接收数据
        byte[] bytes = new byte[2048];
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //3. 调用DatagramSocket的receive方法接收数据, 放入新箱子中
        ds.receive(dp);
        //4. 解析数据包, 将数据展示在控制台
        String receiveMsg = new String(bytes, 0, dp.getLength());
        System.out.println("接收到的参数:" + receiveMsg);
        //5. 释放资源
        ds.close();

        return receiveMsg;
    }

    public static String receive(int port) throws IOException {
        return BroadcastOnChange.receive(new DatagramSocket(port));
    }

    public static String receive(int port, String address) throws IOException {
        return BroadcastOnChange.receive(new DatagramSocket(port, InetAddress.getByName(address)));
    }

}
