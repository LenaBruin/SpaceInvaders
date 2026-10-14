package game;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel {
    Image background;
    JButton startButton;
    String backgroundPath="src/game_objects/models/background3.jpg";
    JButton healthUpgradeButton;
    JButton rocketDamageUpgradeButton;
    JButton beamSpeedUpgradeButton;
    public GUI(){
        
        JPanel gameLayout = new JPanel();
        this.setLayout(null);
        //gameLayout.setLayout(new GridBagLayout());
        try{
            background= ImageIO.read(new File(backgroundPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        healthUpgradeButton = new JButton("Upgrade Rocket Health");
        healthUpgradeButton.setBounds(0, 0, 200, 50);
        gameLayout.add(healthUpgradeButton);

        rocketDamageUpgradeButton = new JButton("Upgrade Beam Damage");
        rocketDamageUpgradeButton.setBounds(210, 0, 200, 50);
        gameLayout.add(rocketDamageUpgradeButton);

        beamSpeedUpgradeButton = new JButton("Upgrade Beam Speed");
        beamSpeedUpgradeButton.setBounds(0, 60, 200, 50);
        gameLayout.add(beamSpeedUpgradeButton);

        startButton= new JButton("Start Game");
        startButton.setBounds(810, 515, 200, 50);
        gameLayout.add(startButton);
        healthUpgradeButton.setFocusable(false);
        rocketDamageUpgradeButton.setFocusable(false);
        beamSpeedUpgradeButton.setFocusable(false);
        startButton.setFocusable(false);
        startButton.addActionListener(e->{
            startButton.setVisible(false);
            Manager.running= true;
            repaint();
            add(healthUpgradeButton);
            add(rocketDamageUpgradeButton);
            add(beamSpeedUpgradeButton);
        });
        add(startButton);

        healthUpgradeButton.addActionListener(health->{
            Upgrades.upgradeHealth();
        });
        
        rocketDamageUpgradeButton.addActionListener(damage->{
            Upgrades.upgradeRocketDamage();
        });

        beamSpeedUpgradeButton.addActionListener(speed->{
            Upgrades.upgradeBeamSpeed();
        });

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        try{
            g.drawImage(background, 0, 0, super.getWidth(), super.getHeight(), this);
        }
        catch (NullPointerException ignored){}



    }
    public boolean isRunning(){
        return Manager.running;
    }


}
