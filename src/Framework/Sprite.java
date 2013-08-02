/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// NOOB
//C'est toi le NOOB
//JOHN C'EST LE BEST !!!!!!!!!!!
/**
 *
 * @author John
 */
public class Sprite {
    
    private BufferedImage image;
    
    public Sprite (BufferedImage image) {
        this.image = image;
    }
    public void updateAnim(){}
    
    public BufferedImage getImage(){
        return image;
    } 
    public int getWidth(){
        return getImage().getWidth();
    }
    public int getHeight(){
        return getImage().getHeight();
    }
    public void draw (Graphics g,double _x, double _y){
        g.drawImage(getImage(), (int)_x , (int)_y , null);
    }

}
