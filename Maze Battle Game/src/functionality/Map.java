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
    public Map(int w, int h) {
        matrix = new Unit[h][w];
        PathPoint = new Vector<>();
        warriors = new Vector<>();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = new Unit();
            }
        }
    }
    
    public void SetUnitType(JPanel map, int x, int y, int type) {
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
        map.add(matrix[x][y].GetLabel());
    }
    
    public void SetBoundsLabel(int x, int y, int size) {
        matrix[x][y].GetLabel().setBounds(y * size, x * size, size, size);
    }
    
    public Point SetTankOnMap(JPanel map, int TankSize){
        Random r1 = new Random();
        Point p = PathPoint.elementAt(r1.nextInt((PathPoint.size() - 1 - 0) + 1) + 0);
        matrix[p.x][p.y].SetObjectType(-1);
        
        System.out.println(p.x + " " + p.y);
        Random r2 = new Random();
        int direction = r2.nextInt((4 - 1) + 1) + 1;
        System.out.println(direction);
        
        if (direction == 1) {
            Player tank = new Player("","NORTH",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-north.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            map.add(tank.getPhoto());
        }
        else if (direction == 2) {
            Player tank = new Player("","SOUTH",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-south.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            map.add(tank.getPhoto());
        }
        else if (direction == 3) {
            Player tank = new Player("","WEST",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-west.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            map.add(tank.getPhoto());
        }
        else{
            Player tank = new Player("","EAST",p,new JLabel(), 0, new Bomb());
            tank.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-east.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            tank.getPhoto().setBounds(p.y * TankSize, p.x * TankSize, TankSize, TankSize);
            map.add(tank.getPhoto());
        }
        return p;
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
