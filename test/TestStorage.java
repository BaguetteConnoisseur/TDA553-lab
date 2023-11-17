import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestStorage {
    Saab95 mySaab95;
    Volvo240 myVolvo240;
    Scania myScania;
    TransportTruck myTransportTruck;
    Workshop<Cars> myWorkshop;
    @Before
    public void setup() {
        mySaab95 = new Saab95();
        myVolvo240 = new Volvo240();
        myScania = new Scania();
        myTransportTruck = new TransportTruck();
        myWorkshop = new Workshop<>(5);
    }
    @Test
    public void test_if_you_can_store_cars(){
        myWorkshop.loadCar(mySaab95);
        assertEquals(1,myWorkshop.getStorageSize());
    }
    @Test
    public void test_if_Storage_can_contain_more_cars_than_max_and_not_get_error_when_empty() {
        myTransportTruck.lowerRamp();
        myTransportTruck.getStorage();
        myTransportTruck.loadCar(mySaab95);
        myTransportTruck.loadCar(myVolvo240);
        myTransportTruck.loadCar(myScania);
        assertEquals(2, myTransportTruck.getStorageSize(),0.0);
        //assertEquals(3, myTransportTruck.);

        myTransportTruck.raiseRamp();
        myTransportTruck.unloadCar();
        assertEquals(2, myTransportTruck.getStorageSize(),0.0);

        myTransportTruck.lowerRamp();
        myTransportTruck.unloadCar();
        myTransportTruck.unloadCar();
        myTransportTruck.unloadCar();
        assertEquals(0, myTransportTruck.getStorageSize(),0.0);
    }
}
