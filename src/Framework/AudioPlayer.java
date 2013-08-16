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
import javax.sound.sampled.FloatControl;

/**
 *
 * 
 * @author John
 */
public class AudioPlayer extends Thread {
    File file;
    AudioInputStream audioInputStream = null;
    SourceDataLine line;
    AudioFormat audioFormat;
    FloatControl volCtrl;
    boolean stop = false;
    boolean existe = false;
    private boolean looping = true;
    
 
    private AudioPlayer (File f){
        this.file = f;
    }
    
    public static AudioPlayer createPlayer(File f){
        AudioPlayer a = new AudioPlayer(f);
        a.init();
        return a;
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
        
        
        //on recupere le DataLine
        try {
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open();
            volCtrl = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setVolume(Float volume){
        if (volume > 6) volume = 6F;
        volCtrl.setValue(volume);
    }
    
    @Override
    public void run(){
        try {
            stop = false;
            try {
                line.open();
            } catch (LineUnavailableException ex) {
                Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            line.start();
                byte bytes[] = new byte[1024];
                int bytesRead=0;
                while (((bytesRead = audioInputStream.read(bytes, 0, bytes.length)) != -1) && !stop) {
                    line.write(bytes, 0, bytesRead);
                }
            
            line.close();
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void setFile (File file){
        this.file = file;
    }
    public SourceDataLine getLine(){
        return line;
    }

    public void pause() {
        this.stop = true;
    }

    public void restart() {
        this.stop = false;
    }

    /**
     * @return the looping
     */
    public boolean isLooping() {
        return looping;
    }

    /**
     * @param looping the looping to set
     */
    public void setLooping(boolean looping) {
        this.looping = looping;
    }
}
