package utils.math;

public class Vector {

    public double magnitude, theta;
    public double xComponent, yComponent;

    public enum Type{
        CARTESIAN, POLAR
    }
    Type type;
    public Vector(double a, double b, Type type){
        if(type== Type.CARTESIAN)
        {
            xComponent= a;
            yComponent= b;
            magnitude= Math.hypot(a,b);
            theta= Math.atan2(b,a);
        }
        else{
            if(a< 0)
                theta= Meth.normalize(b+ Math.PI);
            else
                theta= Meth.normalize(b);
            magnitude= Math.abs(a);
            xComponent= magnitude * Math.cos(theta);
            yComponent= magnitude * Math.sin(theta);
        }
        this.type= type;
    }
    public Vector(double a, double b){
        this(a, b, Type.CARTESIAN);
    }


    public double getXComponent(){
        return this.xComponent;
    }
    public double getYComponent(){
        return this.yComponent;
    }

    public double getMagnitude(){
        return this.magnitude;
    }
    public double getTheta(){
        return this.theta;
    }
    public void rotateBy(double rad){

        double x = xComponent, y = yComponent;
        xComponent = Math.cos(rad) * x - Math.sin(rad) * y;
        yComponent = Math.sin(rad) * x + Math.cos(rad) * y;
        theta = Math.atan2(yComponent, xComponent);
        magnitude = Math.hypot(xComponent, yComponent);
    }
    public double dot(Vector other){
        return xComponent * other.xComponent + yComponent * other.yComponent;
    }

}
