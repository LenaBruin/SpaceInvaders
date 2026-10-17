package game;

import game_objects.Asteroids;
import game_objects.PlasmaBeams;
import game_objects.Rocketship;
import utils.wrappers.ElapsedTime;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class Manager {
    public static volatile boolean running= false;
    JFrame frame;
    GUI gui;
    JPanel gameCanvas;
    public volatile Rocketship rocket;
    volatile ElapsedTime beamTimer;
    public static volatile ElapsedTime asteroidTimer;
    public static double timer;
    public Manager(){
        beamTimer= new ElapsedTime();
        asteroidTimer= new ElapsedTime();
        frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        int width = 1920;
        int height = 1080;
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        gui= new GUI();
        gui.setBounds(0,0, width, height);
        rocket= new Rocketship()
                .setPosition(width /2, height /2, 0);

        frame.setFocusable(true);
        frame.setLayout(null);
        gameCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(running) {
                    rocket.draw(g, this);
                    if(beamTimer.getMilliseconds()> PlasmaBeams.spawnSpeed) {
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
                    for(Asteroids a : Asteroids.asteroids)
                        drawHitbox(g, a.hitbox);
                    for(PlasmaBeams a : PlasmaBeams.beams)
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


        if (Boolean.getBoolean("autostart"))
            gui.startButton.doClick();

    }

    public void checkRocketCollisions(){
        for(Asteroids a: Asteroids.asteroids){
            boolean collision= Rocketship.hitbox.collidesWith(a.hitbox);
            if(collision && !a.rocketCollision)
                Rocketship.health-= Asteroids.rocketDamage;

            a.rocketCollision= collision;
        }
    }
    volatile boolean threadRunning= false;

    public void update(){
        rocket.update();
        frame.repaint();
        PlasmaBeams.update();
//Asteroids.update();
        if(!threadRunning)
            new Thread(()->{
                threadRunning= true;
                while (threadRunning) {

                    try {
                        checkRocketCollisions();

                        Asteroids.update();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println(Arrays.toString(e.getStackTrace()));
                        }
                    } catch (Exception ignored) {}

                }
            }).start();


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

    public static void restart(){
        try {
            String java = System.getProperty("java.home") + "/bin/java";
            String classPath = System.getProperty("java.class.path");
            new ProcessBuilder(java, "-Dautostart=true", "-cp", classPath, "game.Main")
                    .inheritIO()
                    .start();
        } catch (IOException ignored) {}
        System.exit(0);
    }
    public void showLoseScreen(){
        if(Rocketship.health > 0) return;
        SwingUtilities.invokeLater(() -> {
            gui.restartButton.setVisible(true);
            gui.loseLabel.setVisible(true);
        });
    }


}
