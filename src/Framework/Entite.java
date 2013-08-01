/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
/**
 *
 * @author John
 */
public abstract class Entite {
    protected double xSpeed, ySpeed; //(pixel/frame)
    protected double x,y;
    
    boolean active = false;
    boolean visible = false;
    
    public boolean collide = true;
    public boolean blocked = false;
    
    public int h;
    public int l;
    
    protected Sprite sprite;   
    private Graphics2D g2d;
    private CollisionBox collisionBox;
    protected GamePanel panel;
    
   
    public Entite (Sprite _sprite,GamePanel _panel){
        sprite = _sprite;
        g2d = sprite.getImage().createGraphics();
        h = sprite.getHeight();
        l = sprite.getWidth();
        this.x = 0;
        this.y = 0;
        active = true ;
        visible = true;
        collisionBox = new CollisionBox();
        collisionBox.setBounds(new Rectangle((int)x, (int)y, l, h));
        panel = _panel;
    }
    
    public void update (){
        if (active){
            x+=xSpeed;
            y+=ySpeed;
            codeMe();
            sprite.updateAnim();
         }
    }
    
    
    public void blit(Graphics gBuffer) {
        if (active)
            if (visible)
                sprite.draw(gBuffer, x, y);
    }    
    public int getH (){
        return this.h;
    }
    public int getL (){
        return this.l;
    }
    public void setPostion (int _x, int _y){
        x = _x;
        y = _y;
    }
    public void setCenteredPostion (double _x, double _y){
        x = _x - this.getL()/2;
        y = _y - this.getH()/2;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getXspeed() {
        return xSpeed;
    }
    public double getYspeed() {
        return ySpeed;
    }
    public CollisionBox getCollisionBox (){
       this.collisionBox.setLocation((int)this.x, (int)this.y);
       return this.collisionBox;
    }
    
    public boolean collidesWith(Entite e){
        return this.getCollisionBox().intersects(e.getCollisionBox());
    }
//    public abstract void collided (Sprite s);
//    public abstract void checkcollisions();
    public abstract void codeMe();
//    public abstract void collisionDetected();

    public void setXspeed(int speed) {
        this.xSpeed = speed;
    }
    public void setYspeed(int speed){
        this.ySpeed = speed;
    }

    public GamePanel getPanel() {
        return this.panel;
    }
    
    public boolean isOutOfScreen() {
        if( x < 0-getL() || x > panel.getWIDTH()+getL() || y < 0-getH() || y > panel.getHEIGHT()+getH())
            return true;
        return false;
    }
  
    

   

    
}
