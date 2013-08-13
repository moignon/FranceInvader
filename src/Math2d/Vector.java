/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Math2d;

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
    
    public void rotate(double angle)
    {
        
        
    }
}
