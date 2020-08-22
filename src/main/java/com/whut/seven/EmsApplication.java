package com.whut.seven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class EmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmsApplication.class, args);
        try {
            InetAddress ip4 = Inet4Address.getLocalHost();
            System.out.println("当前服务器IP为"+ip4.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
