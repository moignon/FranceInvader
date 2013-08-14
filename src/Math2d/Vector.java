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
        System.out.println(angle);
        return (angle);
    }
}
