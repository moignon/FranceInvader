/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import Framework.ImageBank;
import franceinvaders.Constantes;

/**
 *
 * @author John
 */
public class Psy extends Mob {
    
    
    
    public Psy (GamePanel panel){
        super( new Sprite(ImageBank.get().getImages(Constantes.psyRef)), panel, 10, 400);
    }

    @Override
    public void codeMe() {
    }

    @Override
    public void collided(Entite s, Collision c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
