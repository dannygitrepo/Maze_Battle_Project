/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import java.awt.Image;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class Map {
    private Unit[][] matrix;
    private Vector<Player> warriors;
    
    public Map(int w, int h) {
        matrix = new Unit[h][w];
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                matrix[i][j] = new Unit();
            }
        }
    }
    public void SetUnitType(JPanel map, int x, int y, int type) {
        matrix[x][y].SetObjectType(type);
        
        if (type == 0)
            matrix[x][y].GetLabel().setIcon(new ImageIcon(new ImageIcon("images/path.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        else
            matrix[x][y].GetLabel().setIcon(new ImageIcon(new ImageIcon("images/home.png").getImage()
						.getScaledInstance(15, 15, Image.SCALE_AREA_AVERAGING)));
        map.add(matrix[x][y].GetLabel());
    }
    
    public void SetBoundsLabel(int x, int y, int size) {
        matrix[x][y].GetLabel().setBounds(x * size, y * size, size, size);
    }
}
