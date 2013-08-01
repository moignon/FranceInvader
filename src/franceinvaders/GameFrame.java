/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import Framework.GamePanel;
import java.util.ArrayList;
import javax.swing.JFrame;


/**
 *
 * @author John
 */
public final class GameFrame extends JFrame{
    
    ArrayList <GamePanel> niveaux;
    
    
    public GameFrame (){
        super();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        if(true) // Full screen mode
	{
	    this.setUndecorated(true);
            this.setLocationRelativeTo(null);
	    // Puts the frame to full screen.
	    this.setExtendedState(this.MAXIMIZED_BOTH);
	}
	else // Window mode
	{
            //this.setUndecorated(true);
            this.setSize(1255+this.getInsets().left+this.getInsets().right, 875 +this.getInsets().top+this.getInsets().bottom);
	    // Puts frame to center of the screen.
	    this.setLocationRelativeTo(null);
	    this.setResizable(true);
	}
        this.requestFocus();
        Level1 jeu = new Level1();        
        this.add(jeu);
        
         System.err.print(this.getInsets());
        this.setVisible(true);
        
         System.err.print(this.getInsets());
    }
    
}
