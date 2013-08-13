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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import Math2d.Vector;

public abstract class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener{
    
    
    
    protected Vector mousePos = new Vector(0,0);
    protected boolean [] keys = new boolean[256];
    protected boolean downKey = false;
    protected boolean upKey = false;
    protected boolean leftKey = false; 
    protected boolean rightKey = false,
                      echap = false,
                      enter = false;
    
    private static int WIDTH2 = 1250;
    private static int HEIGHT2 = 875;
    private long frameRate = 1000/90;
    
    private Boolean running = false ;
    
    protected Graphics2D gBuffer;
    private BufferedImage buffer = null ;
    protected boolean gameOver = false; 
    private String gameOverMsg ="Gameover , Noob !";
    private double fps = 0 ;
    private Thread thread;
    protected AudioPlayer audio;
    protected boolean pause;
    private ArrayList <Entite> listEntite;
    
    public static long previousTime, dTime, sleepTime,currentTime;
    
    private int score = 0;
    
    Boolean packed = false;
    
    public GamePanel (){
        setBackground (Color.BLACK);
        setPreferredSize(new Dimension(WIDTH2,HEIGHT2) );
        setFocusable(true);
        requestFocus();
        readyToClose();
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        listEntite = new ArrayList <>();
        Boolean packed = false;
        GamePanel.currentTime = System.currentTimeMillis();
        initKeys();
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
    public void stopGame (){
        running = false;
    }
    public void pauseGame (){
        pause = !pause;
    }
    
    @Override
    public void run() {
        if(!packed)waitPack();
        
        
        long []times = new long [20];
        int i = 0;
        
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
                currentTime = System.currentTimeMillis();
                dTime = currentTime - previousTime;
                
                sleepTime = frameRate - dTime;
                times[i] = dTime;
                
                long total = 0;
                for(int j = 0; j < times.length; j++){
                    total = total + times[j];
                }
                total = total/times.length;
                if (total > 0)
                    fps =  1000/total;
                i++;
                if(i >= times.length) i = 0;
                if (sleepTime <=0) sleepTime = 5;
            
                paintOnScreen ();
                previousTime = currentTime;
            
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
        if (ke.getKeyCode() == java.awt.event.KeyEvent.VK_ESCAPE) this.pauseGame();
        keys[ke.getKeyCode()] = true;
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        keys[ke.getKeyCode()] = false;
    }
    @Override
    public void keyTyped(KeyEvent ke) {}
    
    @Override
    public void mousePressed(MouseEvent e) {
        keys[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        keys[e.getButton()] = false;    
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.setXY(e.getX(), e.getY());
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
    }
        
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

    public boolean [] getKeys()
    {
        return keys;
    }

    public void initKeys()
    {
        for (int i = 0 ; i < keys.length; i++)
            keys[i] = false;
    }
    
    public Vector getMouseVector()
    {
        return (mousePos);
    }
}
