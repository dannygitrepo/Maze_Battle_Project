/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import SocketControl.Server;
import functionality.Map;
import functionality.Player;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class PlayerThread implements Runnable{
    private Thread t = new Thread(this);
    private Server server;
    private Socket sock;

    public PlayerThread(Server s, Socket sock){
        server = s;
        this.sock = sock; 
    }
    @Override
    public void run() {
        try {
           // Creating input and output stream
            OutputStream outToServer = sock.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            InputStream inFromServer = sock.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            
            while (true) {
                //1) Read UTF messages
                int message = Integer.parseInt(in.readUTF());

                //2) Processing messages (update map,...)
                if (message == 10) {
                    // read player name from client
                    String playerName = in.readUTF();
                    server.GetMap().SetTankOnMap(15, playerName);
                    
                    Integer index = server.GetMap().GetLastAddedPlayerIndex();
                    out.writeUTF(index.toString());
                }
                
                // when the player moves their tank
                // message 1 : move north
                else if (message == 1) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.MoveNorth(server.GetMap().GetMatrix());
                }
                // message 2: move south
                else if (message == 2) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.MoveSouth(server.GetMap().GetMatrix());
                }
                // message 3 : move east
                else if (message == 3) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.MoveEast(server.GetMap().GetMatrix());
                }
                // message 4: move west
                else if (message == 4) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.MoveWest(server.GetMap().GetMatrix());
                }
                
                //3) Broadcast (sending message to each PC by loop)
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        t.start();
    }
}
