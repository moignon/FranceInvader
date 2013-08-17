/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
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
public class ImageBank {
    
    private final static String IMAGE_DIR ="ressources/images/";
    private static ImageBank bank = new ImageBank();
    private HashMap images = new HashMap();
    
    private ImageBank() {
        
    }
    /**
     *
     * @return the SpriteBank singleton
     */
    public static ImageBank get(){
        return bank;
    }
    public BufferedImage getImages(String ref){
        BufferedImage Bimage = (BufferedImage) images.get(ref);
        if (Bimage != null){
            return Bimage;
        }
        System.out.println("acces disque"+ref);
        String path = IMAGE_DIR+ref;
	try {            
            Bimage = ImageIO.read(new File(path)) ;
            images.put(ref,Bimage);
            return Bimage;
        } catch (IOException e) {
            return null;
        }
        
// une piste pour ameliorer le temps pris par une image pour etre dessiné dans le buffer
// mais le resultat obtenu est
//        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        GraphicsDevice device = env.getDefaultScreenDevice();
//        GraphicsConfiguration config = device.getDefaultConfiguration();
//        BufferedImage buffy = config.createCompatibleImage(Bimage.getWidth(), Bimage.getHeight(), Transparency.TRANSLUCENT);
//        Graphics2D g2d = buffy.createGraphics();
//        g2d.drawImage(Bimage, 0, 0, null);
//        images.put(ref,buffy);
//        return buffy;
        
    }
    
    public BufferedImage[][] getImages (String ref, int nbAnim, int nbClé){
        BufferedImage[][] clés = (BufferedImage[][])images.get(ref);
        if (clés != null) {
            return  clés;
        }
        System.out.println("acces disque"+ref);
        String path = IMAGE_DIR+ref;
        BufferedImage Bimage;
	try {
            Bimage =  ImageIO.read(new File(path)) ;
            clés = new BufferedImage[nbAnim][nbClé];
            for (int i = 0 ; i < clés.length; i++){
                for (int j = 0 ; j < clés[i].length; j++){
                    clés[i][j] = getImage (i,nbAnim,j,nbClé,Bimage);
                }
            }
            images.put(ref,clés);
            return clés;
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
