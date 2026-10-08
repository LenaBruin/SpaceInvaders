package utils.math;

public class Meth {
    public static double normalize(double radians){
        while(radians<= -Math.PI)
            radians+= 2*Math.PI;
        while (radians>= Math.PI)
            radians-= 2*Math.PI;
        return radians;
    }
}
