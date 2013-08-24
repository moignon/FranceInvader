/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Framework;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.applet.AudioClip;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.Mixer;

/**
 *
 * 
 * @author John
 */
public class Audio {
    URL file;
    Clip clip;
    AudioInputStream audioInputStream = null;
    AudioFormat audioFormat;
    FloatControl volCtrl;
    boolean stop = false;
    boolean existe = false;
    private boolean looping = true;
    private static HashMap sons = new HashMap();
    private static Mixer mixer;
    
 
    private Audio(URL ressource){
        file = ressource;
    }
    
    public static AudioPlayer createPlayer(String ref){
        return null;
//            URL sound = AudioPlayer.class.getResource("/"+ref);
//            AudioPlayer audioPlayer = new AudioPlayer(sound);
//            audioPlayer.init();
//            AudioPlayer.sons.put(ref, audioPlayer);
//            return audioPlayer;
//        
    }
    
    public void init(){
        
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //on recuper le format de fichier
            audioFormat = audioInputStream.getFormat();
        
        //On specifie le type de DataLine ici une SourceDataLine pour la lecture, (targetDataLine pour l'enregistrement)
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        
        
        try {
            clip = AudioSystem.getClip();
            try {
                clip.open(audioInputStream);
                clip.addLineListener(new LineListener() {

                    @Override
                    public void update(LineEvent le) {
                        if ( (clip.getFramePosition() >= clip.getFrameLength()) && !looping || stop)
                            le.getLine().close();
                    }
                });
            } catch (IOException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            volCtrl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }
        catch (LineUnavailableException ex) {}
    }
    public void setVolume(Float volume){
        if (volume > 6) volume = 6F;
        volCtrl.setValue(volume);
    }
    public void start (){
        if (clip.isOpen())
            clip.start();
        else {
                this.init();
                clip.setFramePosition(0);
                clip.start();
        }
    }
    public void pause() {
        clip.stop();
    }
    public void stop(){
        clip.stop();
        clip.flush();
        clip.setFramePosition(0);
        stop = true;
    }
    public void restart() {
        clip.setFramePosition(0);
    }
    public boolean isLooping() {
        return looping;
    }
    public void setLooping(boolean looping) {
        this.looping = looping;
        if (looping)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        else
            clip.loop(0);
    }
    public void setMixer (Mixer mixer){
        this.mixer = mixer;
    }
}
