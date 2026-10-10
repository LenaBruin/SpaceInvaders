package game;

import utils.math.Meth;
import utils.math.Pose;
import utils.math.Vector;

import java.util.LinkedList;
import java.util.List;

public class Hitbox {
    public int width, height;
    public int halfWidth, halfHeight;

    List<Vector> corners;
    Pose pose;
    public Hitbox(int width, int height){
        this.width = width;
        this.height= height;
        this.halfHeight= this.height /2;
        this.halfWidth = this.width /2;
        corners= new LinkedList<>();

        corners.add(new Vector(-halfWidth, halfHeight, Vector.Type.CARTESIAN));
        corners.add(new Vector(halfWidth, halfHeight, Vector.Type.CARTESIAN));
        corners.add(new Vector(halfWidth, -halfHeight, Vector.Type.CARTESIAN));
        corners.add(new Vector(-halfWidth, -halfHeight, Vector.Type.CARTESIAN));

        pose= new Pose();
    }
    public Hitbox(int length, int height, Pose pose){
        this(length, height);
        this.pose= pose;
        this.rotate(pose.getNormalizedAngle());

    }

    public void rotate(double radians){
        for(Vector corner: corners)
            corner.rotateBy(radians);
    }

    public void setAngle(double radians){
        double rotationAngle= Meth.normalize(radians- pose.getNormalizedAngle());
        this.rotate(rotationAngle);
        this.pose = new Pose(pose.getX(), pose.getY(), radians);
    }


    public void setPosition(Pose pose){
        double rotationAngle= Meth.normalize(pose.getAngle()- this.pose.getNormalizedAngle());
        this.rotate(rotationAngle);
        this.pose= new Pose(pose.getX(), pose.getY(), pose.getAngle());


    }

    public void setPosition(int x, int y){
        this.pose= new Pose(x, y, this.pose.getNormalizedAngle());
    }

    public boolean collidesWith(Hitbox other){
        Vector[] axis={
                edgeDirection(this.corners.get(0), this.corners.get(1)),
                edgeDirection(this.corners.get(1), this.corners.get(2)),
                edgeDirection(other.corners.get(0), other.corners.get(1)),
                edgeDirection(other.corners.get(1), other.corners.get(2))

        };
        for(Vector a: axis){
            double[] ax= this.shadow(a);
            double[] b= other.shadow(a);

            if(ax[1]< b[0] || b[1]< ax[0])
                return false;
        }
        return true;


    }

    public Vector edgeDirection(Vector one, Vector two){
        Vector edge= new Vector(one.getXComponent()- two.getXComponent(),
                one.getYComponent()- two.getYComponent(),
                Vector.Type.CARTESIAN);
        double lenght= Math.hypot(edge.getXComponent(), edge.getYComponent());
        return new Vector(edge.getXComponent()/lenght, edge.getYComponent()/lenght);
    }

    public double[] shadow(Vector axis){
        Vector center= new Vector(pose.getX(), pose.getY());
        double centerPosition= center.dot(axis);

        double min= Double.POSITIVE_INFINITY;
        double max= Double.NEGATIVE_INFINITY;

        for(Vector corner: corners){
            double offset= corner.dot(axis);
            min= Math.min(min, offset);
            max= Math.max(max, offset);
        }
        return new double[]{centerPosition+ min, centerPosition+ max};
    }


}
