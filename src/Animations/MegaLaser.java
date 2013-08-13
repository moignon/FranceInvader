/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import Framework.CollisionBox;
import Framework.Entite;
import Framework.GamePanel;
import Framework.ImageBank;
import Framework.Sprite;
import franceinvaders.Constantes;
import franceinvaders.Mobs.Mob;
import franceinvaders.Mobs.VagueAsteroid;
import franceinvaders.ProjectilesEntites.Explosion;
import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


/**
 *
 * @author John
 */
public class MegaLaser extends Entite {
    private int xPos;
    private int yPos;
    private int xBase;
    private int yBase;
    private boolean needInit = true;
    private boolean done;
    
    public MegaLaser (GamePanel panel,Papa p){
        super( new LaserSprite(),panel);
        this.done = false;
        xBase = ((LaserSprite)sprite).largeur;
        yBase = ((LaserSprite)sprite).hauteur;
        xPos = 900 +  xBase*8/10;
        yPos = (int)p.getY()-50;
        this.setPosition(xPos, yPos);

        panel.add(this);
        ((LaserSprite)this.sprite).init(this);
    }
    
    @Override
    public void blit(Graphics2D gBuffer) {
        super.blit(gBuffer);
        LaserSprite s = (LaserSprite) this.sprite;
    }

    @Override
    public void codeMe() {
        
        for (int i = 0; i < this.panel.getListEntite().size(); i++){
            Entite e = this.panel.getListEntite().get(i);
            if (this.getCollisionBox().getHeight() < this.getPanel().getHEIGHT()){
             if (e instanceof Mob) {
                
                 if (e.collidesWith(this)){
                     int dmg = ((Mob)e).getLifeMax();
                    ((Mob)e).takeDmg(dmg);
                   }
             }
          }
        }
        
        
        LaserSprite s = (LaserSprite) this.sprite;
        if (s.largeur > 1000 && s.hauteur > 600){
            if (needInit){
               xBase = ((LaserSprite)sprite).largeur;
               yBase = ((LaserSprite)sprite).hauteur;
               xPos = (int) this.getX();
               yPos = (int) this.getY(); 
               needInit = false;
            }
            this.setPosition(xPos+xBase/2, yPos+yBase/2);
            
            s.opacity = s.opacity - 0.03f;
            if (s.opacity < 0) {
                s.opacity = 0;
                this.getPanel().remove(this);
                this.done = true;
            }
        }
        else
            this.setPosition( xBase + xPos - s.largeur, yBase + yPos - s.hauteur/2);
    }

    boolean isDone() {
        return done;
    }
    
   
    @Override
    public CollisionBox getCollisionBox (){
        CollisionBox col;
        LaserSprite s = (LaserSprite) this.sprite;
        col = new CollisionBox();
        col.setBounds(0, (int)this.getY() - s.hauteur * 1/4, panel.getWIDTH(), s.hauteur * 2/4);
        
       return col;
    }
    
}
class LaserSprite extends Sprite {
    
    long firstTime, currentTime, dTime;
    int largeur, hauteur;
    float opacity = 1f;
    MegaLaser l;
    
    LaserSprite (){
        super(ImageBank.get().getImages(Constantes.megaLaserRef));
        largeur = super.getImage().getWidth()/10;
        hauteur = super.getImage().getHeight();
        firstTime = System.currentTimeMillis();
        currentTime = firstTime;
        dTime = 0;
    }
    public void init(MegaLaser l){
        this.l = l;
    }
    @Override
    public void updateAnim(){
        if (largeur <1000 && largeur < 600){
            if (largeur < 1200 )
                largeur += 10;
            if (hauteur < 900)
                hauteur += 20;
        }
        else{
            largeur +=40;
            hauteur += 80;
        }
            
     
        
       
        
        
    }
        
    @Override
    public void draw (Graphics2D g,double _x, double _y){
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g.drawImage(getImage(), (int)_x , (int)_y ,largeur, hauteur, null);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));     
    }
    
    public int getWidth(){
        return largeur;
    }
    public int getHeight(){
        return hauteur;
    }
    
    
}
