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

    // Just a single image,
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    private int starting_y = 0;
    // To keep track of a single cars position
    HashMap<Cars, Point> carPoints = new HashMap<Cars, Point>();
    public void createPoint(Cars car){
        int x = 0;
        carPoints.put(car, new Point(x,starting_y));
        car.position.setPosition(x,starting_y);
        starting_y += 100;
    }

    /*
    void moveit( Cars car, int x, int y){
        carPoints.put(car, new Point(x,y));
    }

     */

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {

        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));
            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("Volvo240.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("Scania.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Map.Entry<Cars, Point> carPoint : carPoints.entrySet()){
            g.drawImage(getImage(carPoint.getKey().getModelName()), carPoint.getValue().x, carPoint.getValue().y, null);

        }
    }

    private BufferedImage getImage(String modelName) {
        return switch (modelName) {
            case ("Volvo240") -> volvoImage;
            case ("Saab95") -> saabImage;
            case ("Scania") -> scaniaImage;
            default -> volvoImage;
        };
    }
}
