/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.GamePanel;
import Framework.Sprite;
import Framework.ImageBank;
import franceinvaders.Constantes;

/**
 *
 * @author John
 */
public class jamel extends Mob {
    
    
    public jamel (GamePanel panel){
        super(new Sprite(ImageBank.get().getImages(Constantes.jamelRef)),panel, 40,1000);
        this.setPosition(0-sprite.getWidth(), 0-sprite.getWidth());
        this.setPosition(Math.random()*panel.getWIDTH() % panel.getWIDTH() - this.getL() / 2, 0 - this.getH()/2 + 1);
        this.setXspeed((int) (Math.random()*10) - 5);
        //this.setXspeed(1);
        this.setYspeed((int)(Math.random()*10));
        panel.add(this);
    }

    @Override
    public void codeMe() {
        
        if (this.isOutOfScreen()) {
            this.getPanel().remove(this);
        }
    }
    
}
