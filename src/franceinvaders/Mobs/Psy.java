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
public class Psy extends Mob {
    
    
    
    public Psy (GamePanel panel){
        super(SpriteBank.get().getSprite(Constantes.psyRef),panel, 10,400);
    }

    @Override
    public void codeMe() {
    }
    
}
