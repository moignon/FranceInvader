/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.ProjectilesEntites;

import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import franceinvaders.Mobs.Mob;
import franceinvaders.Mobs.VagueAsteroid;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public abstract class Projectile extends Entite{
    
    public Projectile(Sprite sprite, GamePanel panel){
        super(sprite,panel);
     }
    
    @Override
    public void codeMe(){
        if (isOutOfScreen()){
            panel.remove(this);
        }
        for (int i = 0; i < this.panel.getListEntite().size(); i++){
            Entite e = this.panel.getListEntite().get(i);
            if (e instanceof Mob) {
                if (e.collidesWith(this)){
                    ((Mob)e).takeDmg(this.getHitDmg());
                    this.panel.getListEntite().remove(this);
                }
                
            }
        }
    }
    

    
    public abstract int getHitDmg();
                        
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


}
