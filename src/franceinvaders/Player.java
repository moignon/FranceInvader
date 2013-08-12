/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import franceinvaders.Weapons.Weapon;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;
import java.util.ArrayList;

/**
 *
 * @author John
 */
class Player extends Entite {

    public Player(Sprite sprite,GamePanel panel) {
        super(sprite, panel);
        this.angle = 0;
    }
    
    public void getInput()
    {
        
    }
    
    public void fire (Weapon arme){
        arme.Fire(this);
    }

    
    
    @Override
    public void codeMe() {
    }
    
}
