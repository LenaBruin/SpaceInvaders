package game;

import game_objects.Asteroids;
import game_objects.PlasmaBeams;
import game_objects.Rocketship;
import utils.wrappers.ElapsedTime;

import javax.swing.*;
import java.awt.*;

public class Manager {
    final private int width= 1920, height= 1080;
    public static boolean running= false;
    JFrame frame;
    GUI gui;
    JPanel gameCanvas;
    public volatile Rocketship rocket;
    volatile PlasmaBeams Beam;
    volatile ElapsedTime beamTimer;
    public static volatile ElapsedTime asteroidTimer;
    public static double timer;
    public double beamInterval;
    public Manager(){
        beamTimer= new ElapsedTime();
        asteroidTimer= new ElapsedTime();
        frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        gui= new GUI();
        gui.setBounds(0,0,width,height);
        rocket= new Rocketship()
                .setPosition(width/2, height/2, 0);

        frame.setFocusable(true);
        frame.setLayout(null);
        gameCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(running) {
                    rocket.draw(g, this);
                    if(beamTimer.getNanoseconds()> 5e8) {
                        PlasmaBeams.spawnBeam(rocket);
                        beamTimer.reset();
                        timer= System.nanoTime();

                    }
                    if(asteroidTimer.getNanoseconds()> 1e9){
                        Asteroids.spawnAsteroid();
                        asteroidTimer.reset();
                    }
                    PlasmaBeams.drawAll(g, this);
                    Asteroids.drawAll(g, this);
                    drawHitbox(g, Rocketship.hitbox);
                    for(Asteroids a : Asteroids.asteroids)     // opțional: și hitbox-urile asteroizilor
                        drawHitbox(g, a.hitbox);
                    for(PlasmaBeams a : PlasmaBeams.beams)     // opțional: și hitbox-urile asteroizilor
                        drawHitbox(g, a.hitbox);
                }
             //   Beam.draw(g, this);
            }
        };
        gameCanvas.setBounds(0, 0, width, height);
        gameCanvas.setOpaque(false);

        frame.addKeyListener(rocket.keys);
        frame.add(gameCanvas);
        frame.add(gui);

        frame.setVisible(true);



    }

    public void checkRocketCollisions(){
        for(Asteroids a: Asteroids.asteroids){
            boolean collision= Rocketship.hitbox.collidesWith(a.hitbox);
            if(collision && !a.rocketCollision)
                Rocketship.health-= a.rocketDamage;

            a.rocketCollision= collision;
        }
    }
    public void update(){
        rocket.update();
        frame.repaint();
        PlasmaBeams.update();
        Asteroids.update();
        checkRocketCollisions();


    }
    private void drawHitbox(Graphics g, Hitbox h){
        int[] xs = new int[4], ys = new int[4];
        for(int i = 0; i < 4; i++){
            xs[i] = (int)(h.pose.getX() + h.corners.get(i).getXComponent());
            ys[i] = (int)(h.pose.getY() + h.corners.get(i).getYComponent());
        }
        g.setColor(Color.RED);
        g.drawPolygon(xs, ys, 4);
    }

}
