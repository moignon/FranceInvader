/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.ProjectilesEntites;

import Framework.AudioPlayer;
import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import Framework.SpriteAnime;
import Framework.SpriteBank;
import franceinvaders.Constantes;
import java.io.File;

/**
 *
 * @author John
 */
public class Explosion extends Entite{
    
    static File Sound;
    
    public Explosion (GamePanel panel){
        super(SpriteBank.get().getSprite(Constantes.explosionRef,1,5,100*1000000), panel);
        ((SpriteAnime)this.sprite).setLooping(false);
        if (Sound == null)
            Sound = new File ("ressources/audio/fx2.wav");
            AudioPlayer audio = AudioPlayer.createPlayer(Sound);
            audio.setVolume(-5f);
            audio.start();
        
    }

    @Override
    public void codeMe() {
        SpriteAnime s = ((SpriteAnime)this.sprite);
        if (!s.isActive()){
            System.out.println("anim explo terminée");
            this.panel.getListEntite().remove(this);
        }
    }
    
    
    
}
