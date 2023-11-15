import java.awt.*;

public class Scania extends Cars {
    public double bedAngle;
    public Scania(){
        super(2,70, Color.GREEN,"Scania");
        // super(nrDoors,enginePower,color,modelName);
        super.stopEngine();
    }
    @Override
    public void startEngine() {
        if (getBedAngle() == 0){
            super.startEngine();
        }
    }

    public double speedFactor() {
        if (getBedAngle() == 0) {
            return getEnginePower() * 0.01;
        }
        else

            return 0;
    }
    public double getBedAngle() {
        return bedAngle;
    }
    private void setBedAngle(double angle){ this.bedAngle = angle;}

    public void raiseBedAngle(double amount) {
        if (getCurrentSpeed() == 0) {
            setBedAngle(Math.min(bedAngle + amount, 70));
        }
    }
    public void lowerBedAngle(double amount) {
        if (getCurrentSpeed() == 0) {
            setBedAngle(Math.max(bedAngle - amount, 0));
        }
    }
}
