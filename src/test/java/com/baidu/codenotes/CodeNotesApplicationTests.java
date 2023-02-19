package com.baidu.codenotes;

import com.baidu.codenotes.upd.Broadcast;
import com.baidu.codenotes.upd.BroadcastOnChange;
import com.baidu.codenotes.upd.Unicast;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.nio.ch.ThreadPool;

import java.io.IOException;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CodeNotesApplicationTests {

    @Test
    void contextLoads() throws IOException, InterruptedException {

//        String sendMsg = "{\"udp_password\":\"lxh123456\",\"action\":\"updateInfo\",\"fileName\":\"udp_base_info.json\",\"data\":\"{\\\"udp_password\\\": \\\"lxh123456\\\"}\"}";
//        Broadcast.send(sendMsg);
//
//        TimeUnit.SECONDS.sleep(10);
    }

    @Test
    void contextLoads2() throws IOException, InterruptedException {

        String sendMsg = "{\"udp_password\":\"lxh123456\",\"action\":\"discoveryDevice\"}";

        DatagramSocket ds = new DatagramSocket(3000);
        BroadcastOnChange.send(sendMsg, ds, "255.255.255.255", 8000);

        BroadcastOnChange.receive(new DatagramSocket(3000));
        TimeUnit.SECONDS.sleep(3);
    }

}
