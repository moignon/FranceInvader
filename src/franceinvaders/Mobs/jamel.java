/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import Framework.ImageBank;
import franceinvaders.Constantes;
import franceinvaders.Weapons.CashIcon;

/**
 *
 * @author John
 */
public class jamel extends Mob {
    
    
    public jamel (GamePanel panel){
        super(new Sprite(ImageBank.get().getImages(Constantes.jamelRef)),panel, 40,1000);
        this.setPosition(0-sprite.getWidth(), 0-sprite.getWidth());
        this.setPosition(Math.random()*panel.getWIDTH() % panel.getWIDTH() - this.getL() / 2, 0 - this.getH()/2 + 1);
        this.setXspeed((int) (Math.random()*10) - 5);
        //this.setXspeed(1);
        this.setYspeed((int)(Math.random()*10));
        panel.add(this);
    }

    @Override
    public void codeMe() {
        
        if (this.isOutOfScreen()) {
            this.getPanel().remove(this);
        }
    }

    @Override
    public void collided(Entite s, Collision c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void takeDmg(int hitDmg) {
        super.takeDmg(hitDmg);
        
        String ref = null;
        if(getLife() <= 0){
            CashIcon i = new CashIcon(getPanel());
            i.setPosition(this.getX(), this.getY());
            getPanel().getListEntite().add(i);
        }
    }
    
}
