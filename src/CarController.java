import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/*
* This class represents the Controller part in the MVC pattern.
* It is responsibilities is to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 8;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();

    }


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getPositionX());
                int y = (int) Math.round(car.getPositionY());
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

                if (car.getPositionX() + 100 > frame.drawPanel.getWidth()){
                    car.stopEngine();
                    car.setPosition(frame.drawPanel.getWidth() -100, car.getPositionY());
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }
                else if (car.getPositionY() + 60 > frame.drawPanel.getHeight()) {
                    car.stopEngine();
                    car.setPosition(car.getPositionX(), (frame.drawPanel.getHeight()- 60));
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }
                else if (car.getPositionY() < 0) {
                    car.stopEngine();
                    car.setPosition(car.getPositionX(), 0);
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }
                else if (car.getPositionX() < 0) {
                    car.stopEngine();
                    car.setPosition(0, car.getPositionY());
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();

                }
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Cars car : cars) {
            car.gas(gas);
        }
    }

    // Calls the brake for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Cars car : cars) {
            car.brake(brake);
        }
    }
    void stop_all_cars(){
        for (Cars car : cars){
            car.stopEngine();
        }
    }
    void start_all_cars(){
        for (Cars car : cars){
            car.startEngine();
        }
    }
    void saab_turbo_on(){
        for (Cars car : cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOn();
        }
    }
    void saab_turbo_off(){
        for (Cars car : cars){
            if (car instanceof Saab95)
                ((Saab95) car).setTurboOff();
        }
    }
    /*
    void lowerBed(){ //TODO fix a check that checks if car contains a ramp variable
        for (Cars car: cars);
            if (cars.);
    }
    void raiseBed(){

    }

     */
}
