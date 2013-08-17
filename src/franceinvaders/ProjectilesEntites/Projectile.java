/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.ProjectilesEntites;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import Math2d.Vector;
import franceinvaders.Mobs.Mob;
import franceinvaders.Mobs.VagueAsteroid;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class Projectile extends Entite{
    
    private int hitDmg = 0;
    
    public Projectile(Sprite sprite, GamePanel panel){
        super(sprite,panel);
     }
    
    @Override
    public void trollNoobs(){
        
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
    
    public int getHitDmg() {
        return hitDmg;
    }
    public void setHitDmg(int param) {
        hitDmg = param;
    }

    @Override
    public void collided(Entite s, Collision c) {
    }
    
                        
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


}
