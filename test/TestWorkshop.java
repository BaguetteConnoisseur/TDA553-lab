import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestWorkshop {
    Saab95 mySaab95;
    Volvo240 myVolvo240;
    TransportTruck myTransportTruck;
    Workshop<Cars> myWorkshop;
    Workshop<Volvo240> volvoWorkshop;

    @Before
    public void setup() {
        mySaab95 = new Saab95();
        myVolvo240 = new Volvo240();
        myTransportTruck = new TransportTruck();
        volvoWorkshop = new Workshop<>(5);
        myWorkshop = new Workshop<>(5);
    }
    @Test
    public void test_if_volvoWorkshop_can_load_a_car(){
        volvoWorkshop.loadCar(myVolvo240);
        assertEquals(1,volvoWorkshop.getStorageSize());
        volvoWorkshop.unloadCar(myVolvo240);
        assertEquals(0,volvoWorkshop.getStorageSize());
    }
    @Test
    public void test_if_can_not_load_far_away_car(){
        mySaab95.gas(1);
        mySaab95.move();
        mySaab95.move();
        myWorkshop.loadCar(mySaab95);
        assertEquals(0,myWorkshop.getStorageSize());

    }
    @Test
    public void test_if_you_can_unload_car_from_workshop_that_is_loaded_not_last(){
        myWorkshop.loadCar(mySaab95);
        myWorkshop.loadCar(myVolvo240);
        myWorkshop.loadCar(myTransportTruck);
        myWorkshop.unloadCar(mySaab95);
        assertEquals(2,myWorkshop.getStorageSize(), 0.0);
    }
}
