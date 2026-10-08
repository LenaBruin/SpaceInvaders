package game_objects;

import utils.math.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Asteroids {
    Image asteroid;
    String imagePath= "C:\\Users\\czran\\Downloads\\SpaceInvaders\\SpaceInvaders\\SpaceInvaders\\src\\utils\\models\\asteroid.png";
    public Pose pose;
    private static final Random random = new Random();
    private static final int width = 1920, height = 1080;
    private static final int spawnMargin = 100;
    private static final int despawnMargin = 300;
    public Asteroids(){
        int side = random.nextInt(4);
        double spread = Math.PI / 3;
        double offset = (random.nextDouble() * 2 - 1) * spread;

        switch (side){
            case 0:
                pose = new Pose(-spawnMargin, random.nextInt(height), offset);
                break;
            case 1:
                pose = new Pose(width + spawnMargin, random.nextInt(height), Math.PI + offset);
                break;
            case 2:
                pose = new Pose(random.nextInt(width), -spawnMargin, Math.PI / 2 + offset);
                break;
            default:
                pose = new Pose(random.nextInt(width), height + spawnMargin, -Math.PI / 2 + offset);
                break;
        }
        try{
            asteroid = ImageIO.read(new File(imagePath));
        } catch (IOException ignored) {}
    }

    public static void update(){
        asteroids.removeIf(a ->
                a.getX() < -despawnMargin || a.getY() < -despawnMargin ||
                        a.getX() > width + despawnMargin || a.getY() > height + despawnMargin);
        for (Asteroids a : asteroids)
            a.move(1);
    }
    public void move(int direction){
        double x= pose.getX()+ direction * movingPixels * Math.cos(getAngle());
        double y= pose.getY()+ direction * movingPixels * Math.sin(getAngle());
        this.pose= new Pose(x, y, this.pose.getNormalizedAngle());
    }
    public void draw(Graphics g, java.awt.image.ImageObserver observer) {
        if (asteroid != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) pose.getX() + (asteroid.getWidth(null) / 2);
            int centerY = (int) pose.getY() + (asteroid.getHeight(null) / 2);

            g2d.rotate(pose.getNormalizedAngle()+ Math.PI/2, centerX, centerY);
            g2d.drawImage(asteroid, (int) pose.getX(), (int) pose.getY(), observer);
            g2d.rotate(-(pose.getNormalizedAngle()+Math.PI/2), centerX, centerY);
        }
    }
    public double getAngle(){
        return this.pose.getNormalizedAngle();
    }
    public double getX(){
        return this.pose.getX();
    }
    public double getY(){
        return this.pose.getY();
    }
    private int movingPixels= 4;
    public static List<Asteroids> asteroids= new LinkedList<>();

    public static void drawAll(Graphics g, ImageObserver o){
        for(Asteroids asteroid: asteroids)
            asteroid.draw(g, o);
    }
    public static void spawnAsteroid(){
        asteroids.add(new Asteroids());
    }
}
