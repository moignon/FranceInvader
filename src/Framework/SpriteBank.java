/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author John
 */
public class SpriteBank {
    
    private final static String IMAGE_DIR ="ressources/images/";
    private static SpriteBank bank = new SpriteBank();
    private HashMap sprites = new HashMap();
    
    private SpriteBank() {
    }
    /**
     *
     * @return the SpriteBank singleton
     */
    public static SpriteBank get(){
        return bank;
    }
    
     /**
     *
     * @return the SpriteBank singleton
     */
    public Sprite getSprite (String ref) {
        if (sprites.get(ref) != null) {
            System.out.println("image deja chargée:"+ref);
            return (Sprite) sprites.get(ref);
        }
        String path = IMAGE_DIR+ref;
        BufferedImage Bimage;
	try {            
            Bimage = ImageIO.read(new File(path)) ;  
            System.out.println("lecture disque !:"+ref);
            Sprite sprite = new Sprite(Bimage);
            sprites.put(ref,sprite);
            return sprite;
        } catch (IOException e) {
            return null;
        }
    }
    
    public SpriteAnime getSprite (String ref, int nbAnim, int nbClé, int animTime){
        if (sprites.get(ref) != null) {
            System.out.println("image animée deja chargée:"+ref);
            return (SpriteAnime) sprites.get(ref);
        }
        String path = IMAGE_DIR+ref;
        BufferedImage Bimage;
	try {
            Bimage =  ImageIO.read(new File(path)) ;  
            System.out.println("lecture disque !:"+ref);
            BufferedImage[][] images = new BufferedImage[nbAnim][nbClé];
            for (int i = 0 ; i < images.length; i++){
                for (int j = 0 ; j < images[i].length; j++){
                    images[i][j] = getImage (i,nbAnim,j,nbClé,Bimage);
                }
            }
            SpriteAnime sprite = new SpriteAnime(images, animTime);
            //sprites.put(ref,sprite);
            return sprite;
        } catch (IOException e) {
            return null;
        }
    }
    
    private BufferedImage getImage (int anim, int nbAnim, int cle, int nbCle, BufferedImage Bimage){
        int largeurCle = Bimage.getWidth()/nbCle;
        int hauteurCle = Bimage.getHeight()/nbAnim;
        int offsetX = cle*largeurCle;
        int offsetY = anim*hauteurCle;
        return Bimage.getSubimage(offsetX, offsetY, largeurCle, hauteurCle);
    }

    
    
    
}
