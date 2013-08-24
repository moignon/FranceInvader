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
import java.net.URI;
import java.nio.channels.Channels;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author John
 */
public class MainMenu extends GamePanel {
    
    Background background;
    AudioPlayer audio;
    public MainMenu (GameFrame frame){
        super(frame);
        background = new Background(Constantes.MainMenuBackgroundRef);
        background.setSpeed(0);
        audio = AudioPlayer.createPlayer(Constantes.MainMenuBgmRef);
        audio.setLooping(false);
        audio.setChan(AudioPlayer.Channels.MUSIC);
        audio.START();
        
    }
    
        @Override
    public void gameUpdate(){
            background.update();
            if(keys[java.awt.event.KeyEvent.VK_ENTER]){
                launchLV(new Level1(this.getConteneur()));
            }
        }
    public void launchLV(GamePanel level){
        getConteneur().remove(this);
        getConteneur().setVisible(false);
        getConteneur().add(level);
        getConteneur().setVisible(true);
        audio.STOP();
        audio = null;
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
