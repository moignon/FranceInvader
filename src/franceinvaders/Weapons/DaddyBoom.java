/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Animations.Papa;
import Framework.Entite;

/**
 *
 * @author John
 */
public class DaddyBoom implements Weapon {
    
    int nbMaxBomb = 4 ;
    int nbBomb = 4;
    Papa animation;
    
    @Override
    public void Fire(Entite e) {
        if ( !e.getPanel().getListEntite().contains(animation) && nbBomb > 0){
            animation = null;
            animation = new Papa(e.getPanel());
            e.getPanel().add(animation);
            nbBomb--;
        }
        
    }
    
}
