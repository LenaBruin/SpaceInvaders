import utils.wrappers.Key;
import utils.math.Meth;
import utils.math.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Rocketship{

    Key keys;
    String imagePath="C:\\Users\\czran\\Downloads\\SpaceInvaders2\\SpaceInvaders\\SpaceInvaders\\src\\utils\\models\\rocketship2.png";
    Image rocket;
    public Rocketship(){

        keys= new Key();
        try {
            rocket = ImageIO.read(new File(imagePath));
        } catch (IOException ignored){}
    }

    private final double rotationRadians= .01, movingPixels=1;
    public double x, y;
    public double radians;
    public void move(int direction){
        x= x+ direction * movingPixels * Math.cos(getAngle());
        y= y+ direction * movingPixels * Math.sin(getAngle());
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
        if (rocket != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) x + (rocket.getWidth(null) / 2);
            int centerY = (int) y + (rocket.getHeight(null) / 2);

            g2d.rotate(radians+ Math.PI/2, centerX, centerY);
            g2d.drawImage(rocket, (int) x, (int) y, observer);
            g2d.rotate(-(radians+Math.PI/2), centerX, centerY);
        }
    }
    public Rocketship setPosition(int x, int y, double radians){
        this.x= x;
        this.y= y;
        this.radians= radians;
        return this;
    }

    public void update(){
        for(Key.Keys key: Key.Keys.values())
            if(keys.isPressed(key))
                if(key.equals(Key.Keys.ARROW_DOWN) || key.equals(Key.Keys.ARROW_UP))
                    move(key.direction);
                else
                    rotate(key.direction);
        keys.update();

    }





}
