import java.awt.*;

public class Saab95 extends Cars {
    private boolean turboOn;
    public Saab95(){
        super(2,125, Color.red,"Saab95", 2, 1900);
        // super(nrDoors,enginePower,color,modelName);
        this.setTurboOff();
        super.stopEngine();
    }
    public void setTurboOn(){
        turboOn = true;
    }
    public void setTurboOff(){
        turboOn = false;
    }
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}

