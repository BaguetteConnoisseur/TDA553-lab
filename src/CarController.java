import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/*
* This class represents the Controller part in the MVC pattern.
* It is responsibilities is to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.

    //Cars starting Y-pos
    private int starting_y = 0;

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    protected void changeDirectionIfCarIsOutOfBounds(Cars car) {
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

    public void setStartingPosition(Cars car){
        car.position.setPosition(0,starting_y);
        starting_y += 100;
    }


    public void initlisteners(){
        initActionListener();
    }
    //ActionListeners for all the buttons in the view
    private void initActionListener(){
        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brake(frame.gasAmount);
            }
        });
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(frame.gasAmount);
            }
        });
        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start_all_cars();
            }
        });
        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop_all_cars();
            }
        });
        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saab_turbo_off();
            }
        });
        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saab_turbo_on();
            }
        });
        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBed();
            }
        });
        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                raiseBed();
            }
        });
        frame.addVolvo240Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cars.add(CarFactory.createVolvo240());
            }
        });
        frame.addSaab95Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cars.add(CarFactory.createSaab95());
            }
        });
        frame.addScaniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cars.add(CarFactory.createScania());
            }
        });
        frame.removeVolvo240.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Cars car : cars){
                    if (car instanceof Volvo240){
                        cars.remove(car);
                        break;
                    }
                }
            }
        });
        frame.removeSaab95.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Cars car : cars){
                    if (car instanceof Saab95){
                        cars.remove(car);
                        break;
                    }
                }
            }
        });
        frame.removeScania.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Cars car : cars){
                    if (car instanceof Scania){
                        cars.remove(car);
                        break;
                    }
                }
            }
        });

    }
    /*
    void moveit( Cars car, int x, int y){
        frame.drawPanel.carPoints.put(car, new Point(x,y));
    }
    */
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
