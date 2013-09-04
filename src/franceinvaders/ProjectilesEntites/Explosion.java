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
import Framework.SpriteAnime;
import Framework.ImageBank;
import franceinvaders.Constantes;
import java.io.File;

/**
 *
 * @author John
 */
public class Explosion extends Entite{
    
     AudioPlayer audio = null;
    public Explosion (GamePanel panel){
        
        super(new SpriteAnime(ImageBank.get().getImages(Constantes.explosionRef, 1, 5),100), panel);
        ((SpriteAnime)this.sprite).setLooping(false);
            audio = AudioPlayer.createPlayer(Constantes.ExplosionFxRef);
            audio.setGain(-5);
            audio.setLooping(false);
            audio.setChan(AudioPlayer.Channels.FXs);
            audio.START();
        
    }

    @Override
    public void trollNoobs() {
        SpriteAnime s = ((SpriteAnime)this.sprite);
        if (!s.isActive()){
            this.panel.getListEntite().remove(this);
        }
    }

    @Override
    public void collided(Entite s, Collision c) {
    }
    
    
    
}
