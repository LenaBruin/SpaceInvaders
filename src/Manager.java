import javax.swing.*;
import java.awt.*;

public class Manager {
    final private int width= 1920, height= 1080;
    public static boolean running= false;
    JFrame frame;
    GUI gui;//
    JPanel gameCanvas;
    volatile Rocketship rocket;
    volatile PlasmaBeams Beam;
    volatile Asteroid asteroid;
    public Manager(){
        frame= new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        gui= new GUI();
        gui.setBounds(0,0,width,height);
        rocket= new Rocketship()
                .setPosition(width/2, height/2, 0);
        Beam = new PlasmaBeams()
                .setPosition(0, 0, 0);
        asteroid = new Asteroid()
                .setPosition(0, 0, 0);
        frame.setFocusable(true);
        frame.setLayout(null);
        gameCanvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if(running) {
                    rocket.draw(g, this);
                    Beam.draw(g, this);
                    asteroid.draw(g, this);}
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
        Beam.update();
        asteroid.update();
        frame.repaint();
    }

}
