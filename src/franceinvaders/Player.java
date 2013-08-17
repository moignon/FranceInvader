/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import Framework.Collision;
import franceinvaders.Weapons.Weapon;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import Framework.SpriteAnime;
import franceinvaders.Mobs.Mob;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;
import franceinvaders.Weapons.DaddyBoom;
import franceinvaders.Weapons.TirSimple;
import java.util.ArrayList;
import Math2d.Vector;
import franceinvaders.ProjectilesEntites.Explosion;
import franceinvaders.Weapons.CashIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author John
 */
public class Player extends Entite {
    
    Vector v;
    private boolean []keys;
    private String []keymap;
    private int controlMode = 1;
   // private Vector speedVector = new Vector(0,0);
    private double speed = 7.0 ;
    SpriteAnime sprite;
    Weapon armeEquipee,armeSecondaire;
    
    public Player(Sprite sprite,GamePanel panel) {
        super(sprite, panel);
        this.sprite = (SpriteAnime)super.sprite;
        this.angle = 0;
        this.armeEquipee = new TirSimple();
        this.armeSecondaire = new DaddyBoom();
        keys = panel.getKeys();
        loadKeymap("Defaut");
    }
    
    public void fire (Weapon arme){
        arme.Fire(this);
    }

    public void loadKeymap(String param)
    {
        int i = 0;
        keymap = new String[256];
        while (i < keymap.length)
        {
            keymap[i] = "";
            i++;
        }
        
        if(param == "Defaut")
        {
            keymap[java.awt.event.KeyEvent.VK_UP] = "haut";
            keymap[java.awt.event.KeyEvent.VK_DOWN] = "bas";
            keymap[java.awt.event.KeyEvent.VK_LEFT] = "gauche";
            keymap[java.awt.event.KeyEvent.VK_RIGHT] = "droite";
            keymap[java.awt.event.MouseEvent.BUTTON1] = "tir1";
            keymap[java.awt.event.MouseEvent.BUTTON3] = "tir2";
        }
        else
        {
            // charger a partir d'un fichier , d'adresse param gogogogo noob
        }
        
    }
    
    @Override
    public void trollNoobs() {
        
        this.setAngle(this.pos.orientToVector(panel.getMouseVector()));
        getSpeedVector().setXY(0, 0);
        for (int i = 0; i < keys.length; i ++){
            if(keys[i]){
                switch (keymap[i]){ 
                    case "droite" :{
                        setXspeed(speed); break;
                    }
                    case "gauche" :{
                        setXspeed(-speed); break;
                    }
                    case "haut": {
                        setYspeed( -speed); break;
                    }
                    case "bas" : {
                       setYspeed (speed); break;
                    }
                    case "tir1" :{
                         this.fire(armeEquipee); break;
                    }
                    case "tir2" : {
                       this.fire(armeSecondaire); break;
                    }
                    default :
                }
            }
        }
        v = new Vector(getXspeed(),getYspeed());
        
        if(controlMode == 1)
            getSpeedVector().rotate(this.getAngle()+Math.PI);  
        
        for (int j = 0; j < this.panel.getListEntite().size(); j++){
            Entite e = this.panel.getListEntite().get(j);
            if (e instanceof Mob) {
                if (e.collidesWith(this)){
                    this.dead();
                    panel.gameOver();
                    return;
                }
            }
            if (e instanceof CashIcon){
                if (this.collidesWith(e))
                   e.collided(this, null);
            }
        }
        chooseAnim();
        this.armeEquipee.update();
        this.armeSecondaire.update();
    }
    @Override
    public void move() {
        
        getPosVector().addX(getXspeed());
        getPosVector().addY(getYspeed());
        
        double limiteX = getPanel().getWIDTH()- (this.getL()/2) -1;
        double limiteY = getPanel().getHEIGHT()- this.getH()/2 -1;
        if(getPosVector().getX() > limiteX)
            getPosVector().setX(limiteX);
        if(getPosVector().getX() < this.getL()/2)
            getPosVector().setX(this.getL()/2);
        if(getPosVector().getY() > limiteY)
            getPosVector().setY(limiteY);
        if(getPosVector().getY() < this.getH()/2)
            getPosVector().setY(this.getH()/2);
    }
    
    @Override
    public void blit (Graphics2D gBuffer){
     super.blit(gBuffer);
     if(panel.getDevMode())
        gBuffer.drawLine((int)getX(), (int)getY(),(int)panel.getMouseVector().getX() ,(int) panel.getMouseVector().getY());
    }

    private void dead() {
        Explosion exp = new Explosion(getPanel());
        exp.setPosition(getX(), getY());
        panel.add(exp);
    }
    private void chooseAnim (){
        //sprite.setAnim(0);
        if(v.getX() > 0)sprite.setAnim(1);
        if(v.getX()< 0)sprite.setAnim(2);
        
        if(Math.abs(v.getX()) <= Math.abs(v.getY()))
            sprite.setAnim(0);
    }

    @Override
    public void collided(Entite s, Collision c) {
    }
   
}

