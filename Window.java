package spacegame;

import javax.swing.*;
import java.awt.event.*;

import spacegame.*;
//Our custom JFrame, could add a KeyListener(for keyboard input) or buttons
public class Window extends JFrame implements KeyListener, MouseMotionListener {
    //Our custom JPanel, holds the world, overridden draw function
    public WorldCanvas worldCanvas;

    //Constuctor
    public Window() {
        this.setUndecorated(true);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        int width = this.getWidth();
        worldCanvas = new WorldCanvas(width, this.getHeight());
        this.add(worldCanvas);
        this.worldCanvas.setSize(width, width);
        this.setSize(width, width);
        addKeyListener(this);
        addMouseMotionListener(this);
        this.setVisible(true);
        this.worldCanvas.repaint();
    }


    public void initWorld(){
        worldCanvas.initWorld();
    }

    //Wrapper for repaint, reduces the length of commands in higher level files.
    //Without this we'd have to write window.worldCanvas.repaint() in gameLoop.
    public void drawWorld() {
        worldCanvas.PLAYER = this.PLAYER;
        worldCanvas.world.addStars(worldCanvas.camera);
        worldCanvas.repaint();
    }

    //Another Wrapper
    public void applyPhysics() {
        this.worldCanvas.world.applyPhysics();
    }

    //Controls 

    public void applyControls() {
        if(W) { 
            this.worldCanvas.world.player.push(0, -2);
        }
        if(A) {
            this.worldCanvas.world.player.push(-2, 0);
        }
        if(S) {
            this.worldCanvas.world.player.push(0, 2);
        }
        if(D) {
            this.worldCanvas.world.player.push(2, 0);
        }
        if(SPACE && this.PLAYER) {
            this.worldCanvas.world.player.shoot(this.worldCanvas.world);
        }
    }
    boolean W = false;
    boolean A = false;
    boolean S = false;
    boolean D = false;
    boolean P = false;
    boolean SPACE = false;

    boolean PLAYER = true;

    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 87){
            //w
            this.W = true;
        }
        if(e.getKeyCode() == 83){
            //s
            this.S = true;
        }
        if(e.getKeyCode() == 65){
            //a
            this.A = true;
        }
        if(e.getKeyCode() == 68){
            //d
            this.D = true;
        }
        if(e.getKeyCode() == 80){
            //p
            if(!this.P) {
                this.PLAYER = !this.PLAYER;
            }
            this.P = true;
        }
        if(e.getKeyCode() == 32){
            //space
            this.SPACE = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 87){
            //w
            this.W = false;
        }
        if(e.getKeyCode() == 83){
            //s
            this.S = false;
        }
        if(e.getKeyCode() == 65){
            //a
            this.A = false;
        }
        if(e.getKeyCode() == 68){
            //d
            this.D = false;
        }
        if(e.getKeyCode() == 80){
            //p
            this.P = false;
        }
        if(e.getKeyCode() == 32){
            //space
            this.SPACE = false;
        }
    }
    public void keyTyped(KeyEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }
 
     public void mouseDragged(MouseEvent e) {
    }
}
