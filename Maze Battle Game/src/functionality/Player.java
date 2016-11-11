/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class Player {
    private int OrderNum;
    private String Name;
    private String Direction;
    private Point pos;
    private JLabel photo;
    private int score;
    private int HealthPoint;
    private Bomb ammunition;
    
    public Player(String Name, String Direction, Point p, JLabel photo, int score, Bomb ammunition) {
        this.Name = Name;
        this.Direction = Direction;
        pos = (Point)p.clone();
        this.photo = photo;
        this.score = score;
        this.ammunition = ammunition;
        HealthPoint = 100;
    }
    
    public int GetOrder() {
        return this.OrderNum;
    }
     public String getName() {
        return Name;
    }

    public String getDirection() {
        return Direction;
    }

    public Point getPos() {
        return pos;
    }

    public JLabel getPhoto() {
        return photo;
    }

    public int getScore() {
        return score;
    }

    public Bomb getAmmunition() {
        return ammunition;
    }
    
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDirection(String Direction) {
        this.Direction = Direction;
    }

    public void setxPos(Point p) {
        this.pos = p;
    }
    
    public void setPhoto(JLabel photo) {
        this.photo = photo;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAmmunition(Bomb ammunition) {
        this.ammunition = ammunition;
    }
    
    public void setOrderNum(int OrderNum) {
        this.OrderNum = OrderNum;
    }
    
    public void MoveWest(Unit[][] matrix) {
        if (pos.y == 0)
            return;
        if (matrix[pos.x][pos.y-1].GetObjectType() != 0)
            return;
        matrix[pos.x][pos.y].SetObjectType(0);
        pos.y --;
        matrix[pos.x][pos.y].SetObjectType(-1);
        photo.setBounds(pos.y * 15, pos.x * 15, 15, 15);
    }
    
    public void MoveEast(Unit[][] matrix) {
        if (pos.y == 35)
            return;
        if (matrix[pos.x][pos.y + 1].GetObjectType() != 0)
            return;
        matrix[pos.x][pos.y].SetObjectType(0);
        pos.y ++;
        matrix[pos.x][pos.y].SetObjectType(-1);
        photo.setBounds(pos.y * 15, pos.x * 15, 15, 15);
    }
    
    public void MoveSouth(Unit[][] matrix) {
        if (pos.x == 11)
            return;
        if (matrix[pos.x + 1][pos.y].GetObjectType() != 0)
            return;
        matrix[pos.x][pos.y].SetObjectType(0);
        pos.x ++;
        matrix[pos.x][pos.y].SetObjectType(-1);
        photo.setBounds(pos.y * 15, pos.x * 15, 15, 15);
    }
    
    public void MoveNorth(Unit[][] matrix) {
        if (pos.x == 0)
            return;
        if (matrix[pos.x-1][pos.y].GetObjectType() != 0)
            return;
        matrix[pos.x][pos.y].SetObjectType(0);
        pos.x --;
        matrix[pos.x][pos.y].SetObjectType(-1);
        photo.setBounds(pos.y * 15, pos.x * 15, 15, 15);
    }
    
    public void TurnRight(){
        if (this.Direction.equals("NORTH")) {
            this.setDirection("EAST");
            this.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-east.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (this.getDirection().equals("EAST")) {
            this.setDirection("SOUTH");
            this.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-south.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (this.getDirection().equals("SOUTH")) {
            this.setDirection("WEST");
            this.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-west.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (this.getDirection().equals("WEST")) {
            this.setDirection("NORTH");
            this.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-north.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        }
    }
    
    public void TurnLeft() {
        Player p = this;
        if (p.getDirection().equals("NORTH")) {
            p.setDirection("WEST");
            p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-west.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (p.getDirection().equals("EAST")) {
            p.setDirection("NORTH");
            p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-north.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (p.getDirection().equals("SOUTH")) {
            p.setDirection("EAST");
            p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-east.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        } else if (p.getDirection().equals("WEST")) {
            p.setDirection("SOUTH");
            p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-south.png").getImage()
                    .getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        }
    }
    
    public void TurnBack() {
        Player p = this;
        if (p.getDirection().equals("NORTH")) {
                p.setDirection("SOUTH");
                p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-south.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            }
            else if (p.getDirection().equals("EAST")) {
                p.setDirection("WEST");
                p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-west.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            }
            else if (p.getDirection().equals("SOUTH")) {
                p.setDirection("NORTH");
                p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-north.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            }
            else if (p.getDirection().equals("WEST")) {
                p.setDirection("EAST");
                p.getPhoto().setIcon(new ImageIcon(new ImageIcon("images/tank-east.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
            }
    }
}
