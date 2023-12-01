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
        for (Cars car: cc.cars) {
            cc.frame.drawPanel.createPoint(car);
        }

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
                int x = (int) Math.round(car.position.getPositionX());
                int y = (int) Math.round(car.position.getPositionY());
                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                changeDirectionIfCarIsOutOfBounds(car);
            }
        }
    }

    private void changeDirectionIfCarIsOutOfBounds(Cars car) {
        if (car.position.getPositionX() + 100 > frame.drawPanel.getWidth()){
            car.position.setPosition(frame.drawPanel.getWidth() -100, car.position.getPositionY());
            turnCarAroundAndChangeSpeedToLowest(car);
        }
        else if (car.position.getPositionY() + 60 > frame.drawPanel.getHeight()) {
            car.position.setPosition(car.position.getPositionX(), (frame.drawPanel.getHeight()- 60));
            turnCarAroundAndChangeSpeedToLowest(car);
        }
        else if (car.position.getPositionY() < 0) {
            car.position.setPosition(car.position.getPositionX(), 0);
            turnCarAroundAndChangeSpeedToLowest(car);
        }
        else if (car.position.getPositionX() < 0) {
            car.position.setPosition(0, car.position.getPositionY());
            turnCarAroundAndChangeSpeedToLowest(car);
        }
    }
    private void turnCarAroundAndChangeSpeedToLowest(Cars car){
        car.stopEngine();
        car.turnRight();
        car.turnRight();
        car.startEngine();
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

    void lowerBed() {
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).lowerRampAngle(10);
                System.out.println("RampAngle: "+((Scania) car).getRampAngle());
            }
        }
    }
    void raiseBed(){
        for (Cars car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).raiseRampAngle(10);
                System.out.println("RampAngle: "+((Scania) car).getRampAngle());
            }
        }
    }


}
