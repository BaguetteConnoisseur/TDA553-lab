import java.awt.*;

public class TransportTruck extends Cars{
    private final Ramp ramp;
    private Storage<Cars> transportedCars;
    private final int maxRampAngle = 1;
    private final int maxLoadAmount = 2;
    public TransportTruck(){
        super(2,70, Color.GREEN,"Transport Truck");
        // super(nrDoors,enginePower,color,modelName);
        super.stopEngine();
        this.ramp = new Ramp(maxRampAngle);
        transportedCars = new Storage<Cars>(maxLoadAmount);
    }

    public double speedFactor() {
        if (ramp.getRampAngle() == 0) {
            return getEnginePower() * 0.01;
        }
        else
            return 0;
    }

    public void raiseRamp(){
        if (this.getCurrentSpeed() == 0.0D){
            ramp.raiseRamp();
        }
    }
    public void lowerRamp(){
        if (this.getCurrentSpeed() == 0.0D) {
            ramp.lowerRamp();
        }
    }
    public void loadCar(Cars car){
        if (this.ramp.getRampAngle() == maxRampAngle && !(car instanceof TransportTruck)){
            transportedCars.loadCar(car);
        }
    }
    public void unloadCar(){
        if (ramp.getRampAngle()  == maxRampAngle) {
            transportedCars.unloadCar();
        }
    }
    public void getStorage(){
        transportedCars.getStorage();
    }
    public int getStorageSize(){
        return transportedCars.getStorageSize();
    }
    @Override
    public void move(){
        super.move();
        transportedCars.moveStoredCars(this);
    }
    /*


    public void loadCar(Cars car){
        if(storage.size() < maxLoadAmount && ramp.getRampAngle() == 1 && overlaps(car)) {
            storage.add(car);
            System.out.println(car.getModelName() + " is on the transport");
        }
        else {
            System.out.println("The truck is full");
        }
    }

    public void unloadCar(){
        if(storage.size() > 1 && ramp.getRampAngle() == 1){
            System.out.println("This car was unloaded: "+storage.getLast());
            storage.removeLast();
        }
        else {
            System.out.println("There is no Vehicles on the truck");
        }

    }

    private boolean overlaps(Cars car){
        return (abs(this.getPositionX() - car.getPositionX()) < 1) && (abs(this.getPositionY() - car.getPositionY()) < 1);
    }

    public void getStorage(){
        if (storage.size() == 1){
            System.out.println("There is no Vehicles on the truck");
        }
        for (int i = 1; i < storage.size(); i++){
            System.out.println("This Vehicle is on the truck: " + storage.get(i));
        }
        */
}
