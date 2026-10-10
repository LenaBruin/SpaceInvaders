package utils.math;

public class Pose {
    double x, y, angle;
    public Pose(){
        this.x= 0;
        this.y= 0;
        this.angle= 0;
    }
    public Pose(double x, double y){
        this.x= x;
        this.y= y;
        this.angle= 0;
    }
    public Pose(double x, double y, double angle){
        this(x, y);
        this.angle= angle;
    }
    public double getX(){
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public double getAngle(){
        return this.angle;
    }
    public double getNormalizedAngle(){
        return Meth.normalize(this.angle);
    }
    public void setX(double x){
        this.x= x;
    }
    public void setY(double y){
        this.y= y;
    }
    public void setAngle(double radians){
        this.angle= radians;
    }
}
