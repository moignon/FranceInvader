/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.GamePanel;
import Framework.Sprite;
import Framework.SpriteBank;
import franceinvaders.Constantes;

/**
 *
 * @author John
 */
public class jamel extends Mob {
    
    
    public jamel (GamePanel panel){
        super(SpriteBank.get().getSprite(Constantes.jamelRef),panel, 4000,200);
    }

    @Override
    public void codeMe() {
    }
    
}
