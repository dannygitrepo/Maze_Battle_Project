/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import Interface.MainInterface;
import SocketControl.Server;
import functionality.Map;
import functionality.Player;
import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private Integer index;
    
    public PlayerThread(Server s, Socket sock){
        server = s;
        this.sock = sock; 
    }
    @Override
    public void run() {
        try {
           // Creating input and output stream
            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
            
            while (true) {
                //1) Read UTF messages
                Integer message = Integer.parseInt((String)in.readObject());
                System.out.println(message);
                
                //2) Processing messages (update map,...)
                // message 10 : a client enter
                if (message == 10) {
                    // read player name from client
                    String playerName = (String)in.readObject();
                    System.out.println(playerName);
                    server.GetMap().SetTankOnMap(15, playerName);
                    
                    // create ID for client (player) and send it to client
                    index = server.GetMap().GetLastAddedPlayerIndex();
                    out.writeObject(index); //(1)
                    for (int i = 0; i <= index; i++) {
                        System.out.println(i + server.GetTank(i).getName());
                    }
                    
                    // send all other clients to new player
                    for (int j = 0; j < index; j++) {
                        out.writeObject(server.GetTank(j)); //(2)
                    }
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
                
                // when a player change their direction
                // message : turn back
                else if (message == 5) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.TurnBack();
                }
                // message : turn right
                else if (message == 6) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.TurnRight();
                }
                // message : turn left
                else if (message == 7) {
                    Player p = server.GetTank(Integer.parseInt(in.readUTF()));
                    p.TurnLeft();
                }
                
                //3) Broadcast (sending message to each PC by loop)
                for (int j = 0; j < index; j++) {
                    ObjectOutputStream oos = new ObjectOutputStream(server.GetSockets().elementAt(j).getOutputStream());
                    oos.writeObject(10);
                    oos.writeObject(server.GetTank(index));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void start() {
        t.run();
    }
    
    public static void main(String args[]) throws IOException {
    }
}
