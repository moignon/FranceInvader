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
    Weapon armeEquipee,armeSecondaire;
    
    Boolean firestVagueOff = false;
   
    public Level1() {
        super();
        
        this.armeEquipee = new TirSimple();
        this.armeSecondaire = new DaddyBoom();
        background = new Background(Constantes.backgroundRef);
        
        sPlayer = new SpriteAnime(ImageBank.get().getImages(Constantes.playerRef, 3, 4),60 );
//        SpriteFlamme = ImageBank.get().getSprite(Constantes.flammeRef,1,4,10);
        
        player1 = new Player(sPlayer, this);
        player1.setCenteredPostion(this.getWIDTH()/2, this.getHEIGHT()- player1.h);
        
        Entite Vague = VagueAsteroid.createVagueAsteroid(this);
        this.getListEntite().add(Vague);
        

        
        audio = AudioPlayer.createPlayer(new File("ressources/audio/RoccoW_-_Break-A-Leg.wav"));
        audio.start();    

    }
            
    @Override
    public void gameUpdate(){
        
        if (firestVagueOff){
            double rand = Math.random();
            if (rand < 0.02){
                jamel j = new jamel(this);
                this.add(j);
            }
        }
        
        
        if (rightKey && player1.getX()+player1.getL()<this.getWIDTH()-5){
            player1.setXspeed(7);
            sPlayer.setAnim(1);
        }
        else {
            player1.setXspeed(0);
            sPlayer.setAnim(0);
        }
        if (leftKey&& player1.getX()>5){
            player1.setXspeed(-7);
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
        if(downKey){
            player1.fire(armeSecondaire);
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
        //this.setGameOverMessage("Bravo");
        //this.gameOver = true;
        firestVagueOff = true;
        this.armeEquipee = new TriBlaster();
        Entite Vague = VagueAsteroid.createVagueAsteroid(this);
        this.getListEntite().add(Vague);
    }

    


}
