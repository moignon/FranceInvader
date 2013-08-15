/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Math2d.Vector;
import java.awt.Color;
/**
 *
 * @author John
 */
public abstract class Entite {
    
    protected Vector speed = new Vector(0,0);
    protected Vector pos = new Vector(0,0);
    
    protected double angle = 0;
    
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
        active = true ;
        visible = true;
        collisionBox = new CollisionBox();
        collisionBox.setBounds(new Rectangle((int)pos.getX(), (int)pos.getY(), l, h));
        panel = _panel;
    }
    
    public void update (){
        if (active){
            codeMe();
            pos.addX(speed.getX());
            pos.addY(speed.getY());
            sprite.updateAnim();
         }
    }
    
    
    public void blit(Graphics2D gBuffer) {
        if (active)
            if (visible){
                sprite.drawRotate(gBuffer, pos.getX()- this.getL()/2,pos.getY()-getH()/2, angle);
                if(panel.getDevMode()){
                    Color col = gBuffer.getColor();
                    gBuffer.setColor(Color.RED);
                    gBuffer.fillOval((int)pos.getX(), (int)pos.getY(), 2, 2); 
                    Rectangle r = this.getCollisionBox();
                    gBuffer.drawRect((int)r.getX(), (int)r.getY(), r.width, r.height);
                    gBuffer.setColor(col);
                }
            }
    }    
    public int getH (){
        return this.h;
    }
    public int getL (){
        return this.l;
    }
    public void setPosition (double _x, double _y){
        pos.setXY(_x, _y);
    }
    public double getX() {
        return pos.getX();
    }
    public double getY() {
        return pos.getY();
    }
    public double getXspeed() {
        return speed.getX();
    }
    public double getYspeed() {
        return speed.getY();
    }
    
    public Vector getSpeedVector()
    {
        return speed;
    }
    
    public Vector getPosVector()
    {
        return pos;
    }
    public CollisionBox getCollisionBox (){
       this.collisionBox.setLocation((int)this.pos.getX()-this.l/2, (int)this.pos.getY()-this.h/2);
       return this.collisionBox;
    }
    
    public boolean collidesWith(Entite e){
        return this.getCollisionBox().intersects(e.getCollisionBox());
    }
    
    public abstract void collided (Entite s, Collision c);
    public abstract void codeMe();
//    public abstract void collisionDetected();

    public void setXspeed(double speed) {
        this.speed.setX(speed);
    }
    public void setYspeed(double speed){
        this.speed.setY(speed);
    }

    public GamePanel getPanel() {
        return this.panel;
    }
    
    public boolean isOutOfScreen() {
        Boolean test = false;
        if( pos.getX() < 0-getL()/2 || pos.getX()> panel.getWIDTH()+getL()/2 || pos.getY() < 0-getH()/2 || pos.getY() > panel.getHEIGHT()+getH()/2)
            test = true;
        return test;
    }
  
    public double getAngle()
    {
        return this.angle;
    }
    
    public double setAngle(double param)
    {
        this.angle = param;
        return this.angle;
    }
    

   

    
}
