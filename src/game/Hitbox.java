package game;

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


    public void setPosition(Pose pose){
        this.pose= pose;
    }

    public void setPosition(int x, int y){
        this.pose= new Pose(x, y, this.pose.getNormalizedAngle());
    }



}
