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
    
    public SpriteAnime (BufferedImage[][] images, int AnimTime) {
        super(images[0][0]);
        this.lastFrameTime = 0;
        clés = images;
        animTime = AnimTime*1000000;
        active = true;
    }
    
    @Override
    public void updateAnim(){
        if (isActive()){
            if (getClés()[getAnim()].length == 1)
                return;
            if (getLastFrameTime() == 0 ){
                this.lastFrameTime = System.nanoTime();
                return;
            }

            long currentTime = System.nanoTime();
            if (currentTime - getLastFrameTime() > getAnimTime()){
                setNumClé(getNumClé() + 1);
                this.lastFrameTime = currentTime;
                if (getNumClé() >= getClés()[0].length){
                    if (isLooping())
                        setNumClé(0);
                    else{
                        setActive((Boolean) false);
                        setNumClé(getNumClé() - 1);
                    }
                }
            }
        }
    }
    
    
    @Override
    public BufferedImage getImage(){
        return getClés() [getAnim()][getNumClé()];
    } 
    
    /**
     * @return true si l'animation tourne en boucle
     */
    public Boolean isLooping() {
        return looping;
    }
    
    public void setLooping (Boolean looping){
        this.looping = looping;
    }

    public boolean isActive() {
        return this.active;
    }
    public void setActive(Boolean b){
        this.active = b;
    }
    
    /**
     * @return le numero de l'Animation en cours
     */
    public int getAnim() {
        return Anim;
    }

    /**
     * @param Anim le numero de l'animation souhaité, si le chiffre et trop gros, ne fait rien
     */
    public void setAnim(int Anim) {
        if (Anim <= this.clés.length)
            this.Anim = Anim;
    } 
    
    /**
     * @return les clés qui composent ce Sprite
     */
    public BufferedImage[][] getClés() {
        return clés;
    }

    /**
     * @return le numero de la clé actuelle
     */
    public int getNumClé() {
        return numClé;
    }

    /**
     * @param numClé set la clé actuelle ( si nombre trop grand ne fait rien)
     */
    public void setNumClé(int numClé) {
        int size;
        if(numClé <= this.getClés()[0].length)
            this.numClé = numClé;
    }

    /**
     * @return animTime, le temps que dure une clé avant de passer a la suivante
     */
    public long getAnimTime() {
        return animTime;
    }
    
    /**
     * @param animTime le temps que dure une clé avant de passer a la suivante
     */
    public void setAnimTime(long animTime) {
        this.animTime = animTime;
    }

    /**
     * @return the lastFrameTime
     */
    public long getLastFrameTime() {
        return lastFrameTime;
    }


    
    
    

}
