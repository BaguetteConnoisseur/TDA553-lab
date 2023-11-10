import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;


public class TestCars {

    Saab95 mySaab95;
    Volvo240 myVolvo240;

    @Before
    public void setup() {
        mySaab95 = new Saab95();
        myVolvo240 = new Volvo240();
    }



    @Test
    public void test_if_startEngine_sets_current_speed(){
        myVolvo240.startEngine();
        assertEquals(0.1, myVolvo240.getCurrentSpeed(), 0.0);
        mySaab95.startEngine();
        assertEquals(0.1, mySaab95.getCurrentSpeed(), 0.0);
    }
    @Test
    public void test_gas() {
        mySaab95.startEngine();
        mySaab95.gas(1);
        mySaab95.gas(1);
        assertEquals(2.6D, mySaab95.getCurrentSpeed(), 0.0D);

        myVolvo240.startEngine();
        myVolvo240.gas(1);
        myVolvo240.gas(1);
        myVolvo240.gas(1);
        assertEquals(3.85D, myVolvo240.getCurrentSpeed(), 0.0D);
    }
    @Test
    public void test_Break(){
        mySaab95.setCurrentSpeed(2.5);
        mySaab95.Break(1);
        mySaab95.Break(1);
        assertEquals(0.0D,mySaab95.getCurrentSpeed(),0.0D);

        myVolvo240.setCurrentSpeed(3.75);
        myVolvo240.Break(1);
        myVolvo240.Break(1);
        myVolvo240.Break(1);
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
    public void test_if_setCurrentSpeed(){
        mySaab95.setCurrentSpeed(40);
        assertEquals(40, mySaab95.getCurrentSpeed(), 0.0F);
        myVolvo240.setCurrentSpeed(40);
        assertEquals(40,myVolvo240.getCurrentSpeed(),0.0F);
    }

    @Test
    public void test_if_incrementSpeed_changes_currentSpeed(){
        mySaab95.incrementSpeed(40);
        assertEquals(50.0F,(mySaab95.getCurrentSpeed()), 0.0F);

        myVolvo240.incrementSpeed(40);
        assertEquals(50.0F,(myVolvo240.getCurrentSpeed()), 0.0F);

        myVolvo240.incrementSpeed(356);
        assertEquals(100.0F,(myVolvo240.getCurrentSpeed()), 0.0F);
    }

    @Test
    public void test_if_decrementSpeed_changes_currentSpeed(){
        mySaab95.setCurrentSpeed(50);
        mySaab95.decrementSpeed(40);
        assertEquals(0.0F,(mySaab95.getCurrentSpeed()), 0.0F);

        myVolvo240.setCurrentSpeed(50);
        myVolvo240.decrementSpeed(40);
        assertEquals(0.0F,(mySaab95.getCurrentSpeed()), 0.0F);

        myVolvo240.setCurrentSpeed(100);
        myVolvo240.decrementSpeed(7642);
        assertEquals(0.0D,(mySaab95.getCurrentSpeed()), 0.0D);
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


    }
    @Test
    public void test_if_car_has_color_doors_and_modelName(){
        myVolvo240.setColor(Color.BLACK);
        mySaab95.setColor(Color.RED);
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
}
