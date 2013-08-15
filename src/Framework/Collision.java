/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import Math2d.Vector;

/**
 *
 * @author John
 */
public class Collision {
    Entite entite;
    Entite bloqueur;
    Vector pos;

    public Collision (Entite param, Entite bloqueur, Vector _pos){
        entite = param;
        this.bloqueur = bloqueur;
        pos = _pos;
    }
    public double getX(){
        return pos.getX();
    }
    public double getY(){
        return pos.getY();
    }
    public Vector getPosition (){
        return pos;
    }
    public Entite getEntite (){
        return entite;
    }
    public Entite getBloqueur (){
        return bloqueur;
    }
    
}
