/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import functionality.Player;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class GettingMessageFromServerThread extends Thread{
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ObjectInputStream ois;

    private ObjectOutputStream oos;
    private Player p;
    public GettingMessageFromServerThread(Socket sock, Player player, DataOutputStream out, DataInputStream in) throws IOException {
        socket = sock;
        this.out = out;
        this.in = in;
        ois = new ObjectInputStream(sock.getInputStream());
        oos = new ObjectOutputStream(sock.getOutputStream());
        p = player;
    }
    public void run(){
        try {
            // catch message
            int message = Integer.parseInt(in.readUTF());
            if (message == 10) {
                int id1 = Integer.parseInt(in.readUTF());
                p.setOrderNum(id1);
                int id2 = (int)ois.readObject();
                if (id2 != p.GetOrder()) {
                    Player NewPlayer = (Player)ois.readObject();
                }
                else {
                    
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GettingMessageFromServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GettingMessageFromServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
