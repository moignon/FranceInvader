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
            if (e instanceof VagueAsteroid) {
                if (e.collidesWith(this)){
                    ((VagueAsteroid)e).takeDmg(this.getHitDmg());
                    this.panel.getListEntite().remove(this);
                    Explosion exp = new Explosion(this.panel);
                    exp.setCenteredPostion(x, y);
                    this.panel.getListEntite().add(exp);
                }
                
            }
        }
    }
    

    
    public abstract int getHitDmg();
                        
    
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


}
