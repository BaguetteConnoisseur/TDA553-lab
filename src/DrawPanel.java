import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    // To keep track of a single cars position
    //HashMap<Cars, Point> carPoints = new HashMap<Cars, Point>();
    ArrayList<Cars> cars;


    // Initializes the panel and reads the images
    public DrawPanel(int x, int y, ArrayList<Cars> cars) {

        this.cars = cars;
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        for (Cars car: cars) {
            addCarImage(car);
        }
    }

    private void addCarImage(Cars car){
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            car.vehicleImage = ImageIO.read(DrawPanel.class.getResourceAsStream(car.getModelName() + ".jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cars car: cars){
            if (car.vehicleImage == null){
                addCarImage(car);
            }
            System.out.println(car.vehicleImage);
            g.drawImage(car.vehicleImage, (int)car.getPositionX(), (int)car.getPositionY(), null);

        }
    }
}
