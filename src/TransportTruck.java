import java.awt.*;

public class TransportTruck extends Cars{
    private final Ramp ramp;
    private final Storage<Cars> transportedCars;
    private final int maxRampAngle = 1;
    private final int maxLoadAmount = 2;
    private final int maxLoadWeight;
    public TransportTruck(){
        super(2,70, Color.GREEN,"Transport Truck", 2.5, 18000);
        // super(nrDoors,enginePower,color,modelName);
        super.stopEngine();
        this.maxLoadWeight = 8000;
        this.ramp = new Ramp(maxRampAngle);
        this.transportedCars = new Storage<>(maxLoadAmount);
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
        if (this.ramp.getRampAngle() == maxRampAngle && !(car instanceof TransportTruck) && (car.getWeight() <= this.maxLoadWeight) && !(car.isLoaded)){
            car.isLoaded = transportedCars.loadCar(car);
        }
        else {
            System.out.println("Can't load Car");
        }
    }
    public void unloadCar(){
        if (ramp.getRampAngle()  == maxRampAngle && transportedCars.getStorageSize() > 0) {
            Cars lastCar = transportedCars.getLastStorageCar();
            lastCar.isLoaded = transportedCars.unloadCar(lastCar);
        }
        else {
            System.out.println("Can't unload");
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
}
