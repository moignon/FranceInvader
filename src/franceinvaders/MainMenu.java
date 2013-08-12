/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;

import Framework.AudioPlayer;
import Framework.Background;
import Framework.GamePanel;
import java.awt.Component;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author John
 */
public class MainMenu extends GamePanel {
    
    Background background;
    GameFrame conteneur;
    AudioPlayer audio;
    public MainMenu (GameFrame frame){
        super();
        conteneur = frame;
        conteneur.add(this);
        background = new Background(Constantes.MainMenuBackgroundRef);
        background.setSpeed(0);
        audio = AudioPlayer.createPlayer(new File("ressources/audio/The_J_Arthur_Keenes_Band_-_07_-_El_Campo_Del_Laser.wav"));
        audio.start();
        
    }
    
        @Override
    public void gameUpdate(){
            background.update();
           
            if(enter){
                launchLV(new Level1());
                enter = false;
            }
        }
    public void launchLV(GamePanel level){
        conteneur.remove(this);
        conteneur.setVisible(false);
        conteneur.add(level);
        conteneur.setVisible(true);
        audio.pause();
        this.stopGame();
    }

    @Override
    protected void blitEntites() {
        background.blit(gBuffer);
    }

    @Override
    public void gameOver() {
    }
    
    
    
}
