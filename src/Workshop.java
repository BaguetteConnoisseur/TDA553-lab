import java.util.ArrayList;

import java.util.List;

public class Workshop<T extends Cars> extends Object{
    private Storage<T> StoredCars;

    public Workshop(int maxLoadAmount){
        super();
        StoredCars = new Storage<T>(maxLoadAmount);

    }

    public void unloadCar(T car){
        StoredCars.unloadCar(car);
    }
    public int getStorageSize(){
        return StoredCars.getStorageSize();
    }
    public void loadCar(T car){
        StoredCars.loadCar(car);
    }
}
