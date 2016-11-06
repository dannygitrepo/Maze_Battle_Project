/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import functionality.Map;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class PlayerThread implements Runnable{
    private Thread t = new Thread(this);
    private Map maze;
    private Socket sock;
    public PlayerThread(Map m, Socket sock){
        maze = m;
        this.sock = sock; 
    }
    @Override
    public void run() {
        try {
            // starting running thread
            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        t.start();
    }
}
