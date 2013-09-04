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
import franceinvaders.Mobs.VagueAsteroid;
import franceinvaders.Mobs.jamel;
import franceinvaders.Weapons.CashIcon;
import franceinvaders.Weapons.MissileGuide;
import franceinvaders.Weapons.TirSimple;
import franceinvaders.Weapons.Tourbilol;
import franceinvaders.Weapons.TriBlaster;
import java.awt.Cursor;
import java.awt.Font;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import Framework.Camera;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
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
    CashIcon cash;
    Camera cameraP1;
    
    Boolean firstVagueOff = false;
    boolean cashh = false;
   
    public Level1(GameFrame frame) {
        super(frame);
       
        background = new Background(Constantes.backgroundRef);
        
        sPlayer = new SpriteAnime(ImageBank.get().getImages(Constantes.playerRef, 3, 4),60 );
//        SpriteFlamme = ImageBank.get().getSprite(Constantes.flammeRef,1,4,10);
        
        
        
        
        player1 = new Player(sPlayer, this);
        player1.setPosition(this.getWIDTH()/2,this.getHEIGHT()- player1.h);
        
        player1.armeEquipee = new TriBlaster();
        cameraP1 = new Camera(null , this);
        cameraP1.center(player1);
        cameraP1.follow(player1);
        this.cam = cameraP1;
        getListEntite().add(cameraP1);
        player1.setDefaultCamera(cameraP1);
        cash = new CashIcon (this);
        cash.setPosition(getWIDTH()/2, getHEIGHT()/2);
        getListEntite().add(cash);
        
        jamel j = new jamel(this);
        j.setPosition(200, 500);
        j.setXspeed(0);
        j.setYspeed(0);
        getListEntite().add(j);
        
//        Psy p = new Psy(this);
//        p.setPosition(500, 500);
//        getListEntite().add(p);
        
//        vague = VagueAsteroid.createVagueAsteroid(this);
//        this.getListEntite().add(vague);
        

        
        audio = AudioPlayer.createPlayer(Constantes.Level1BgmRef);
        audio.setLooping(true);
        audio.setChan(AudioPlayer.Channels.MUSIC);
        audio.START();

    }
            
    @Override
    public void gameUpdate(){
        if (!getListEntite().contains(cash)){
            if (firstVagueOff == false && !getListEntite().contains(vague) && !cashh){
                vague = VagueAsteroid.createVagueAsteroid(this);
                getListEntite().add(vague);
                cashh = true;
            }
            if ((!getListEntite().contains(vague))){
                firstVagueOff = true;
                vague = VagueAsteroid.createVagueAsteroid(this);
                getListEntite().add(vague);
              //  player1.armeEquipee = new TriBlaster();
                player1.armeSecondaire = new Tourbilol(player1);
                
            }
        }
        
        
        
        if (firstVagueOff && Math.random() < 0.2)
            this.add(new jamel(this));
        
        for(int i = 0; i<getListEntite().size(); i++){
            getListEntite().get(i).update();
        }
        player1.update();
        background.update();
    }
    
    @Override
    public void blitEntites () {
        background.blit(gBuffer, this.cam);
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
    
    
        @Override
    public void pauseGame (){
        pause = !pause;
        if(pause){
            audio.PAUSE();
        }
        else {
            audio.START();
        }
    }

    public Entite getPlayer1() {
        return this.player1;
    }

        @Override
    public void mouseEntered(MouseEvent e) {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
    }
    


}
