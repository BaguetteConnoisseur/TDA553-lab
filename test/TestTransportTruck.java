import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestTransportTruck {
    Saab95 mySaab95;
    Volvo240 myVolvo240;
    Scania myScania;
    TransportTruck myTransportTruck;

    @Before
    public void setup() {
        mySaab95 = new Saab95();
        myVolvo240 = new Volvo240();
        myScania = new Scania();
        myTransportTruck = new TransportTruck();
    }

    @Test
    public void test_if_car_can_not_be_loaded_from_far_away(){
        mySaab95.gas(1);
        mySaab95.gas(1);
        mySaab95.move();
        myTransportTruck.loadCar(mySaab95);
        assertEquals(0,myTransportTruck.getStorageSize());
    }

    @Test
    public void test_if_ramp_can_be_adjusted_while_moving(){
        myTransportTruck.gas(1);
        System.out.println(myTransportTruck.getCurrentSpeed());
        myTransportTruck.lowerRamp();
        myTransportTruck.loadCar(myScania);
        assertEquals(0,myTransportTruck.getStorageSize());
        myTransportTruck.stopEngine();
        myTransportTruck.raiseRamp();
        myTransportTruck.startEngine();

    }
    @Test
    public void test_if_car_moves_with_transportTruck(){
        myTransportTruck.lowerRamp();
        myTransportTruck.loadCar(myVolvo240);
        myTransportTruck.loadCar(mySaab95);
        myTransportTruck.raiseRamp();
        myTransportTruck.startEngine();
        myTransportTruck.gas(1);
        myTransportTruck.move();
        assertEquals(myTransportTruck.getPositionY(),myVolvo240.getPositionY(),0.0D);
        myTransportTruck.stopEngine();
        myTransportTruck.lowerRamp();
        myTransportTruck.unloadCar();
        myTransportTruck.raiseRamp();
        myTransportTruck.startEngine();
        myTransportTruck.gas(1);
        myTransportTruck.move();
        assertNotEquals(myVolvo240.getPositionY(), mySaab95.getPositionY());
    }

    @Test
    public void test_if_TransportTruck_can_not_load_TransportTruck(){
        TransportTruck myOtherTransportTruck = new TransportTruck();
        myTransportTruck.loadCar(myOtherTransportTruck);
        assertEquals(0, myTransportTruck.getStorageSize());
    }

    @Test
    public void test_if_you_can_not_load_a_dummy_thick_car_on_TransportTruck(){
        myTransportTruck.loadCar(myScania);
        assertEquals(0,myTransportTruck.getStorageSize(),0.0);
    }
}
