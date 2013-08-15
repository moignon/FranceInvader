/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author John
 */
class Player extends Entite {
    private boolean []keys;
    private String []keymap;
    private int controlMode = 0;
    private Vector speedVector = new Vector(0,0);
    private double speed = 7.0 ;
    Weapon armeEquipee,armeSecondaire;
    
    public Player(Sprite sprite,GamePanel panel) {
        super(sprite, panel);
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
    public void codeMe() {
        
        SpriteAnime s = (SpriteAnime) this.sprite;
        s.setAnim(0);
        int i = 0;
        
        this.setAngle(this.pos.orientToVector(panel.getMouseVector()));
        speedVector.setXY(0, 0);
        while(i < keys.length)
        {
            if(keys[i])
            {
                switch (keymap[i])
                { 
                    case "droite" :
                    {
                        moveLateral( this.controlMode,speed);
                        break;
                    }
                    case "gauche" :
                    {
                       moveLateral (this.controlMode, - speed); 
                       break;
                    }
                    case "haut":
                    {
                        speedVector.setY(-7);
                        break;
                    }
                    case "bas" : 
                    {
                        speedVector.setY(7);
                        break;
                    }
                    case "tir1" :
                    {
                         this.fire(armeEquipee);
                        break;
                    }
                    case "tir2" :
                    {
                       this.fire(armeSecondaire);
                       break;
                    }
                    default :
                }
            }
            i++;
        }
        
        if(controlMode == 1)
        {
            speedVector.rotate(this.getAngle());  
        }
        
        this.setXspeed((int) speedVector.getX());
        this.setYspeed((int) speedVector.getY());
        
        for (int j = 0; j < this.panel.getListEntite().size(); j++){
            Entite e = this.panel.getListEntite().get(j);
            if (e instanceof Mob) {
                if (e.collidesWith(this)){
                    
                    this.dead();
                    panel.gameOver();
                    return;
                }
                
            }
        }
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
    private void moveLateral (int mode, double speed){
        pos = pos.move(speed, panel.getMouseVector());
    }
    private void moveVertical (int mode, double speed){
        Vector mouse = panel.getMouseVector();
        Vector pos = this.pos;
        Vector sommetRect = new Vector(mouse.getX(),mouse.getY());
    }
   
}

