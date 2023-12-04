import java.awt.*;
import java.awt.image.BufferedImage;


public class CarFactory {
    public static Volvo240 createVolvo240(){
        return new Volvo240();
    }

    public static Saab95 createSaab95(){
        return new Saab95();
    }

    public static Scania createScania(){
        return new Scania();
    }

    public static TransportTruck createTransportTruck(){
        return new TransportTruck();
    }

}
