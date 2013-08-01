/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;
import Framework.SpriteBank;
import franceinvaders.Constantes;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;

/**
 *
 * @author John
 */
public class TirSimple implements Weapon {
    
//    int delai = 700; // en milliSec
     int delai = 400;
    long dernierTir;
    
    @Override
    public void Fire(Entite e) {
        if (System.currentTimeMillis() - dernierTir < delai)
            return;
        double x = e.getX(),
               y = e.getY();
        dernierTir = System.currentTimeMillis();
        Projectile tir;
        tir= new Flamme(SpriteBank.get().getSprite(Constantes.flammeRef,1,4,60*1000000), e.getPanel());
        tir.setCenteredPostion(e.getX()+ e.getL()/2, e.getY());
        tir.setYspeed(0);
        tir.setYspeed(-10);
        e.getPanel().add(tir);
    }
    
}
