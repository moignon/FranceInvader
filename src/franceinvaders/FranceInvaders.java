/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import Framework.GamePanel;
import Math2d.Vector;
import javax.swing.JFrame;

/**
 *
 * @author John
 */
public class FranceInvaders {

    /**
     * @param args the command line arguments
     */
    public static GameFrame jeu;
    public static void main(String[] args) {
        
//        Vector pos = new Vector(5,5);
//        Vector mous = new Vector (3,2);
//        Double speed = 2.0;
//        Double angleTest = pos.findAngle( speed ,mous);
//        //angleTest = -Math.PI/2;
//        System.out.println ("angle : "+ toD(angleTest)); 
//        
//        pos = pos.rotate((angleTest), mous);
//        System.out.println (" a' ("+pos.getX()+","+pos.getY()+")");
//        
//        pos = pos.rotate((-angleTest), mous);
//        System.out.println (" a' ("+pos.getX()+","+pos.getY()+")");
        
        jeu = new GameFrame();
        
        
    }
    
    public static double toD (double param){
            return param * (180 / Math.PI);
        }
    
}
