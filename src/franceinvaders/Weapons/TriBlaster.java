/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;
import Framework.ImageBank;
import Framework.Sprite;
import Framework.SpriteAnime;
import Math2d.Vector;
import franceinvaders.Constantes;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;

/**
 *
 * @author John
 */
public class TriBlaster implements Weapon {
    
    int delai = 40; // en milliSec
    long dernierTir;
    double projectileSpeed = 25;
    int projectileDmg = 10;
    Sprite s = new SpriteAnime(ImageBank.get().getImages(Constantes.flammeRef, 1, 4),60 );

    @Override
    public void Fire(Entite tireur) {
        if (tireur.getPanel().getCurrentTime() - dernierTir < delai)
            return;
        
        dernierTir = tireur.getPanel().getCurrentTime();
        Projectile[] tirs = new Projectile[3];
        tirs[0] = new Flamme(s, tireur.getPanel());
        tirs[0].setYspeed(-projectileSpeed);
        tirs[0].setPosRelativeTo(0, tireur.getH()*3/4, tireur, true);
        
        tirs[1] = new Flamme(s, tireur.getPanel());
        tirs[1].setYspeed(-projectileSpeed);
        tirs[1].setXspeed(-1);
        tirs[1].setPosRelativeTo(30, 30, tireur, true);
        
        tirs[2] = new Flamme(s, tireur.getPanel());
        tirs[2].setYspeed(-projectileSpeed);
        tirs[2].setXspeed(1);
        tirs[2].setPosRelativeTo(-30, 30, tireur, true);
        
        for (int i =0; i < tirs.length; i ++){
            tirs[i].setHitDmg(projectileDmg);
            tireur.getPanel().add(tirs[i]);
        }
        
    }
    @Override
    public void update() {
    }
    
    public void setHitDmg (int param){
        this.projectileDmg = param;
    }
    
}