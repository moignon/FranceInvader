/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import franceinvaders.Weapons.Weapon;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import franceinvaders.Mobs.Mob;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;
import franceinvaders.Weapons.DaddyBoom;
import franceinvaders.Weapons.TirSimple;
import java.util.ArrayList;
import Math2d.Vector;

/**
 *
 * @author John
 */
class Player extends Entite {
    private boolean []keys;
    private String []keymap;
    private int controlMode = 0;
    private Vector speedVector = new Vector(0,0);
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

        
        
        int i = 0;
        speedVector.setXY(0, 0);

        while(i < keys.length)
        {
            if(keys[i])
            {
                switch (keymap[i])
                { 
                    case "haut":
                    {
                        setYspeed(-7);
                        break;
                    }
                    case "bas" : 
                    {
                        setYspeed(7);
                        break;
                    }
                    case "droite" :
                    {
                        this.setXspeed(7);
                        break;
                    }
                    case "gauche" :
                    {
                       this.setXspeed(-7);
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
            speedVector.rotate(this.angle);  
        }
    
    }
   
}

