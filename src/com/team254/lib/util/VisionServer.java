package com.team254.lib.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class VisionServer implements Runnable {

    public static final int PORT = 8125;

    private static VisionServer s_instance = null;
    
    private volatile double angle = 5000.0;
    private volatile String message = "5000.0";
    
    public static VisionServer getInstance() {
        if (s_instance == null) {
            s_instance = new VisionServer(PORT);
        }
        return s_instance;
    }
    
    public String getAngle() {
    	//System.out.println(this.message);
    	return this.message;
    }
    private DatagramSocket socket;
    private boolean m_running = true;

    private VisionServer(int port) {
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        new Thread(this).start();
    }

    @Override
    public void run() {
        byte[] buf = new byte[2048];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (m_running) {
            try {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                this.message = message;
                // TODO: parse the message
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}