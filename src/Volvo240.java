import java.awt.*;

public class Volvo240 extends Cars {
        private final double trimfactor;
        public Volvo240(){
            super(4,100, Color.black,"Volvo240", 1.7, 1300);
            // super(nrDoors,enginePower,color,modelName);
            this.trimfactor = 1.25;
            super.stopEngine();
        }

        public double speedFactor(){
            return getEnginePower() * 0.01 * trimfactor;
        }


    }

