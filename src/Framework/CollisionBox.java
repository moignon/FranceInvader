/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.awt.Rectangle;

/**
 *
 * @author John
 */
public class CollisionBox extends Rectangle {
    
    void translate(double xSpeed, double ySpeed) {
        translate((int)xSpeed,(int)ySpeed);
    }
}
