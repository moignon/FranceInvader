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
public class TriBlaster implements Weapon {
    
    int delai = 100; // en milliSec
    long dernierTir;

    @Override
    public void Fire(Entite e) {
        if (System.currentTimeMillis() - dernierTir < delai)
            return;
        double x = e.getX(),
               y = e.getY();
        dernierTir = System.currentTimeMillis();
        Projectile[] tirs = new Projectile[3];
        tirs[0] = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tirs[0].setCenteredPostion(e.getX()+ e.getL()/2, e.getY());
        tirs[0].setXspeed(0);
        tirs[0].setYspeed(-25);
        
        tirs[1] = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tirs[1].setCenteredPostion(e.getX()+30+ e.getL()/2, e.getY()+30);
        tirs[1].setXspeed(2);
        tirs[1].setYspeed(-25);
        
        tirs[2] = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tirs[2].setCenteredPostion(e.getX()-30+ e.getL()/2, e.getY()+30);
        tirs[2].setXspeed(-2);
        tirs[2].setYspeed(-25);
        
        for (int i =0; i < tirs.length; i ++){
            e.getPanel().add(tirs[i]);
        }
        
    }
    
}
