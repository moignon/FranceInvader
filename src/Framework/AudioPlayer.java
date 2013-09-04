/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import franceinvaders.Options;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.net.URL;
import java.util.HashMap;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Mixer;

    

/**
 *
 * 
 * @author John
 */
public class AudioPlayer extends Thread {

    
    private static HashMap sons = new HashMap();
    private static Mixer mixer;
    private static int ThreadCount = 0;
    public static String AUDIO_DIR = "/ressources/audio/";
    
    private URL file;
    private AudioInputStream audioInputStream = null;
    private AudioFormat audioFormat;
    private SourceDataLine line;
    private FloatControl volCtrl;
    
    
    private boolean stop = false,
                    looping = false,
                    end = false,
                    play = false,
                    pause = false;
    
    private float gain = 0;
    
    private Channels chan = Channels.FXs;
    
    private byte bytes[] = new byte[1024];
    private int bytesRead=0;
    
 
    private AudioPlayer (URL ressource){
        if (mixer == null){
            mixer = AudioSystem.getMixer(null);
        }
        file = ressource;
    }
    public static AudioPlayer createPlayer(String ref){
        
        URL sound = AudioPlayer.class.getResource(AUDIO_DIR+ref);
            AudioPlayer audioPlayer = new AudioPlayer(sound);
            audioPlayer.setName("Audio : "+ ref +" #"+ ThreadCount++);
            audioPlayer.init();
            return audioPlayer;
    }
    public void init(){
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
            audioFormat = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            line = (SourceDataLine)mixer.getLine(info);
            line.open();
            volCtrl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
            this.adjustVolume();
            bytes = new byte[1024];
            bytesRead=0;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {}
    }
    
    @Override
    public void run(){
        stop = false;
        play = true;
        line.start();
        while (!end){
            
            if (play){
                try {
                    while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) && !stop && play) {
                        this.adjustVolume();
                        line.write(bytes, 0, bytesRead);
                    }
                    if(bytesRead == -1){
                        if(isLooping()){
                            this.RESTART();
                            this.START();
                        }else{
                            this.STOP();
                        }
                    }
                } catch (IOException ex) {}
            }
            else{
                try {
                    Thread.sleep(99);
                } catch (InterruptedException ex) {}
            }
        }
        
    }
    private void setVolume(double volume){
        if (volume > 6) volume = 6F;
        if (volume < -80)volume = -80F;
        float f = (float) volume;
        volCtrl.setValue(f);
    }
    private void adjustVolume (){
        float optionsVolume;
        if (this.chan == Channels.MUSIC)
            optionsVolume = Options.BgmVolume;
        else 
            optionsVolume = Options.FxVolume;
        float vol = (float) (-80 + this.getGain() + (optionsVolume*Options.master));
        if (vol != volCtrl.getValue()){
            this.setVolume(vol);
        }
    }
    public void START(){
        try{
            this.start();
        }catch (IllegalThreadStateException i){}
        line.start();
        pause = false;
        play = true;
        end = false;
    }
    public void PAUSE(){
        line.stop();
        line.flush();
        pause = true;
        play = false;
    }
    public void STOP (){
        this.end();
        end = true;
    }
    public void RESTART(){
        play = false;
        end = false;
        pause = false;
        line.stop();
        this.init();
    }
    public void end (){
        play = false;
        pause = false;
        stop = true;
        end = true;
        line.stop();
        line.flush();
        line.close();
    }
    public boolean isLooping() {
        return looping;
    }
    public void setLooping(boolean looping) {
        this.looping = looping;
    }
    
    public void setMixer (Mixer mixer){
        if (!mixer.equals(this.mixer)){
            this.mixer = mixer;
            this.init();
        }
    }

    public float getGain() {
        return gain;
    }

    public void setGain(float deltaVolume) {
        this.gain = deltaVolume;
    }

    public Channels getChan() {
        return chan;
    }

    public void setChan(Channels chan) {
        this.chan = chan;
    }
    
    public enum Channels {
    MUSIC,
    FXs;
}
    
 
}



















//
//
//
//
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package Framework;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.DataLine;
//import javax.sound.sampled.LineUnavailableException;
//import javax.sound.sampled.SourceDataLine;
//import javax.sound.sampled.UnsupportedAudioFileException;
//import java.applet.AudioClip;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.HashMap;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;
//import javax.sound.sampled.LineEvent;
//import javax.sound.sampled.LineListener;
//import javax.sound.sampled.Mixer;
//
///**
// *
// * 
// * @author John
// */
//public class AudioPlayer {
//    URL file;
//    Clip clip;
//    AudioInputStream audioInputStream = null;
//    AudioFormat audioFormat;
//    FloatControl volCtrl;
//    boolean stop = false;
//    boolean existe = false;
//    private boolean looping = true;
//    private static HashMap sons = new HashMap();
//    private static Mixer mixer;
//    static String AUDIO_DIR = "/ressources/audio/";
//    
// 
//    private AudioPlayer(URL ressource){
//        file = ressource;
//    }
//    
//    public static AudioPlayer createPlayer(String ref){
//            URL sound = AudioPlayer.class.getResource(AUDIO_DIR+ref);
//            AudioPlayer audioPlayer = new AudioPlayer(sound);
//            audioPlayer.init();
//            AudioPlayer.sons.put(ref, audioPlayer);
//            return audioPlayer;
//        
//    }
//    
//    public void init(){
//        
//        try {
//            audioInputStream = AudioSystem.getAudioInputStream(file);
//        } catch (UnsupportedAudioFileException ex) {
//            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        //on recuper le format de fichier
//            audioFormat = audioInputStream.getFormat();
//        
//        //On specifie le type de DataLine ici une SourceDataLine pour la lecture, (targetDataLine pour l'enregistrement)
//        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
//        
//        
//        try {
//            clip = AudioSystem.getClip();
//            try {
//                clip.open(audioInputStream);
//                clip.addLineListener(new LineListener() {
//
//                    @Override
//                    public void update(LineEvent le) {
//                        if ( (clip.getFramePosition() >= clip.getFrameLength()) && !looping || stop)
//                            le.getLine().close();
//                    }
//                });
//            } catch (IOException ex) {
//                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            volCtrl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//        }
//        catch (LineUnavailableException ex) {}
//    }
//    public void setVolume(Float volume){
//        if (volume > 6) volume = 6F;
//        volCtrl.setValue(volume);
//    }
//    public void START (){
//        if (clip.isOpen())
//            clip.start();
//        else {
//                this.init();
//                clip.setFramePosition(0);
//                clip.start();
//        }
//    }
//    public void PAUSE() {
//        clip.stop();
//    }
//    public void STOP(){
//        clip.stop();
//        clip.flush();
//        clip.setFramePosition(0);
//        stop = true;
//    }
//    public void restart() {
//        clip.setFramePosition(0);
//    }
//    public boolean isLooping() {
//        return looping;
//    }
//    public void setLooping(boolean looping) {
//        this.looping = looping;
//        if (looping)
//            clip.loop(Clip.LOOP_CONTINUOUSLY);
//        else
//            clip.loop(0);
//    }
//    public void setMixer (Mixer mixer){
//        this.mixer = mixer;
//    }
//}
