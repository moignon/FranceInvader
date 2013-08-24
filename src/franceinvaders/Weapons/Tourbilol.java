/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;
import Framework.ImageBank;
import Framework.Sprite;
import Framework.SpriteAnime;
import franceinvaders.Constantes;

/**
 *
 * @author John
 */
public class Tourbilol implements Weapon{
    
    TriBlaster tir = new TriBlaster();
    double angle;
    long duréeTir = 300;
    long deltaDuréeTir = 0;
    long delai = 2000;
    long deltaDelai = delai ;
    Entite tireur;
    private boolean init;
    
    public Tourbilol (Entite e){
        tireur = e;
        tir.projectileSpeed = 15;
        tir.delai = 10;
        tir.setHitDmg(5);
        tir.s = new Sprite(ImageBank.get().getImages(Constantes.shurikenRef));
    }   
    
    @Override
    public void Fire(Entite e) {
    tireur = e;
    
    if (!init && deltaDelai >= delai ){
        angle = e.getAngle();
        init = true;
        }
    }

    @Override
    public void update() {
        
        if (init){
            try{((SpriteAnime)tireur.getSprite()).setAnim(1);
            }
            catch (Exception ignored){}
           angle = angle + (Math.PI*0.1);
           tireur.setAngle(angle);
           tir.Fire(tireur); 
           if (deltaDuréeTir >= duréeTir){
               init = false;
               deltaDuréeTir = 0 ;
               deltaDelai = 0;
           }
           if(init)
               deltaDuréeTir = deltaDuréeTir + tireur.getPanel().getdTime();
        }
        if(deltaDelai < delai){
            deltaDelai = deltaDelai + tireur.getPanel().getdTime(); 
        }
    }
}
