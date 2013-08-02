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
        super(new Sprite(ImageBank.get().getImages(Constantes.jamelRef)[0][0]),panel, 4000,200);
    }

    @Override
    public void codeMe() {
    }
    
}
