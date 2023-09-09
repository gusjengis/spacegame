package spacegame;

import java.awt.*;

import spacegame.*;
public class Star extends Particle {
    private Color color;
    public Star(double x, double y, double yForce, double radius) {
        this.toggleFriction();
        this.setPos(x, y);
        this.setDimensions((int) radius, (int) radius);
        this.push(0, yForce);
        this.color = Color.WHITE;//new Color((int) (255*Math.random()), (int) (255*Math.random()), (int) (255*Math.random()));
    }

    public void draw(Graphics g, Camera camera) {
        g.setColor(color);
        g.fillRect((int) (this.x() - camera.centerX() + camera.width()/2), (int) (this.y()  - camera.centerY() + camera.height()/2), this.width(), this.height());
    }
}
