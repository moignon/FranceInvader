/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author John
 */
public class SpriteAnime extends Sprite {
    
    private BufferedImage[][] clés;
    private int Anim;
    private int numClé;
    private long animTime;      //en nanoSec
    private long lastFrameTime;
    private Boolean looping = true;
    private Boolean active;
    
    public SpriteAnime (BufferedImage[][] images, long AnimTime) {
        super(images[0][0]);
        this.lastFrameTime = 0;
        clés = images;
        animTime = AnimTime;
        active = true;
    }
    
    @Override
    public void updateAnim(){
        if (active){
            if (clés[Anim].length == 1)
                return;
            if (lastFrameTime == 0 ){
                lastFrameTime = System.nanoTime();
                return;
            }

            long currentTime = System.nanoTime();
            long test = currentTime - lastFrameTime;

            if (currentTime - lastFrameTime > animTime){
                numClé++;
                lastFrameTime = currentTime;
                if (numClé >= clés[0].length){
                    if (looping)
                        numClé = 0;
                    else{
                        active = false;
                        numClé--;
                    }
                }
            }
        }
    }
    
    @Override
    public BufferedImage getImage(){
        return clés [Anim][numClé];
    }

    /**
     * @param animTime le temps que dure une clé avant de passer a la suivante
     */
    public void setAnimTime(long animTime) {
        this.animTime = animTime;
    }
    public void setLooping (boolean looping){
        this.looping = looping;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(Boolean b){
        this.active = b;
    }
    
    
    

}
