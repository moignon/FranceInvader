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
        
//        Vector pos = new Vector(0,5);
//        double angleTest = -Math.PI/2;
//        pos.rotate(angleTest);
//        pos.rotate(angleTest);
//        System.out.println(pos.getX()+","+pos.getY());
        
        jeu = new GameFrame();
        
        
    }
    
    public static double toD (double param){
            return param * (180 / Math.PI);
        }
    
}
