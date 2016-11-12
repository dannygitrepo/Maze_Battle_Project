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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class GettingMessageFromServerThread extends Thread{
    private Socket socket;
    private ObjectInputStream ois;

    private ObjectOutputStream oos;
    private Player p;
    
    private JPanel MapPanel;
    private LinkedList<Player> PlayerList;
    private DefaultTableModel model;
    public GettingMessageFromServerThread(Socket sock, Player player, ObjectOutputStream out, ObjectInputStream in, LinkedList Players, JPanel jmap, JTable PlayerTable) throws IOException {
        socket = sock;
        this.oos = out;
        this.ois = in;
        //ois = new ObjectInputStream(sock.getInputStream());
        //oos = new ObjectOutputStream(sock.getOutputStream());
        p = player;
        MapPanel = jmap;
        PlayerList = Players;
        model = (DefaultTableModel) PlayerTable.getModel();
    }
    public void run(){
        try {
            while (true) {
                // catch message
                Integer message = (Integer)ois.readObject(); /*sai 3: kg the cast tu integer sang string.*/
                System.out.println("-----------" + message + "----------");
                //Integer message = Integer.parseInt(m);
                
                // a new player comes in, and you must add him
                if (message == 10) {
                    Player NewPlayer = (Player)ois.readObject();
                    PlayerList.add(NewPlayer.GetOrder(), NewPlayer);
                    model.addRow(new Object[]{NewPlayer.getName(), "0", "100"});
                    MapPanel.add(NewPlayer.getPhoto());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GettingMessageFromServerThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GettingMessageFromServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
