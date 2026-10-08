import utils.wrappers.ElapsedTime;
import utils.math.Meth;
import utils.math.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


public class Asteroid {
    String imagePath="SpaceInvaders\\src\\utils\\models\\BeautifulAsteroid.png";
    Image asteroid;
    
    public Asteroid(){
        
        try {
            asteroid = ImageIO.read(new File(imagePath));
        } catch (IOException ignored){}
    }

    private final double rotationRadians= .01, movingPixels=1;
    public int plasmaMovementSpeed=5;
    public double x, y;
    public double radians;
    Random randomGenerator = new Random(10);
    int randomXVelocity = randomGenerator.nextInt(3) + 2;
    int randomYVelocity = randomGenerator.nextInt(2) + 1;
    double randomAngle = 2*randomGenerator.nextDouble(Math.PI) - Math.PI;
    public int totalAsteroids = 0;
    public void move(){
        if((x > 1920) ^ (y > 1080) ^ (x < -60) ^ (y < -60)) {
            x = 300;
            y = 300;
            totalAsteroids = totalAsteroids + 1;
            randomXVelocity = randomGenerator.nextInt(3) + 2;
            randomYVelocity = randomGenerator.nextInt(2) + 1;
            randomAngle = 2*randomGenerator.nextDouble(Math.PI);
        }
        else{
            x= x + randomXVelocity * movingPixels * Math.cos(randomAngle) * (totalAsteroids+10)/10;
            y= y + randomYVelocity * movingPixels * Math.sin(randomAngle) * (totalAsteroids+10)/10;
        }
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
        return new Pose(this.x, this.y, Meth.normalize(radians));
    }
    public void rotate(int direction){
        radians-= direction*rotationRadians;
    }

    public void draw(Graphics g, java.awt.image.ImageObserver observer) {
        if (asteroid != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) x + (asteroid.getWidth(null) / 2);
            int centerY = (int) y + (asteroid.getHeight(null) / 2);

            g2d.rotate(radians+ Math.PI/2, centerX, centerY);
            g2d.drawImage(asteroid, (int) x, (int) y, observer);
            g2d.rotate(-(radians+Math.PI/2), centerX, centerY);
        }
    }
    public Asteroid setPosition(int x, int y, double radians){
        this.x= x;
        this.y= y;
        this.radians= radians;
        return this;
    }

    public void update(){  
        move();       
    }

}
