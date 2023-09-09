package spacegame;

import javax.swing.Timer;
import java.awt.*;

import spacegame.*;

public class Main {
    //global vars, stuff like a window
    private static Window window;
    // static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    // static int height = gd.getDisplayMode().getHeight();

    public static void main(String[] args) {
        //Initializing Code
        System.setProperty("sun.java2d.uiScale", "1"); // Sets integer scaling, at 4k I'm liking "4"(720p) for gameplay and "2"(1080p) or "4"(2160p) for looking at stars. Try different values to see what works on your monitor.
        window = new Window();
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment()
        .getDefaultScreenDevice();
        // device.setFullScreenWindow(window);



        //Start main loop
        Timer gameLoop = new Timer(7, (e) -> gameLoop());
        window.initWorld();
        gameLoop.start();
    }

    private static void gameLoop(){

        //Update positions/states
        window.applyControls();
        window.applyPhysics();

        //Draw
        window.drawWorld();

    }
}