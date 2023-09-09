package spacegame;

import java.awt.*;

import spacegame.*;
public class Camera extends WorldObject {
    
    public double left() {
        return this.x();
    }

    public double right() {
        return this.x() + this.width();
    }

    public double top() {
        return this.y();
    }

    public double bottom() {
        return this.y() + this.height();
    }

    public void setSize(Dimension size) {
        double centerX = this.centerX();
        double centerY = this.centerY();
        this.setDimensions((int) Math.round(size.getWidth()), (int) Math.round(size.getHeight()));
        this.setCenter(centerX, centerY);
    }
}
