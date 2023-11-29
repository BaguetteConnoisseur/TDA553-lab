import java.awt.*;
import java.awt.geom.Point2D;

public class Object {
    private final Point2D position;

    public Object() {
        this.position = new Point2D.Double(0, 0);
    }
    public void setPosition(double positionX, double positionY) {
        this.position.setLocation(positionX, positionY);
    }
    public double getPositionX() {return this.position.getX();
    }


    public double getPositionY() {return this.position.getY();
    }
}