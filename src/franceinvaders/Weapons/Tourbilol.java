/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Entite;

/**
 *
 * @author John
 */
public class Tourbilol implements Weapon{
    
    TriBlaster tir = new TriBlaster();
    Boolean bool = true;
    double angle;
@Override
    public void Fire(Entite e) {
        if (bool){
            angle = e.getAngle();
            bool = false;
        }
        angle = angle + (Math.PI*0.1);
        double a = e.getAngle();
        e.setAngle(angle);
        tir.projectileSpeed = 15;
        tir.delai = 10
                
                ;
        tir.Fire(e);
        e.setAngle(a);
    }
    
}
