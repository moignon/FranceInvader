/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;
import Framework.ImageBank;
import Framework.SpriteAnime;
import franceinvaders.Constantes;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;

/**
 *
 * @author John
 */
public class TirSimple implements Weapon {
    
    int delai = 700; // en milliSec
     
    long dernierTir;
    
    @Override
    public void Fire(Entite e) {
        if (System.currentTimeMillis() - dernierTir < delai)
            return;
        double x = e.getX(),
               y = e.getY();
        dernierTir = System.currentTimeMillis();
        Projectile tir;
        tir = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tir.setPosition((int)e.getX(), (int)e.getY());
        tir.setAngle(e.getAngle());
        tir.getSpeedVector().setXY(0, 30);
        tir.getSpeedVector().rotate(tir.getAngle());
        e.getPanel().add(tir);
    }

    @Override
    public void update() {
    }
    
}
