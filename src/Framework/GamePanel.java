package Framework;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import franceinvaders.FranceInvaders;
import java.awt.Frame;

public abstract class GamePanel extends JPanel implements Runnable, KeyListener{
    
    protected boolean downKey = false;
    protected boolean upKey = false;
    protected boolean leftKey = false; 
    protected boolean rightKey = false,
                      echap = false;
    
    private static int WIDTH2 = 1250;
    private static int HEIGHT2 = 875;
    private long frameRate = 1000/85;
    
    private Boolean running = false ;
    
    protected Graphics2D gBuffer;
    private BufferedImage buffer = null ;
    protected boolean gameOver = false; 
    private String gameOverMsg ="Gameover , Noob !";
    private double fps = 0 ;
    private Thread thread;
    protected AudioPlayer audio;
    private boolean pause;
    private ArrayList <Entite> listEntite;
    
    private int score = 0;
    
    Boolean packed = false;
    
    public GamePanel (){
        setBackground (Color.BLACK);
        setPreferredSize(new Dimension(WIDTH2,HEIGHT2) );
        setFocusable(true);
        requestFocus();
        readyToClose();
        addKeyListener(this);
        listEntite = new ArrayList <>();
        Boolean packed = false;
    }  
    public int getWIDTH (){
        return WIDTH2;
        //return this.getHeight();
    } 
    public int getHEIGHT (){
        return HEIGHT2;
        //return this.getWidth();
    }
    private void readyToClose() {
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed (KeyEvent e){
                int code = e.getKeyCode();
                    if ((code == KeyEvent.VK_ESCAPE) ||(code == KeyEvent.VK_ESCAPE) ||(code == KeyEvent.VK_ESCAPE) ||(code == KeyEvent.VK_ESCAPE)){
                    }
                }
            });
    }
    
    @Override
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    
    public void startGame (){
        if (thread == null || !running){
            pause = false;
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    private void stopGame (){
        running = false;
    }
    private void pauseGame (){
        pause = !pause;
    }
    
    @Override
    public void run() {
        if(!packed)waitPack();
        long previousTime, dTime, sleepTime;
        previousTime = System.currentTimeMillis();
        while(running){
            if (!pause){
                if (gameOver){
                gameOverMessage(gBuffer);
                }
                else{
                    gameUpdate();
                    gameRenderer ();
                }
                dTime = System.currentTimeMillis() - previousTime;
                sleepTime = frameRate - dTime;
                fps =  1000/dTime;
           
                if (sleepTime <=0) sleepTime = 5;
            
                paintOnScreen ();
                previousTime = System.currentTimeMillis();
            
                try{
                    Thread.sleep(sleepTime);
                }
                catch (InterruptedException ignored){}
                }
            else{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
        gameOver();
    }
    
    
    public void setGameOverMessage(String msg){
        gameOverMsg = msg;
    }

    public void gameUpdate() {};

    protected void gameRenderer() {
        
        //create the Buffer
        if( gBuffer == null) {
            buffer = new BufferedImage(WIDTH2,HEIGHT2, BufferedImage.TYPE_INT_ARGB);  
            gBuffer = buffer.createGraphics();
        }
        
        //clear the Background
        gBuffer.setColor(Color.white);
        gBuffer.fillRect(0, 0, WIDTH2, HEIGHT2);
        
        
        blitEntites ();
        //draw all the Sprites

        gBuffer.setFont(new Font(null,20,20));
        gBuffer.drawString("FPS :"+ fps, 50, 50);
        
        if(gameOver){
            gameOverMessage (gBuffer);
        }  
    }
    
    

    private void gameOverMessage(Graphics gBuffer) {
        int msgSize = gameOverMsg.length();
        int x = WIDTH2/2;
        int y = HEIGHT2/2 - msgSize/2;
        gBuffer.drawString(gameOverMsg, x, y);
    }
    
    private void paintOnScreen(){
        Graphics g;
        try{
            g = this.getGraphics();
            Frame f = franceinvaders.FranceInvaders.jeu;
            g.drawImage(buffer, 0, 0,f.getWidth(),f.getHeight(), null);
            g.dispose(); 
        }catch (Exception e){}
    }        
    
    
    @Override
    public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN) downKey = true;
            if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_UP) upKey = true;
            if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT) leftKey = true;
            if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT) rightKey = true;
            if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) {
                echap = true;
                this.pauseGame();
            }
            
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_DOWN)downKey = false;
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_UP)upKey = false;
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_LEFT)leftKey = false;
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_RIGHT)rightKey = false;
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) echap = false;
    }
    @Override
    public void keyTyped(KeyEvent ke) {}
    
    public void remove(Entite e){
        listEntite.remove(e);
    }
    public void add (Entite e){
        listEntite.add(e);
    }

    /**
     *Apeller les methode de peinture de tout ce qui doit etre affiché
     */
    protected abstract void blitEntites();

    /**
     *L'action a realiser lorsque LE NIVEAU est terminé
     */
    public abstract void gameOver();

    /**
     * @return the listEntite
     */
    public ArrayList <Entite> getListEntite() {
        return listEntite;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void addScore(int score) {
        this.score = this.score + score;
    }

    private void waitPack() {
        while(this.getHeight() == 0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignorée) {}
        }
    }

    
    
    
    }
