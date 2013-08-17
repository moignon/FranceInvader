/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.ProjectilesEntites;

import Framework.AudioPlayer;
import Framework.Collision;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import java.io.File;

/**
 *
 * @author John
 */
public class Flamme extends Projectile {
    
    private static File fireSound;
    
    public Flamme (Sprite sprite,GamePanel panel){
        super(sprite,panel);
        this.setHitDmg(10);
        if (fireSound == null)
            fireSound = new File ("ressources/audio/fire.wav");
        AudioPlayer audio = AudioPlayer.createPlayer(fireSound);
        audio.setVolume(-20F);
        audio.start();
      }
    
    @Override
    public void collided(Entite s, Collision c) {
    }

    
}
