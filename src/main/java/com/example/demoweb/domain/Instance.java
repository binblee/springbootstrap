package com.example.demoweb.domain;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Instance {
    private String ip;

    public Instance() {
    }

    public String getIp() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }


}
