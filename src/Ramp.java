public class Ramp {
    private int rampAngle;
    private int maxRampAngle;
    private boolean isRampUp;
    public Ramp(int maxRampAngle){
        this.maxRampAngle = maxRampAngle;
        this.rampAngle = 0;
    }
    public Ramp(boolean isRampUp){
        this.isRampUp = isRampUp;
    }
    public void raiseRamp(int angle){
        this.rampAngle = Math.max(this.rampAngle - angle, 0);
    }
    public void lowerRamp(int angle){
        this.rampAngle = Math.min(this.rampAngle + angle, this.maxRampAngle);
    }
    public void raiseRamp(){
        this.isRampUp = true;
    }
    public void lowerRamp(){
        this.isRampUp = false;
    }
    public int getRampPos() {
        return this.rampAngle;
    }
    public boolean getIsRampUp() {
        return isRampUp;
    }
}
