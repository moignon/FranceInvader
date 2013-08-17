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
    static VagueAsteroid v;
    public  ArrayList <Psy> Psys;
    private final int nbLignes = 4,ecartLigne = 75,
                      nbColonnes = 12, ecartColonne = 55, dG = 300,
                      pixelDescente = 80;
    
    static int vitesseDepart = 1;
    int vitesseActuelle;
    public double coteGauche, coteDroit;
    private int pixelsDescendues;
    private int pixelsDescendus;
    Boolean droite = false, gauche = false, bas = false, haut = false;
    
    private boolean wait = false;
    private boolean invulnerable = true;
    long recoveryTime = 1500,
         deltaTime = 0,
         baseTime;
    
    
    private VagueAsteroid (GamePanel panel) {
        super(panel);
        Psys = new ArrayList <>();
        vitesseActuelle = vitesseDepart;
        for (int j=30 ; j <= nbLignes*ecartLigne; j+=ecartLigne){
            for (int i=dG ; i <nbColonnes*ecartColonne+dG; i+=ecartColonne){
                Psy psy = new Psy (panel);
                psy.setPosition(i,j);
                Psys.add(psy);
                panel.getListEntite().add(psy);
            }
        }
        baseTime =  panel.getCurrentTime();
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
        
        boolean b = false;
        for (int i = 0 ; i < Psys.size(); i ++){
            if (panel.getListEntite().contains(Psys.get(i))){
                b = true;
            }
        }
        if(!b) this.dead();
        
    }
    
    @Override
    public void blit(Graphics2D gBuffer) { 
    }
    @Override
    public void setXspeed(double speed) {
        this.speed.setX(speed);
        droite = speed>0;
        gauche = speed<0;
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).setXspeed(speed);
        }
    }
    @Override
    public void setYspeed(double speed){
        this.speed.setY(speed);
        bas = speed>0;
        haut = speed<0;
        for(int i = 0; i < Psys.size(); i ++){
            Psys.get(i).setYspeed(speed);
        }
    }
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
    
    @Override
    public void takeDmg(int hitDmg) {
    }
        
    @Override
    public void trollNoobs() {};
    
    
    
}
