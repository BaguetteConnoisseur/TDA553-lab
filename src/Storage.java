import java.util.ArrayList;

import static java.lang.Math.abs;

public class Storage<T extends Object> extends Object {
    private final ArrayList<T> storageContents;
    private final int maxLoadAmount;

    public Storage(int maxLoadAmount){
        storageContents = new ArrayList<>();
        this.maxLoadAmount = maxLoadAmount;
    }

    private boolean overlaps(T car){
        return (abs(this.getPositionX() - car.getPositionX()) < 1) && (abs(this.getPositionY() - car.getPositionY()) < 1);
    }
    public boolean loadCar(T car){
        if(storageContents.size() < maxLoadAmount && this.overlaps(car)){
            storageContents.add(car);
            return true;
        }
        else {
            System.out.println("Can't load car");
            return false;
        }
    }
    public boolean unloadCar(T car){
        if (!storageContents.isEmpty()) {
            car.setPosition(car.getPositionX(), car.getPositionY()-0.5);
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
            car.setPosition(movingObject.getPositionX(), movingObject.getPositionY());
        }
    }
}
