import java.awt.*;

public class Object {
    private final Point position;

    public Object() {
        this.position = new Point(0, 0);
    }
    public void setPosition(double positionX, double positionY) {
        this.position.setLocation(positionX, positionY);
    }
    public double getPositionX() {return this.position.getX();
    }


    public double getPositionY() {return this.position.getY();
    }
}