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
import franceinvaders.Constantes;
import java.io.File;

public class Flamme extends Projectile {
    public AudioPlayer audio ;
    public Flamme (Sprite sprite,GamePanel panel){
        super(sprite,panel);
        this.setHitDmg(10);
        
        audio = AudioPlayer.createPlayer(Constantes.FireFxRef);
        audio.setGain(-5);
        audio.START();
      }
    
    @Override
    public void collided(Entite s, Collision c) {
    }

    
}
