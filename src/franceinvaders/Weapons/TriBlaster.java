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
        tirs[0].setPosition(e.getX(), e.getY() - e.getH()*3/4);
        tirs[0].setAngle(e.getAngle());
        tirs[0].getSpeedVector().setXY(0, 30);
        tirs[0].getSpeedVector().rotate(tirs[0].getAngle());
        
        tirs[1] = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tirs[1].setPosition(e.getX()+30, e.getY()+30 - e.getH()*3/4);
        tirs[1].setAngle(e.getAngle());
        tirs[1].getSpeedVector().setXY(0, 30);
        tirs[1].getSpeedVector().rotate(tirs[0].getAngle());
        
        tirs[2] = new Flamme(new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 ), e.getPanel());
        tirs[2].setPosition(e.getX()-30, e.getY()+30 - e.getH()*3/4);
        tirs[2].setAngle(e.getAngle());
        tirs[2].getSpeedVector().setXY(0, 30);
        tirs[2].getSpeedVector().rotate(tirs[0].getAngle());
        
        for (int i =0; i < tirs.length; i ++){
            e.getPanel().add(tirs[i]);
        }
        
    }
    
}
