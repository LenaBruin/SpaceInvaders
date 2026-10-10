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

    public void update(){
        rocket.update();
        frame.repaint();
        PlasmaBeams.update();
        Asteroids.update();

    }

}
