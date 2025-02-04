import java.awt.*;

public class Scania extends Cars {
    private final Ramp ramp;
    public Scania(){
        super(2,70, Color.GREEN,"Scania", 2.5, 10000);
        // super(nrDoors,enginePower,color,modelName);
        this.ramp = new Ramp(70);
        super.stopEngine();
    }
    @Override
    public void startEngine() {
        if (ramp.getRampPos() == 0){
            super.startEngine();
        }
    }

    public double speedFactor() {
        if (ramp.getRampPos() == 0) {
            return getEnginePower() * 0.01;
        }
        else
            return 0;
    }

    public void raiseRampAngle(int amount) {
        if (getCurrentSpeed() == 0) {
            ramp.raiseRamp(amount);
        }
    }
    public void lowerRampAngle(int amount) {
        if (getCurrentSpeed() == 0) {
            ramp.lowerRamp(amount);
        }
    }
     public int getRampAngle(){
        return ramp.getRampPos();
     }
}
