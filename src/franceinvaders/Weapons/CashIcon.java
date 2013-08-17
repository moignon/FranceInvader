/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Weapons;

import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.ImageBank;
import Framework.Sprite;
import franceinvaders.Constantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class CashIcon extends Entite {
    
    private int score = 2000,
                Xtext = 0,
                Ytext = 0;
    boolean hit = false;
    String text = score+ " Frs!";
    long animTime = 1000; // ms
    long waitTime = 0;
    Color[]colors = new Color[3];
    int couleurActuelle = 0;
    private long colorTime = 200;
    //File font = new File("ressources/fonts/Comic_Book.ttf");
    
    public CashIcon (GamePanel panel){
        super(new Sprite(ImageBank.get().getImages(Constantes.cashRef)), panel);
        colors[0] = Color.BLUE; colors[1] = Color.WHITE; colors [2] = Color.RED; 
    }
    @Override
    public void collided(Entite s, Collision c) {
        if(!hit){
            panel.addScore(getScore());
            hit = true;
            setXspeed(0);
            setYspeed(0);
        }
    }
    @Override
    public void trollNoobs() {
        if(hit){
            waitTime = waitTime + panel.getdTime();
                if(waitTime <= animTime){
                    this.setYspeed(-1);
                    couleurActuelle = ((int) (waitTime/colorTime))%colors.length;
                }
                else{
                    panel.getListEntite().remove(this);
                }
        }
    }
    
    public void blit(Graphics2D gBuffer) {
        if (!hit)
            super.blit(gBuffer);
        else{
            Color c = gBuffer.getColor();
            gBuffer.setColor(colors[couleurActuelle]);
            Font font = new Font(null, 0, 20);
            gBuffer.setFont( font);
            FontMetrics fm = gBuffer.getFontMetrics();
            gBuffer.drawString(text, (int)(getX() - (fm.stringWidth(text))/2), (int)getY());
            gBuffer.setColor(c);
        }
    }

    public int getScore() {
        return score;
    }
    public void setScore(int param) {
        score = param ;
        text = score+ " Frs!";
    }
   
}
