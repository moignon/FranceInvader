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
import Animations.Papa;
import franceinvaders.Weapons.CashIcon;
import franceinvaders.Weapons.DaddyBoom;
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
    VagueAsteroid vague;
    
    Boolean firstVagueOff = false;
   
    public Level1() {
        super();
       
        background = new Background(Constantes.backgroundRef);
        
        sPlayer = new SpriteAnime(ImageBank.get().getImages(Constantes.playerRef, 3, 4),60 );
//        SpriteFlamme = ImageBank.get().getSprite(Constantes.flammeRef,1,4,10);
        
        player1 = new Player(sPlayer, this);
        player1.setPosition(this.getWIDTH()/2,this.getHEIGHT()- player1.h);
        
        CashIcon cash = new CashIcon (this);
        cash.setPosition(getWIDTH()/2, getHEIGHT()/2);
        getListEntite().add(cash);
        
        //vague = VagueAsteroid.createVagueAsteroid(this);
       // this.getListEntite().add(vague);
        

        
        audio = AudioPlayer.createPlayer(new File("ressources/audio/RoccoW_-_Break-A-Leg.wav"));
        audio.start();

    }
            
    @Override
    public void gameUpdate(){
        
//        if (!getListEntite().contains(vague)){
//            firstVagueOff = true;
//            vague = VagueAsteroid.createVagueAsteroid(this);
//            getListEntite().add(vague);
//            player1.armeEquipee = new TriBlaster();
//        }
//        
//        if (firstVagueOff){
//            
//            double rand = Math.random();
//            if (rand < 0.05){
//                jamel j = new jamel(this);
//                this.add(j);
//            }
//        }
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
        this.setGameOverMessage("Bravo Newb");
        this.gameOver = true;
    }
    
    
//    @Override
//    public void pauseGame (){
//        pause = !pause;
//        if(pause){
//            audio.pause();
//        }
//        else {
//            audio = AudioPlayer.createPlayer(new File("ressources/audio/RoccoW_-_Break-A-Leg.wav"));
//            audio.start();
//        }
//    }
    
    
        @Override
    public void pauseGame (){
        pause = !pause;
        if(pause){
            audio.suspend();
        }
        else {
            audio.resume();
        }
    }

    


}
