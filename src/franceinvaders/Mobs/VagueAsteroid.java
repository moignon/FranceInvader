/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package franceinvaders.Mobs;

import Framework.Entite;
import Framework.GamePanel;
import Framework.Sprite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author John
 */
public class VagueAsteroid extends Psy {
    
    ArrayList <Psy> Psys;
    private final int nbLignes = 4,ecartLigne = 75,
                      nbColonnes = 12, ecartColonne = 55, dG = 300,
                      pixelDescente = 80;
    
    static int vitesseDepart = 1;
    int vitesseActuelle;
    public double coteGauche, coteDroit;
    private int pixelsDescendues;
    private int pixelsDescendus;
    Boolean droite = false, gauche = false, bas = false, haut = false;
    ArrayList <Mob> MobsTouché = null;
    private boolean wait = false;
    private boolean invulnerable = true;
    long recoveryTime = 1500,
         deltaTime = 0,
         baseTime;
    
    
    private VagueAsteroid (GamePanel panel) {
        super(panel);
        Psys = new ArrayList <>();
        MobsTouché = new ArrayList();
        vitesseActuelle = vitesseDepart;
        for (int j=30 ; j <= nbLignes*ecartLigne; j+=ecartLigne){
            for (int i=dG ; i <nbColonnes*ecartColonne+dG; i+=ecartColonne){
                Psy psy = new Psy (panel);
                psy.setPosition(i,j);
                Psys.add(psy);
            }
        }
        baseTime =  GamePanel.currentTime;
    }
    
    public static VagueAsteroid createVagueAsteroid (GamePanel panel) {
        VagueAsteroid vague = new VagueAsteroid (panel);
        vague.setXspeed(vitesseDepart);
        vague.coteGauche = 30;
        vague.coteDroit = 30+vague.nbLignes*vague.ecartLigne;
        return vague;
    }
    
    @Override
    public void update (){
        if (GamePanel.currentTime - baseTime > recoveryTime){
            invulnerable = false;
            MobsTouché.clear();
        }
        
        if ( (Psys.size() == 30 || Psys.size() == 20 || Psys.size() == 10 ) && wait == false){
            wait = true ;
            if(bas){
                if (vitesseActuelle < 0)vitesseActuelle--;
                else vitesseActuelle++;
                this.setYspeed(Math.abs(vitesseActuelle));
            }
            if(droite)this.setXspeed(vitesseActuelle++);
            if(gauche)this.setXspeed(vitesseActuelle--);
        }
            
            
        if (bas){
            if (descenteTerminée()){
                setYspeed(0);
                vitesseActuelle = -vitesseActuelle;
                setXspeed(vitesseActuelle);
            }
            else{
                pixelsDescendus += getYspeed();
            }  
        }
        //si on ne descend pas
        else {
            if (!peutBougerHorizontal()){
                setXspeed(0);
                setYspeed(Math.abs(vitesseActuelle));
            }
        }
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).update();
        }
    }
    
    @Override
    public void blit(Graphics2D gBuffer) {        
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).blit(gBuffer);
        }
    }
    @Override
    public void setXspeed(int speed) {
        this.speed.setX(speed);
        droite = speed>0;
        gauche = speed<0;
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).setXspeed(speed);
        }
    }
    @Override
    public void setYspeed(int speed){
        this.speed.setY(speed);
        bas = speed>0;
        haut = speed<0;
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).setYspeed(speed);
        }
    }
    
    @Override
    public boolean collidesWith(Entite e){
        Boolean hit = false;
        for(int i = 0; i < Psys.size(); i ++){
            if (Psys.get(i).collidesWith(e)){
                MobsTouché.add(Psys.get(i));
                hit = true;
            }
        }
        return hit;
    }
    
    @Override
    public void takeDmg(int hitDmg) {
        if (invulnerable) {
            MobsTouché.clear();
            return;
        }
        for (int i = 0 ; i < MobsTouché.size(); i ++){
            Mob MobTouché = MobsTouché.get(i);
            MobTouché.takeDmg(hitDmg);
            if (MobTouché.getLife() <= 0){
                MobTouché.dead();
                Psys.remove(MobTouché);
                wait = false;
            }
        }
        MobsTouché.clear();
        if (this.Psys.size() == 0)
            dead ();
    }

    @Override
    public void dead() {
        panel.gameOver();
    }
    
    
    
    
    
    


    @Override
    public void codeMe() {};
    
    private boolean descenteTerminée() {
        if (pixelsDescendus >= pixelDescente){
            pixelsDescendus = 0 ;
            return true;
        }
        return false;
    }
    
    private boolean peutBougerHorizontal() {
        for (int i = 0 ; i < Psys.size(); i++){
            Psy psy = Psys.get(i);
            if (psy.getX() < 20 || psy.getX()+psy.getL()>panel.getWIDTH()-20)
                return false;
            }
        return true;
    }
    
    
    
    
}
