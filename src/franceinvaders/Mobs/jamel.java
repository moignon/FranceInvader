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
        this.setPostion(0-sprite.getWidth(), 0-sprite.getWidth());
        this.setXspeed((int) (Math.random()*10));
        this.setYspeed((int)(Math.random()*10));
        panel.add(this);
    }

    @Override
    public void codeMe() {
        if (this.isOutOfScreen()) this.getPanel().remove(this);
    }
    
}
