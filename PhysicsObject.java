package spacegame;

import spacegame.*;

public abstract class PhysicsObject extends VisibleObject {
    public double xVel = 0;
    public double yVel = 0;
    public double maxVel = 1;
    public boolean hasFriction = true;

    public void setMaxVel(double max) {
        this.maxVel = max;
    }

    public void push(double xForce, double yForce) {
        if(Math.abs(this.xVel) < this.maxVel) {
            this.xVel += xForce/7;
            if(Math.abs(this.xVel) > this.maxVel) {
                this.xVel = this.maxVel * this.xVel/Math.abs(this.xVel);
            }
        } else {
            this.xVel = this.maxVel * this.xVel/Math.abs(this.xVel);
        }
        if(Math.abs(this.yVel) < this.maxVel) {
            this.yVel += yForce/7;
            if(Math.abs(this.yVel) > this.maxVel) {
                this.yVel = this.maxVel * this.yVel/Math.abs(this.yVel);
            }
        } else {
            this.yVel = this.maxVel * this.yVel/Math.abs(this.yVel);
        }
    }

    //Don't bother trying to read this, it just slows down the object
    public void friction(double  xFForce, double yFForce) {
        if(this.xVel < 0 && Math.abs(this.xVel) > Math.abs(xFForce)) { xVel += xFForce/7; }
        else if(this.xVel > 0 && Math.abs(this.xVel) > Math.abs(xFForce)) { xVel -= xFForce/7; }
        else { xVel = 0; }

        if(this.yVel < 0 && Math.abs(this.yVel) > Math.abs(yFForce)) { yVel += yFForce/7; }
        else if(this.yVel > 0 && Math.abs(this.yVel) > Math.abs(yFForce)) { yVel -= yFForce/7; }
        else { yVel = 0; }
    }

    public void toggleFriction() {
        if(this.hasFriction){
            this.hasFriction = false;
        } else {
            this.hasFriction = true;
        }
    }

    public boolean checkForCollision(WorldObject object) {
        double myTop = this.y();
        double myBottom = this.y() + this.height();
        double myLeft = this.x();
        double myRight = this.x() + this.width();

        double objTop = object.y();
        double objBottom = object.y() + object.height(); 
        double objLeft = object.x();
        double objRight = object.x() + object.width();

        if( myLeft < objRight && 
            myRight > objLeft &&
            myBottom > objTop && 
            myTop < objBottom)
        {
            //collision detected
            System.out.println("collision!");
            return true;
        }
        return false;
    }

    public void applyPhysics(int deltaT) {
        //This applies velocity/moves stuff
        this.setPos(this.x() + this.xVel * deltaT, this.y() + this.yVel * deltaT);
        if(this.hasFriction){
            //This stops things from flying off into space
            this.friction(deltaT, deltaT);
        } else {
            //This is for gravity
            //this.push(0,1);
        }
    }
}
