import utils.wrappers.ElapsedTime;
import utils.math.Meth;
import utils.math.Pose;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PlasmaBeams {
    String imagePath="C:\\Users\\czran\\Downloads\\SpaceInvaders2\\SpaceInvaders\\SpaceInvaders\\src\\utils\\models\\PlasmaBeam.png";
    Image Beam;
    
    public PlasmaBeams(){
        
        try {
            Beam = ImageIO.read(new File(imagePath));
        } catch (IOException ignored){}
    }

    private final double rotationRadians= .01, movingPixels=1;
    public int plasmaMovementSpeed=5;
    public double x, y;
    public double radians;
    public void move(){
        if((x > 1920) ^ (y >1080) ) {
            x = 0;
            y = 0;
        }
        else{
            x= x + plasmaMovementSpeed * movingPixels * Math.cos(getAngle());
            y= y + plasmaMovementSpeed * movingPixels * Math.sin(getAngle());
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
        if (Beam != null) {
            Graphics2D g2d = (Graphics2D) g;

            int centerX = (int) x + (Beam.getWidth(null) / 2);
            int centerY = (int) y + (Beam.getHeight(null) / 2);

            g2d.rotate(radians+ Math.PI/2, centerX, centerY);
            g2d.drawImage(Beam, (int) x, (int) y, observer);
            g2d.rotate(-(radians+Math.PI/2), centerX, centerY);
        }
    }
    public PlasmaBeams setPosition(int x, int y, double radians){
        this.x= x;
        this.y= y;
        this.radians= radians;
        return this;
    }

    public void update(){  
        move();       
    }

}
