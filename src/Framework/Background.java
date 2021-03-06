/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author François
 */

public class Background {
    private Sprite sprite;   
    private Graphics2D g2d;
    private int position = 0;
    private int speed = 10;
    
    public Background(String ref){
        sprite = new Sprite(ImageBank.get().getImages(ref));   
    }

    public void blit(Graphics2D gBuffer, Camera cam) {
        sprite.draw(gBuffer, 0+cam.getCorrectionX(), position+cam.getCorrectionY());
        sprite.draw(gBuffer, 0+cam.getCorrectionX(),position - sprite.getHeight()+cam.getCorrectionY());
    }
    
    public void blit(Graphics2D gBuffer) {
        sprite.draw(gBuffer, 0, position);
        sprite.draw(gBuffer, 0,position - sprite.getHeight());
    }
    

    public void update() {
        position = (position + speed) % sprite.getHeight();
    }
   
    public void setSpeed(int value) {
        if (value < 0){
            speed = 0;
        }
        else{
            speed = value;
        }
    }

}