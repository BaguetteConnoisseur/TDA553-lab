import java.util.ArrayList;

import static java.lang.Math.abs;

public class Storage<T extends Cars> {
    private final ArrayList<T> storageContents;
    private final int maxLoadAmount;
    Position position;

    public Storage(int maxLoadAmount){
        storageContents = new ArrayList<>();
        position = new Position();
        this.maxLoadAmount = maxLoadAmount;

    }

    private boolean overlaps(T car){
        return (abs(this.position.getPositionX() - car.position.getPositionX()) < 1) && (abs(this.position.getPositionY() - car.position.getPositionY()) < 1);
    }
    public boolean loadCar(T car){
        if(storageContents.size() < maxLoadAmount && this.overlaps(car)){
            storageContents.add(car);
            System.out.println("Car loaded");
            return true;
        }
        else {
            System.out.println("Can't load car");
            return false;
        }
    }
    public boolean unloadCar(T car){
        if (!storageContents.isEmpty()) {
            car.position.setPosition(car.position.getPositionX(), car.position.getPositionY()-50);
            storageContents.remove(car);
            return false;
        }
        return true;
    }
    public void getStorage(){
        if (storageContents.isEmpty()){
            System.out.println("There is no Vehicles on the truck");
        }
        for (T car : storageContents){
            System.out.println("This Vehicle is on the truck: " + car);
        }
    }
    public T getLastStorageCar(){
        return storageContents.getLast();
    }
    public int getStorageSize(){
        return storageContents.size();
    }

    public void moveStoredCars(Cars movingObject) {
        for (T car : storageContents) {
            car.position.setPosition(movingObject.position.getPositionX(), movingObject.position.getPositionY());
        }
    }
}
