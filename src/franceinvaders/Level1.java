/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders;
import Framework.AudioPlayer;
import Framework.Background;
import Framework.GamePanel;
import Framework.Entite;
import Framework.Sprite;
import Framework.ImageBank;
import Framework.SpriteAnime;
import franceinvaders.Mobs.Mob;
import franceinvaders.Mobs.Psy;
import franceinvaders.Mobs.VagueAsteroid;
import franceinvaders.Mobs.jamel;
import franceinvaders.ProjectilesEntites.Flamme;
import franceinvaders.Weapons.TirSimple;
import franceinvaders.Weapons.TriBlaster;
import franceinvaders.Weapons.Weapon;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
/**
 * @author John
 */
  

public class Level1 extends GamePanel {
    
    Player player1;
    Background background;
    Mob  jamel, psy;
    Sprite SpriteFlamme;
    SpriteAnime sPlayer;
    Weapon armeEquipee;
   
    public Level1() {
        super();
        this.armeEquipee = new TriBlaster();
        background = new Background(Constantes.backgroundRef);
        
        sPlayer = new SpriteAnime(ImageBank.get().getImages(Constantes.playerRef, 3, 4),60 );
//        SpriteFlamme = ImageBank.get().getSprite(Constantes.flammeRef,1,4,10);
        
        player1 = new Player(sPlayer, this);
        player1.setCenteredPostion(this.getWIDTH()/2, this.getHEIGHT()- player1.h);
        
        Entite Vague = VagueAsteroid.createVagueAsteroid(this);
        this.getListEntite().add(Vague);
        

        
        audio = AudioPlayer.createPlayer(new File("ressources/audio/RoccoW_-_Break-A-Leg.wav"));
        audio.start();
        
//        
//        Flamme test = new Flamme(SpriteBank.get().getSprite(Constantes.flammeRef,1,4,60*1000000), this);
//        test.setCenteredPostion(500,800);
//        test.setYspeed(0);
//        test.setYspeed(0);
//        this.getListEntite().add(test);
//        

    }
            
    @Override
    public void gameUpdate(){
        
        if (rightKey && player1.getX()+player1.getL()<this.getWIDTH()-5){
            player1.setXspeed(5);
            sPlayer.setAnim(1);
        }
        else {
            player1.setXspeed(0);
            sPlayer.setAnim(0);
        }
        if (leftKey&& player1.getX()>5){
            player1.setXspeed(-5);
            sPlayer.setAnim(2);
        }
        else if (!rightKey){
            player1.setXspeed(0);
            sPlayer.setAnim(0);
        }
        if (!rightKey && !leftKey) player1.setXspeed(0);
        if (upKey){
            player1.fire(armeEquipee);
        }
        
        for(int i = 0; i<getListEntite().size(); i++){
            getListEntite().get(i).update();
        }
        player1.update();
        background.update();
    }
    
    @Override
    public void blitEntites () {
        background.blit(gBuffer);
        player1.blit(gBuffer);
        for (int i = 0; i<getListEntite().size(); i ++){
            getListEntite().get(i).blit(gBuffer);
        }
        gBuffer.setFont(new Font(null,20,20));
        gBuffer.drawString("SCORE :"+ getScore(), 1000, 50);
        
    }
    
    @Override
    public void gameOver() {
        this.setGameOverMessage("Bravo");
        //this.gameOver = true;
        Entite Vague = VagueAsteroid.createVagueAsteroid(this);
        this.getListEntite().add(Vague);
    }

    


}
