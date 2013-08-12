/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import franceinvaders.Weapons.Weapon;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.ProjectilesEntites.Projectile;
import franceinvaders.Weapons.DaddyBoom;
import franceinvaders.Weapons.TirSimple;
import java.util.ArrayList;

/**
 *
 * @author John
 */
class Player extends Entite {
    private GamePanel gamePanel;
    private boolean []keys;
    private String []keymap;
    Weapon armeEquipee,armeSecondaire;
    
    public Player(Sprite sprite,GamePanel panel) {
        super(sprite, panel);
        this.angle = 0;
        this.armeEquipee = new TirSimple();
        this.armeSecondaire = new DaddyBoom();
        loadKeymap("Defaut");
    }
    
    public void setGamePannel(GamePanel panel)
    {
        gamePanel = panel;
        keys = gamePanel.getKeys();
    }
    
    public void getInput()
    {
        int i = 0;
        
        this.setXspeed(0);
        while(i < keys.length)
        {
            if(keys[i])
            {
                switch (keymap[i])
                { 
                    case "haut":
                    {

                        break;
                    }
                    case "bas" : 
                    {

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
            keymap[java.awt.event.MouseEvent.BUTTON2] = "tir2";
        }
        else
        {
            // charger a partir d'un fichier , d'adresse param gogogogo noob
        }
    }
    
    @Override
    public void codeMe() {
    }
    
}
