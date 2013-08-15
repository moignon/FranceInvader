/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math2d;

import java.lang.Math;

/**
 *
 * @author bob
 */
public class Vector {
    protected double x = 0;
    protected double y = 0;
    
    public Vector(double xIn , double yIn)
    {
        this.x = xIn;
        this.y = yIn;
    }
    
    public double getX()
    {
        return (x);
    }
    
    public double getY()
    {
        return (y);
    }
    
    public void setX(double xIn)
    {
        this.x = xIn;
    }
    
    public void setY(double yIn)
    {
        this.y = yIn;
    }
    
    public void setXY(double xIn , double yIn)
    {
        this.x = xIn;
        this.y = yIn;
    }
    
     public void addX(double xIn)
    {
        this.x += xIn;
    }
    
    public void addY(double yIn)
    {
        this.y += yIn;
    } 
    public void translate (Vector param){
        this.x = this.x + param.getX();
        this.y = this.y + param.getY();
    }
    public double module(){
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
    
    public void rotate(double angle)
    {
        double x = this.x;
        double y = this.y;
        
        this.x = (x * Math.cos(angle)) - (y * Math.sin(angle));
        this.y = (x * Math.sin(angle)) + (y * Math.cos(angle));
        
    }
    
    public double orientToVector(Vector target)
    {
        double angle = 0;
        Vector calcul = new Vector(0,0);
        
        calcul.setXY(target.getX() - this.x,target.getY() - this.y);
        angle = - (Math.atan2(calcul.getX(),calcul.getY()));
        return (angle);
    }
    
    public double findAngle (Double speed, Vector centre){
        Vector rayon = new Vector(centre.getX() - this.getX(), centre.getY() - this.getY());
        double hauteur =  Math.sqrt(Math.pow(rayon.module(),2) - Math.pow(speed / 2, 2));
        double angle =  - 2 * Math.atan((speed / 2)/hauteur);
        return angle;
    }
    public Vector rotate (Double angle, Vector centre) {
        Vector rayon = new Vector((this.getX() - centre.getX()), ( this.getY() - centre.getY()));
        rayon.rotate(angle);
        return new Vector (rayon.getX()+centre.getX(),rayon.getY()+centre.getY());
    }
    public Vector move (Double speed, Vector centre){
        return this.rotate(this.findAngle(speed, centre), centre);
    }
}
