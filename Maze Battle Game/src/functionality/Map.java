/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class Map {
    private Unit[][] matrix;
    private Vector<Player> warriors;
    private Vector<Point> PathPoint;
    private JPanel MapPanel;
    
    public Map(int w, int h, JPanel panel) {
        matrix = new Unit[h][w];
        PathPoint = new Vector<>();
        MapPanel = panel;
        warriors = new Vector<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = new Unit();
            }
        }
    }
    
    /**
     * 
     * @param map
     * @param x
     * @param y
     * @param type 
     */
    public void SetUnitType(int x, int y, int type) {
        matrix[x][y].SetObjectType(type);
        matrix[x][y].GetLabel().setBorder(BorderFactory.createLineBorder(Color.BLUE));
        if (type == 0) {
           // matrix[x][y].GetLabel().setIcon(new ImageIcon(new ImageIcon("images/path.png").getImage()
	//					.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            Point p = new Point(x,y);
            PathPoint.addElement(p);
        }
        else
            matrix[x][y].GetLabel().setIcon(new ImageIcon(new ImageIcon("images/home.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        MapPanel.add(matrix[x][y].GetLabel());
    }
    
    /**
     * 
     * @param x
     * @param y
     * @param size 
     */
    public void SetBoundsLabel(int x, int y, int size) {
        matrix[x][y].GetLabel().setBounds(y * size, x * size, size, size);
    }
    
    /**
     * 
     * @param map
     * @param TankSize
     * @param PlayerName
     * @return 
     */
    public Point SetTankOnMap(int TankSize, String PlayerName){
        Random r1 = new Random();
        Point p = PathPoint.elementAt(r1.nextInt((PathPoint.size() - 1 - 0) + 1) + 0);
        
        // set the position where the tank locates to -1
        matrix[p.x][p.y].SetObjectType(-1);
        
        System.out.println(p.x + " " + p.y);
        Random r2 = new Random();
        int direction = r2.nextInt((4 - 1) + 1) + 1;
        System.out.println(direction);
        
        
        // tank - north direction
        if (direction == 1) {
            Player tank = new Player(PlayerName,"NORTH",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-north.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            MapPanel.add(tank.getPhoto());
            
            this.AddTankToWarriors(tank);
            tank.setOrderNum(warriors.size() - 1);
        }
        // tank - south direction
        else if (direction == 2) {
            Player tank = new Player(PlayerName,"SOUTH",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-south.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            MapPanel.add(tank.getPhoto());
            this.AddTankToWarriors(tank);
            tank.setOrderNum(warriors.size() - 1);
        }
        
        // tank - south west
        else if (direction == 3) {
            Player tank = new Player(PlayerName,"WEST",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-west.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            MapPanel.add(tank.getPhoto());
            this.AddTankToWarriors(tank);
            tank.setOrderNum(warriors.size() - 1);
        }
        
        // tank - east direction
        else{
            Player tank = new Player(PlayerName,"EAST",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-east.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            MapPanel.add(tank.getPhoto());
            this.AddTankToWarriors(tank);
            tank.setOrderNum(warriors.size() - 1);
        }
        return p;
    }
    
   /**
    * Insert tank to warriors vector
    * @param tank 
    */
    private void AddTankToWarriors(Player tank) {
        this.warriors.addElement(tank);
    }
    
    /**
     * 
     * @param i
     * @return 
     */
    public Player GetPlayer(int i){
        return this.warriors.elementAt(i);
    }
    
    /**
     * Get Last Added Player Index
     * @return 
     */
    public int GetLastAddedPlayerIndex() {
        return warriors.size() - 1;
    }
    
    /**
     * Get matrix unit
     * @return 
     */
    public Unit[][] GetMatrix() {
        return this.matrix;
    }
    
    public static void main(String args[]) {

//khai bao va khoi tao mang 2 chieu 
        int arr[][] = {{1, 2, 3}, {2, 4, 5}, {4, 4, 5}};

//in mang hai chieu  
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
