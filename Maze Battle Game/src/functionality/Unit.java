/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionality;

import javax.swing.JLabel;

/**
 *
 * @author admin
 */
public class Unit {
    private JLabel label;
    private int ObjectType;
    
    public Unit() {
        label = new JLabel();
        ObjectType = 0;
    }
    
    public void SetObjectType(int type) {
        ObjectType = type;
    }
    
    public void SetLabel() {
        
    }
    
    public JLabel GetLabel() {
        return label;
    }
    
    public int GetObjectType() {
        return ObjectType;
    }
}
