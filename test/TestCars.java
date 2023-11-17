import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class TestCars {

    Saab95 mySaab95;
    Volvo240 myVolvo240;
    Scania myScania;
    TransportTruck myTransportTruck;
    Workshop<Cars> myWorkshop;
    Workshop<Volvo240> volvoWorkshop;

    @Before
    public void setup() {
        mySaab95 = new Saab95();
        myVolvo240 = new Volvo240();
        myScania = new Scania();
        myTransportTruck = new TransportTruck();
        volvoWorkshop = new Workshop<>();
        myWorkshop = new Workshop<>();
    }

    @Test
    public void test_if_startEngine_sets_current_speed(){
        myVolvo240.startEngine();
        assertEquals(0.1, myVolvo240.getCurrentSpeed(), 0.0);
        mySaab95.startEngine();
        assertEquals(0.1, mySaab95.getCurrentSpeed(), 0.0);
    }
    @Test
    public void test_gas_and_break() {

        mySaab95.gas(1);
        mySaab95.gas(1);
        assertEquals(2.5D, mySaab95.getCurrentSpeed(), 0.0D);

        myVolvo240.gas(1);
        myVolvo240.gas(1);
        myVolvo240.gas(1);
        assertEquals(3.75D, myVolvo240.getCurrentSpeed(), 0.0D);

        mySaab95.brake(1);
        mySaab95.brake(1);
        assertEquals(0.0D,mySaab95.getCurrentSpeed(),0.0D);

        myVolvo240.brake(1);
        myVolvo240.brake(1);
        myVolvo240.brake(1);
        assertEquals(0.0D,mySaab95.getCurrentSpeed(),0.0D);
    }

    @Test
    public void test_if_direction_is_changed_myCar_changes_direction(){
        mySaab95.turnLeft();
        assertEquals("WEST", mySaab95.getDirection());
        mySaab95.turnLeft();
        assertEquals("SOUTH", mySaab95.getDirection());
        mySaab95.turnLeft();
        assertEquals("EAST", mySaab95.getDirection());
        mySaab95.turnLeft();
        assertEquals("NORTH", mySaab95.getDirection());
        mySaab95.turnRight();
        assertEquals("EAST", mySaab95.getDirection());
    }

    @Test
    public void test_if_move_Cars_changes_location(){
        mySaab95.startEngine();
        mySaab95.gas(1);

        mySaab95.move();
        assertEquals(0.0D, (mySaab95.getPositionX()), 0.0D);
        assertEquals(1.35D,(mySaab95.getPositionY()), 0.0D);

        mySaab95.turnRight();
        mySaab95.move();
        assertEquals(1.35D, (mySaab95.getPositionX()), 0.0D);
        assertEquals(1.35D,(mySaab95.getPositionY()), 0.0D);

        mySaab95.turnRight();
        mySaab95.move();
        assertEquals(1.35D, (mySaab95.getPositionX()), 0.0D);
        assertEquals(0.0D,(mySaab95.getPositionY()), 0.0D);

        mySaab95.turnRight();
        mySaab95.move();
        assertEquals(0.0D, (mySaab95.getPositionX()), 0.0D);
        assertEquals(0.0D,(mySaab95.getPositionY()), 0.0D);
        mySaab95.turnRight();
    }
    @Test
    public void test_if_car_has_color_doors_and_modelName(){
        assertEquals("Volvo240", myVolvo240.getModelName());
        assertEquals("Saab95", mySaab95.getModelName());
        assertEquals(Color.BLACK, myVolvo240.getColor());
        assertEquals(Color.RED, mySaab95.getColor());
        assertEquals(4, myVolvo240.getNrDoors());
        assertEquals(2, mySaab95.getNrDoors());
    }

    @Test
    public void test_if_saab95_has_turbo(){
        mySaab95.startEngine();
        mySaab95.gas(1);
        assertEquals(1.35, mySaab95.getCurrentSpeed(), 0.0);

        mySaab95.stopEngine();
        mySaab95.startEngine();
        mySaab95.setTurboOn();
        mySaab95.gas(1);
        assertEquals(1.725, mySaab95.getCurrentSpeed(), 0.0);
    }

    @Test
    public void test_if_volvo240_has_trimFactor(){
        myVolvo240.startEngine();
        myVolvo240.gas(1);
        assertEquals(1.35D, myVolvo240.getCurrentSpeed(), 0.0);
        myVolvo240.stopEngine();
    }

    @Test
    public void test_if_bedAngle_can_extend_limit_of_vehicle(){
        myScania.raiseBedAngle(10);
        assertEquals(0,myScania.getRampAngle(),0.0);
        myScania.lowerBedAngle(90);
        assertEquals(70, myScania.getRampAngle(), 0.0);
    }
    @Test
    public void test_if_you_can_drive_truck_when_bed_is_raised(){
        myScania.lowerBedAngle(10);
        myScania.startEngine();
        assertEquals(0, myScania.getCurrentSpeed(), 0.0);

        myScania.raiseBedAngle(10);
        myScania.startEngine();
        myScania.lowerBedAngle(10);
        assertEquals(0, myScania.getRampAngle(), 0.0);

        myScania.stopEngine();
        myScania.lowerBedAngle(10);
        myScania.gas(1);
        assertEquals(0, myScania.getCurrentSpeed(), 0.0);
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
    public void test_if_transportTruck_can_contain_more_cars_than_max_and_not_get_error_when_empty() {
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
    @Test
    public void test_if_you_can_not_load_a_dummy_thick_car_on_TransportTruck(){
        myTransportTruck.loadCar(myScania);
        assertEquals(0,myTransportTruck.getStorageSize(),0.0);
    }
    //@Test
    //public void test_if_
}
