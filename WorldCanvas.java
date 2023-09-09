package spacegame;

import javax.swing.*;
import java.awt.*;

import spacegame.*;
public class WorldCanvas extends JPanel {
    //super important, camera object is used to figure out what to draw and where to draw it
    public Camera camera = new Camera();
    //The world, holds all objects, player, projectiles, enemies
    public World world = new World();

    public boolean PLAYER = true;

    //Constructor
    public WorldCanvas(int width, int height) {
        this.setOpaque(false);
        this.setMaximumSize(new Dimension(width, height));
        camera.setDimensions(width, height);
        camera.setCenter(0, 0);
    }

    public void initWorld(){
        camera.setSize(this.getSize());
        world.initWorld(camera);
    }

    //This is the function that gets run when we call repaint()
    @Override
    public void paintComponent(Graphics g) {
        //In case the window is resized
        camera.setSize(this.getSize());

        //clear canvas and set background to black
        g.clearRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        world.PLAYER = this.PLAYER;

        //draw world
        world.draw(g, camera);
    }
}
