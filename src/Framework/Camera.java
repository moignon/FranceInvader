/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import Framework.GamePanel;
import Framework.Sprite;
import Math2d.Vector;
/**
 *
 * @author bob
 */
public class Camera extends Entite{
    private Entite target;
    private int mode;
    private Vector screenResolution;
    
    public Camera(Sprite sprite,GamePanel panel)
    {
        super(sprite, panel);
        this.visible = false;
        this.setPosition(0, 0);
        screenResolution = new Vector(0,0);
        this.setResolution(panel.getWIDTH(), panel.getHEIGHT());
        this.changeMode("STATIC");
    }
    
    public void follow(Entite newTarget)
    {
        target = newTarget;
        changeMode("FOLLOW");
    }
    
    public void changeMode(String param)
    {
        switch(param)
        {
            case "STATIC": 
                mode = 1;
                break;
            case "FOLLOW": 
                mode = 2;
                break;
        }
    }
    
    public void center(Entite param)
    {
        this.setPosition(param.getX(), param.getY());
    }
    
    public void updateFollow()
    {

        this.setPosition(target.getX(), target.getY());
    }
    
    public void setResolution(int x , int y)
    {
        screenResolution.setXY(x,y);
    }
    
    public int getCorrectionX()
    {
        int x = 0;
        
        x = - (int)this.getX() + (int)this.screenResolution.getX()/2;
        
        return x;
    }
    
    public int getCorrectionY()
    {
        int y = 0;
        y = - (int)this.getY() + (int)this.screenResolution.getY()/2;
        return y;
    }
    
    @Override   
    public void trollNoobs()
    {
        switch(mode)
        {
            case 1:
                break;
            case 2:
                updateFollow();
                break;
        }
    }
    
    @Override
    public void collided(Entite s, Collision c)
    {
        
    }
           
}
