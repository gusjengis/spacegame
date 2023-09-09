package spacegame;

import java.awt.*;

import spacegame.*;
public class Projectile extends Particle {
    public Color color; 

    public Projectile(double x, double y, double xForce, double yForce, int size, Color color) {
        this.toggleFriction();
        this.setPos(x, y);
        this.setDimensions(size, size);
        this.push(xForce, yForce);
        this.color = color;
    }

    public void draw(Graphics g, Camera camera) {
        g.setColor(color);
        g.fillRect((int) (this.x()  - camera.centerX() + camera.width()/2), (int) (this.y()  - camera.centerY() + camera.height()/2), this.width(), this.height());
    }
}
