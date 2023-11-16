import java.util.ArrayList;

import java.util.List;

public class Workshop extends Object{
    private Storage<Cars> StoredCars;
    private ArrayList<Cars> acceptedCars;

    public Workshop(){
        super();
        int maxLoadAmount = 5;
        StoredCars = new Storage<Cars>(maxLoadAmount);

    }
    public Workshop(ArrayList<Cars> carNames){
        super();
        this.acceptedCars = carNames;
        int maxLoadAmount = 5;
        StoredCars = new Storage<Cars>(maxLoadAmount);

    }

    public void unloadCar(){

    }
    public void loadCar(){
        // Check if dimentions are compatable
    }
}
