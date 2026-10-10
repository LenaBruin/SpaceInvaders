package game_objects;

import utils.math.Pose;
import utils.wrappers.ElapsedTime;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import game.Hitbox;


public class PlasmaBeams {
    String imagePath="src/game_objects/models/PlasmaBeam.png";
    Image Beam;
    Pose pose;
    Rocketship rocket;
    ElapsedTime timer;
    public static List<Hitbox> hitboxes= new LinkedList<>();
    public Hitbox hitbox;
    public PlasmaBeams(Pose pose){

        timer= new ElapsedTime();
        hitbox= new Hitbox(100, 100);
        this.pose= pose;
        try {
            Beam = ImageIO.read(new File(imagePath));
        } catch (IOException ignored){}
    }

    private final double rotationRadians= .01, movingPixels=8;
    public void move(int direction){
        double x= pose.getX()+ direction * movingPixels * Math.cos(getAngle());
        double y= pose.getY()+ direction * movingPixels * Math.sin(getAngle());
        this.pose= new Pose(x, y, this.pose.getNormalizedAngle());
    }
    public double getAngle(){
        return getPosition().getAngle();
    }
    public double getX(){
        return getPosition().getX();
    }
    public double getY(){
        return getPosition().getY();
    }
    public Pose getPosition(){
        return this.pose;
    }
    @Deprecated
    public void rotate(int direction){
        //radians-= direction*rotationRadians;
    }

    public void draw(Graphics g, java.awt.image.ImageObserver observer) {
        if (Beam != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) pose.getX() + (Beam.getWidth(null) / 2);
            int centerY = (int) pose.getY() + (Beam.getHeight(null) / 2);

            g2d.rotate(pose.getNormalizedAngle()+ Math.PI/2, centerX, centerY);
            g2d.drawImage(Beam, (int) pose.getX(), (int) pose.getY(), observer);
            g2d.rotate(-(pose.getNormalizedAngle()+Math.PI/2), centerX, centerY);
        }
    }
    public PlasmaBeams setPosition(int x, int y, double radians){
        this.pose= new Pose(x, y, radians);
        return this;
    }


    public static List<PlasmaBeams> beams= new LinkedList<>();
    public static void spawnBeam(Rocketship rocker){
        beams.add(new PlasmaBeams(rocker.getPosition()));
    }

    public static void drawAll(Graphics g, ImageObserver observer){
        for(PlasmaBeams beam: beams) beam.draw(g, observer);
    }
    public static void update(){
        beams.removeIf(beam -> (
                beam.getX()< -100 || beam.getY()< -100 || beam.getX()> 2120 || beam.getY()> 1280
                ));
        for(PlasmaBeams beam : beams) {
            beam.move(1);
            beam.hitbox.setPosition(beam.getPosition());
        }

    }

}
