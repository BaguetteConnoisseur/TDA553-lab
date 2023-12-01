import java.util.ArrayList;

import java.util.List;

public class Workshop<T extends Cars>{
    private Storage<T> StoredCars;
    Position position;


    public Workshop(int maxLoadAmount){
        super();
        position = new Position();
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
