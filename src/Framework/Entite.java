/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Math2d.Vector;
/**
 *
 * @author John
 */
public abstract class Entite {
    
    /* A REMPLACER PAR DES VECTEURS */
    protected double xSpeed, ySpeed; //(pixel/frame)
    protected double x,y;
    /* -----------------------------------------------*/
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
            pos.addX(speed.getX());
            pos.addY(speed.getY());
            codeMe();
            sprite.updateAnim();
         }
    }
    
    
    public void blit(Graphics2D gBuffer) {
        if (active)
            if (visible)
                sprite.drawRotate(gBuffer, pos.getX(),pos.getY(), angle);
    }    
    public int getH (){
        return this.h;
    }
    public int getL (){
        return this.l;
    }
    public void setPostion (int _x, int _y){
        pos.setXY(_x, _y);
    }
    public void setCenteredPostion (double _x, double _y){
        pos.setXY(_x - sprite.getWidth()/2,_y - sprite.getHeight()/2 );
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
        return (speed);
    }
    
    public Vector getPosVector()
    {
        return (pos);
    }
    public CollisionBox getCollisionBox (){
       this.collisionBox.setLocation((int)this.pos.getX(), (int)this.pos.getY());
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
        this.speed.setX(speed);
    }
    public void setYspeed(int speed){
        this.speed.setY(speed);
    }

    public GamePanel getPanel() {
        return this.panel;
    }
    
    public boolean isOutOfScreen() {
        if( pos.getX() < 0-getL() || pos.getX()> panel.getWIDTH()+getL() || pos.getY() < 0-getH() || pos.getY() > panel.getHEIGHT()+getH())
            return true;
        return false;
    }
  
    public double getAngle()
    {
        return this.angle;
    }
    
    public double setAngle(double param)
    {
        this.angle = param % 360;
        return this.angle;
    }
    

   

    
}
