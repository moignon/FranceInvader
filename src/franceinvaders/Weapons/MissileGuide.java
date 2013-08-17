/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.ImageBank;
import Framework.Sprite;
import Framework.SpriteAnime;
import Math2d.Vector;
import franceinvaders.Constantes;
import franceinvaders.Level1;
import franceinvaders.Mobs.Mob;
import franceinvaders.Mobs.Psy;
import franceinvaders.Mobs.VagueAsteroid;
import franceinvaders.Player;
import franceinvaders.ProjectilesEntites.Projectile;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class MissileGuide implements Weapon{
    
    Projectile p;
    Sprite s;
    double missileSpeed = 10;
    Entite targett;
    double rayonRecherche = 500000;
    private long dernierTir;
    private long delai = 50;
    
    public MissileGuide (){
       p = null;
       s = new SpriteAnime(ImageBank.get().getImages(Constantes.missileRef, 1, 1), 0);
    }

    @Override
    public void Fire(Entite e) {
        if (e.getPanel().getCurrentTime() - dernierTir < delai)
            return;
        dernierTir = e.getPanel().getCurrentTime();
        targett = this.findTarget(e.getPanel());
        p = new Projectile(s, e.getPanel()){
            Entite target = targett ;
            @Override
            public void trollNoobs(){
                if (target != null){
                    if (target.isActive()){
                        this.setYspeed(-missileSpeed);
                        this.setXspeed(0);
                        double angle = getPosVector().orientToVector(target.getPosVector())*101/100;
                        this.setAngle(angle);
                        getSpeedVector().rotate(Math.PI + this.getAngle());
                    }
                    else
                        target = null;
                }
                super.trollNoobs();
                if (!this.isActive()){
                    if (this.target != null)
                        ((Mob)target).isTarget = false;
                }
            }
            @Override 
            public void blit(Graphics2D gBuffer){
                
                super.blit(gBuffer);
                Color c = gBuffer.getColor();
                gBuffer.setColor(Color.ORANGE);
                if (target != null){
                    int xx = (int) (target.getX() - target.getL()/2);
                    int yy = (int) (target.getY() - target.getH()/2);
                    gBuffer.drawOval(xx, yy, target.getL(), target.getH());
                    gBuffer.drawString("LOCK !",(int) target.getX(), (int)target.getY());
                    }
                if (getPanel().getDevMode()){
                    double x = this.getPanel().getMouseVector().getX() - rayonRecherche;
                    double y = this.getPanel().getMouseVector().getY() - rayonRecherche;
                    gBuffer.drawOval((int)x, (int)y, (int)(2*rayonRecherche), (int)(2*rayonRecherche));
                    
                }
                gBuffer.setColor(c);
            }
        };
        p.setHitDmg(10);
        p.setYspeed(-missileSpeed);
        p.setPosRelativeTo(0, 0, e, true);
        e.getPanel().getListEntite().add(p);
    }
    
    private Entite findTarget(GamePanel panel) {
        
        Vector mousPos = panel.getMouseVector();
        double x = mousPos.getX();
        double y = mousPos.getY();
        ArrayList <Entite> liste;
        liste = panel.getListEntite();
        double best = rayonRecherche;
        Entite candidat = null;
        
        for (int i = 0; i < liste.size(); i ++){
            Entite e = liste.get(i);
            if (e instanceof Mob && !(e instanceof VagueAsteroid)){
                if(((Mob)e).isTarget == false){
                    double dist = new Vector (x -e.getPosVector().getX(),y - e.getPosVector().getY()).module();
                    if (dist < rayonRecherche && dist <= best){
                        best = dist;
                        candidat = e ;
                    }
                }
            }
        }
        if (candidat != null){
            ((Mob)candidat).isTarget = true;
        }
        return candidat;
        
    }
    

    @Override
    public void update() {
    }
    
}
