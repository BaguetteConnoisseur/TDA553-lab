import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRamp {
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
    public void test_if_bedAngle_can_extend_limit_of_vehicle(){
        myScania.raiseRampAngle(10);
        assertEquals(0,myScania.getRampAngle(),0.0);
        myScania.lowerRampAngle(90);
        assertEquals(70, myScania.getRampAngle(), 0.0);
    }
    @Test
    public void test_if_you_can_drive_truck_when_bed_is_raised(){
        myScania.lowerRampAngle(10);
        myScania.startEngine();
        assertEquals(0, myScania.getCurrentSpeed(), 0.0);

        myScania.raiseRampAngle(10);
        myScania.startEngine();
        myScania.lowerRampAngle(10);
        assertEquals(0, myScania.getRampAngle(), 0.0);

        myScania.stopEngine();
        myScania.lowerRampAngle(10);
        myScania.gas(1);
        assertEquals(0, myScania.getCurrentSpeed(), 0.0);
    }
    @Test
    public void test_if_you_can_lower_transportTruck_ramp(){
        myTransportTruck.lowerRamp();
    }
}
