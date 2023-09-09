package spacegame;

import spacegame.*;
//abstract world object class, mostly just used for camera
public abstract class WorldObject {
    private double x;
    private double y;
    private int width;
    private int height;

    public double x() {
        return this.x;
    }
    
    public double y() {
        return this.y;
    }

    public void moveX(double delX) {
        this.x = this.x + delX;
    }

    public void moveY(double delY) {
        this.y = this.y + delY;
    }

    public int width() {
        return this.width;
    }
    
    public int height() {
        return this.height;
    }

    public double centerX() {
        return this.x+this.width/2;
    }
    
    public double centerY() {
        return this.y+this.height/2;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setCenter(double centerX, double centerY) {
        this.x = centerX - (double) this.width()/2;
        this.y = centerY - (double) this.height()/2;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isVisible(Camera camera){
        if(this.x <= camera.right() && this.x+this.width >= camera.left() && this.y >= camera.top() && this.y+this.height <= camera.bottom()) {
            return true;
        } else {
            return false;
        }
    }
}
