package spacegame;

import java.awt.*;
import java.util.Date;
import java.awt.image.*;
import java.awt.geom.AffineTransform;

import spacegame.*;

public class Player extends PhysicsObject {
    private BufferedImage img;

    int lives = 3;

    private Date lastShot = new Date();
    private Boolean lastGun = false;
    private int shotCooldown = 250;
    private double shotSpeed = 10;
    private int shotSize = 3;
    private AffineTransform af = new AffineTransform();

    public Player(double x, double y) {
        img = this.loadImage("Images/player.png");
        this.setDimensions(img.getWidth()*1, img.getHeight()*1);
        this.setCenter(x, y);
        this.setMaxVel(2);
    }

    public void shoot(World world) {
        Date now = new Date();
        if(now.getTime() - lastShot.getTime() >= shotCooldown) {
            this.lastShot = now;
            if(lastGun) {
                lastGun = false;
                world.addParticle(new Projectile(this.x(), this.centerY(), 0, -this.shotSpeed + this.yVel/4, shotSize, Color.YELLOW));
            } else {
                lastGun = true;
                world.addParticle(new Projectile(this.x() + this.width() - shotSize, this.centerY(), 0, -this.shotSpeed + this.yVel/4, shotSize, Color.YELLOW));
            }
        }
    }
       
    
    public void draw(Graphics g, Camera camera){
        if(this.x() < camera.left()){
            this.setPos(camera.left(), this.y());
        } else if(this.x() + this.width() > camera.right()){
            this.setPos(camera.right() - this.width(), this.y());
        }
        if(this.y() < camera.top()){
            this.setPos(this.x(), camera.top());
        } else if(this.y() + this.height() > camera.bottom()){
            this.setPos(this.x(), camera.bottom() - this.height());
        }
        int x = (int) (this.x() - camera.centerX() + camera.width()/2);
        int y = (int) (this.y() - camera.centerY()) + camera.height()/2;
        if(this.img != null){
            af.translate(x, y);
            af.rotate(1.57, this.width(), this.height());
            g.drawImage(this.img, x, y, this.width(), this.height(), null);
            g.setColor(Color.RED);
        }
    }
}