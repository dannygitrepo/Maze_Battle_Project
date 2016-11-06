/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SocketControl;

import Interface.MainInterface;
import functionality.Map;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;
import thread.PlayerThread;

/**
 *
 * @author admin
 */

public class Server {
    private ServerSocket serverSocket;
    private Vector<Socket> sockets= new Vector<>();
    private Vector<PlayerThread> threads = new Vector<>();
    private Map m;
    
    public Server(Map map) throws IOException {
        InetAddress ip;
        String hostname;
        m = map;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip.toString());
            System.out.println("Your current Hostname : " + hostname);

            serverSocket = new ServerSocket(6060);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void WaitAndConnect() throws IOException {
        while (true) {
            // wait for clients to accept
            Socket sock = serverSocket.accept();
            sockets.addElement(sock);
            
            // creating one thread for each client
            PlayerThread t = new PlayerThread(m, sock);
            threads.addElement(t);
            
            // staring thread
            t.start();
        }
    }
    public static void main (String args[]) throws IOException{
        MainInterface Interface = new MainInterface();
        Server s = new Server(Interface.GetMap());
        s.WaitAndConnect();
    }
}
