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
        Flamme f;
        f = new Flamme(s, tireur.getPanel());
        f.setYspeed(-projectileSpeed);
        f.setPosRelativeTo(0, tireur.getH()*3/4, tireur, true);
        f.audio.setGain(-20);
        tirs[0] = f;
        
        f = new Flamme(s, tireur.getPanel());
        f.setYspeed(-projectileSpeed);
        f.setXspeed(-1);
        f.setPosRelativeTo(30, 30, tireur, true);
        f.audio.setGain(-20);
        tirs[1] = f;
        
        f = new Flamme(s, tireur.getPanel());
        f.setYspeed(-projectileSpeed);
        f.setXspeed(1);
        f.setPosRelativeTo(-30, 30, tireur, true);
        f.audio.setGain(-20);
        tirs[2] = f;
        
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