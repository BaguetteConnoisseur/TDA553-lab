import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game{
    //public static ArrayList<Cars> cars;

    private static CarController cc;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    public static void main(String[] args) {
        // Instance of this class
        cc = new CarController();
        //initButtons();
        cc.cars.add(CarFactory.createVolvo240());
        cc.cars.add(CarFactory.createSaab95());
        cc.cars.add(CarFactory.createScania());

        // Start a new view
        cc.frame = new CarView("CarSim 1.0", cc.cars);
        cc.initlisteners();

        for (Cars car: cc.cars) {
            cc.setStartingPosition(car);
        }
        new Game().begin();
    }
    private void begin(){
        timer.start();
    }
    private final int delay = 8;
    private final Timer timer = new Timer(delay, new TimerListener());
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cc.cars) {
                car.move();
                int x = (int) Math.round(car.position.getPositionX());
                int y = (int) Math.round(car.position.getPositionY());
                //moveit(car, x, y);
                cc.changeDirectionIfCarIsOutOfBounds(car);
                // repaint() calls the paintComponent method of the panel
                cc.frame.drawPanel.repaint();
            }
        }
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
}
