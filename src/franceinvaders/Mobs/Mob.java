/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import franceinvaders.Constantes;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public abstract class Mob extends Entite{
    
    private int  lifeMax,
                 life,
                 points;
    
    public Mob (Sprite sprite,GamePanel panel, int _lifeMax, int _points){
        super(sprite, panel);
        lifeMax = _lifeMax;
        life = _lifeMax;
        points = _points;
    }

    /**
     * @return the lifeMax
     */
    public int getLifeMax() {
        return lifeMax;
    }

    /**
     * @param lifeMax the lifeMax to set
     */
    public void setLifeMax(int lifeMax) {
        this.lifeMax = lifeMax;
    }

    /**
     * @return the life
     */
    public int getLife() {
        return life;
    }

    /**
     * @param life the life to set
     */
    public void setLife(int life) {
        this.life = life;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    public void takeDmg(int hitDmg) {
        this.life = this.life - hitDmg;
    }

    public void dead() {
        this.panel.addScore(this.points);
        this.panel.getListEntite().remove(this);
    }
    

    
}
