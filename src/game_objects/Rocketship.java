package game_objects;

import utils.wrappers.ElapsedTime;
import utils.wrappers.Key;
import utils.math.Meth;
import utils.math.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import game.Hitbox;
public class Rocketship{

    public Key keys;
    String imagePath="src/game_objects/models/rocketship67.png";
    Image rocket;
    public static Hitbox hitbox;
    public static double health= 100;
    public static int maxHealth= 100;

    public Rocketship(){

        keys= new Key();
        try {
            rocket = ImageIO.read(new File(imagePath));
        } catch (IOException ignored){}
        int WIDTH = 316;
        int HEIGHT = 120;
        hitbox= new Hitbox(WIDTH, HEIGHT);
        regenerationTimer= new ElapsedTime();
    }

    public static double x, y;
    public static double radians;
    public void move(int direction){
        double movingPixels = 5;
        x= x+ direction * movingPixels * Math.cos(getAngle());
        y= y- direction * movingPixels * Math.sin(getAngle());
    }
    public double getAngle(){
        return getPosition().getAngle();
    }
    @Deprecated
    public double getX(){
        return getPosition().getX();
    }
    @Deprecated
    public double getY(){
        return getPosition().getY();
    }
    public Pose getPosition(){
        return new Pose(hitbox.getPosition().getX(), hitbox.getPosition().getY(), Meth.normalize(radians));
    }
    public void rotate(int direction){
        double rotationRadians = .03;
        radians+= direction* rotationRadians;
        radians= Meth.normalize(radians);
    }

    public void draw(Graphics g, java.awt.image.ImageObserver observer) {
        if (rocket != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) x + (rocket.getWidth(null) / 2);
            int centerY = (int) y + (rocket.getHeight(null) / 2);

            double screenRotation = Math.PI/2 - radians;
            g2d.rotate(screenRotation, centerX, centerY);
            g2d.drawImage(rocket, (int) x, (int) y, observer);
            g2d.rotate(-screenRotation, centerX, centerY);
        }
    }
    public Rocketship setPosition(int x, int y, double radians){
        Rocketship.x = x;
        Rocketship.y = y;
        Rocketship.radians = radians;
        return this;
    }

    ElapsedTime regenerationTimer;
    public void update(){
        for(Key.Keys key: Key.Keys.values())
            if(keys.isPressed(key))
                if(key.equals(Key.Keys.ARROW_DOWN) || key.equals(Key.Keys.ARROW_UP))
                    move(key.direction);
                else
                    rotate(key.direction);
        keys.update();
       // hitbox.setPosition(this.getPosition());
        if(rocket != null)
            hitbox.setPosition(new Pose(x + rocket.getWidth(null) / 2.0,
                    y + rocket.getHeight(null) / 2.0,
                    -radians));

        if(regenerationTimer.getMilliseconds()> regenerationTime){
            health= Math.min(health+ regenerationHealth, maxHealth);
            regenerationTimer.reset();
        }


    }
    public static double regenerationTime= 1000;
    public static double regenerationHealth= 3;





}
