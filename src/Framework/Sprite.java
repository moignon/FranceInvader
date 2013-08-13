
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

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
    public void setImage (BufferedImage image){
        this.image = image;
    }
    public int getWidth(){
        return getImage().getWidth();
    }
    public int getHeight(){
        return getImage().getHeight();
    }
    public void draw (Graphics2D g,double _x, double _y){
        g.drawImage(getImage(), (int)_x , (int)_y , null);
    }
    
    public void drawRotate(Graphics2D g,double _x, double _y, double angle){
        g.rotate(angle, _x , _y);
        g.drawImage(getImage(), (int)_x , (int)_y , null);
        g.rotate(-angle, _x , _y);
    }

}
