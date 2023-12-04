import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game{
    public ArrayList<Cars> cars;

    private static CarController cc;
    private final int delay = 8;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    public static void main(String[] args) {
        // Instance of this class
        cc = new CarController();
        //initButtons();
        cc.cars.add(CarFactory.createVolvo240());
        cc.cars.add(CarFactory.createSaab95());
        cc.cars.add(CarFactory.createScania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);
        //TODO Lite Bad bad nono
        cc.game = new Game(); //TODO Dubbel pil mellan game och carcontroller
        cc.game.initActionListener();

        for (Cars car: cc.cars) {
            cc.frame.drawPanel.createPoint(car);
        }
        cc.startTimer();
    }

    public void createCar(String name){
        switch(name) {
            case "Volvo240":
                Volvo240 volvo240 = CarFactory.createVolvo240();
            case "Saab95":
                Saab95 saab95 = CarFactory.createSaab95();
            case "Scania":
                Scania scania = CarFactory.createScania();
            case "TransportTruck":
                TransportTruck transporttruck = CarFactory.createTransportTruck();
        }
    }

    //ActionListeners for all the buttons in the view
    private void initActionListener(){
        cc.frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.brake(cc.frame.gasAmount);
            }
        });
        cc.frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.gas(cc.frame.gasAmount);
            }
        });
        cc.frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.start_all_cars();
            }
        });
        cc.frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.stop_all_cars();
            }
        });
        cc.frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.saab_turbo_off();
            }
        });
        cc.frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.saab_turbo_on();
            }
        });
        cc.frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.lowerBed();
            }
        });
        cc.frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cc.raiseBed();
            }
        });
    }
}
