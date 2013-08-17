/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Animations;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.SpriteAnime;
import Framework.ImageBank;
import Framework.Sprite;
import franceinvaders.Constantes;
import java.awt.Graphics;
import java.io.File;

/**
 *
 * @author John
 */
public class Papa extends Entite {
    
    static File Sound;
    boolean init = false;
    private boolean fireDone;
    private long lastTime;
    private long dTime;
    private long dTimeRetrait = 65 *100;
    private Boolean steps[];
    MegaLaser tir;
    
    public Papa (GamePanel panel){
        super(new SpriteAnime(ImageBank.get().getImages(Constantes.papaRef, 2, 4),600), panel);
        lastTime = 0;
        steps = new Boolean[5];
        for(int i = 0; i < 5; i++)
            steps[i] = false;
        steps[0] = true;
        setXspeed(-5);
        setYspeed(3);
        setPosition(panel.getWIDTH(), 0);
        
    }

    @Override
    public void trollNoobs() {
        SpriteAnime s = ((SpriteAnime)this.sprite);
        long currentTime = System.currentTimeMillis();
        if(lastTime == 0)
            lastTime = currentTime;
        dTime = currentTime - lastTime;
        
        if(steps[0]){
            if (this.getX()-getL()/3 <panel.getWIDTH()- s.getWidth()*3/4){
                setXspeed(0);
                setYspeed(5);
                steps[0] = false;
                steps[1]= true;
            }
        }
        if (steps[1]){
            if (this.getY() > 400){
                setYspeed(0);
                this.fire();
                steps[1] = false;
                steps[2] = true;
            }
        }
        if(steps[2]){
            if(tir.isDone()){
                s.setAnim(1);
                steps[2] = false;
                steps[3] = true;
            }
        }
        if(steps[3]){
            if(dTime >= dTimeRetrait){
                this.setXspeed(1);
                steps[3] = false;
                steps[4] = true;
            }
        }
        if(steps[4]){
           if(isOutOfScreen()){
                            this.getPanel().remove(this);
                        } 
        }
        
    }
    private void fire() {
        if (tir == null){
            tir = new MegaLaser(panel,this);
        }
    }

    @Override
    public void collided(Entite s, Collision c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
