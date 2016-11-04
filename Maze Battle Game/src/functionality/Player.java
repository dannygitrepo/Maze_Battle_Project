/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import java.awt.Point;
import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class Player {
    private String Name;
    private String Direction;
    private Point pos;
    private JLabel photo;
    private int score;
    private Bomb ammunition;
    
    public Player(String Name, String Direction, Point p, JLabel photo, int score, Bomb ammunition) {
        this.Name = Name;
        this.Direction = Direction;
        pos = (Point)p.clone();
        this.photo = photo;
        this.score = score;
        this.ammunition = ammunition;
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
}
