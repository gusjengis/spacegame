package spacegame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import spacegame.*;
public class World {
    //Array of visbile objects, meaning they must have a .draw() function
    private ArrayList<VisibleObject> objects = new ArrayList<VisibleObject>();
    public ArrayList<Particle> particles = new ArrayList<Particle>();
    public Player player = new Player(0,0);
    public boolean PLAYER = true;
    private Date lastTick = new Date();

    //Adds visible object to the objects array
    public void addObject(VisibleObject object){
        objects.add(object);
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }

    public void initWorld(Camera camera){
        // double speed = 0.2;
        // boolean loop = true;
        // int prevParticleLen = this.particles.size();
        // int particleLen = this.particles.size();
        // // for(double j = camera.height(); j>0; j--){
        // while(loop){
        //     int newStars = (int) (Math.ceil(speed*3*Math.random()));
        //     for(int i = 0; i < newStars*2; i++) {
        //         double xPosition = Math.random() * (camera.right() - camera.left()) + camera.left();
        //         double radius = Math.ceil(Math.random() * 2);
        //         double velocity = speed*(radius - 0.99*Math.random());
        //         this.addParticle(new Star(xPosition, camera.y(), velocity, radius));
        //     }
        //     prevParticleLen = this.particles.size();
        //     this.applyPhysics(7);
        //     particleLen = this.particles.size();
        //     if(particleLen<prevParticleLen){
        //         loop = false;
        //     }
        // }
        // System.out.println(camera.bottom() - camera.top());
    }

    public void addStars(Camera camera) {
        double speed = 0.2;
        int newStars = (int) (Math.ceil(speed*3*Math.random()));
        for(int i = 0; i < newStars; i++) {
            double xPosition = Math.random() * (camera.right() - camera.left()) + camera.left();
            double radius = Math.ceil(Math.random() * 2);
            double velocity = speed*(radius - 0.99*Math.random());
            this.addParticle(new Star(xPosition, camera.y(), velocity, radius));
        }
    }

    public void applyPhysics() {
        Date now = new Date();
        int deltaT = (int) (now.getTime() - this.lastTick.getTime());
        this.lastTick = now;
        this.player.applyPhysics(deltaT);
        for(Particle particle : this.particles) {
            try {
                particle.applyPhysics(deltaT);
            } catch (NullPointerException e){

            }
        }
    }

    public void applyPhysics(int deltaT) {
        Date now = new Date();
        this.lastTick = now;
        this.player.applyPhysics(deltaT);
        for(Particle particle : this.particles) {
            try {
                particle.applyPhysics(deltaT);
            } catch (NullPointerException e){
                
            }
        }
    }

    //draws all objects the camera can see
    public void draw(Graphics g, Camera camera) {
        camera.setCenter(0, 0);
        for(VisibleObject object : objects){
            if(object.isVisible(camera)) {
                object.draw(g, camera);
            }
        }
        for(int i = 0; i < this.particles.size(); i++) {
            Particle particle = this.particles.get(i);
            if(particle.isVisible(camera)) {
                particle.draw(g, camera);
            } else {
                this.particles.remove(i);
            }
        }
        if(PLAYER){
            player.draw(g, camera);
        }
    }
}