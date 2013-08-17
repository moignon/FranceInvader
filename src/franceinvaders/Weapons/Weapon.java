/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;

/**
 *
 * @author John
 */
public interface Weapon {
    
    public void Fire (Entite e);
        
    public void update();
}
