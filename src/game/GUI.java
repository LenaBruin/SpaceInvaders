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
        JButton moneyUpgradeButton;
        JButton spawnSpedUpgradeButton;
        JButton restartButton;
        JLabel loseLabel;
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
            rocketDamageUpgradeButton.setBounds(0, 60, 200, 50);
            gameLayout.add(rocketDamageUpgradeButton);

            beamSpeedUpgradeButton = new JButton("Upgrade Beam Speed");
            beamSpeedUpgradeButton.setBounds(0, 120, 200, 50);
            gameLayout.add(beamSpeedUpgradeButton);

            moneyUpgradeButton= new JButton("Upgrade Money Gain");
            moneyUpgradeButton.setBounds(0, 180, 200, 50);
            gameLayout.add(moneyUpgradeButton);

            spawnSpedUpgradeButton= new JButton("Upgrade Beam Spawn Speed");
            spawnSpedUpgradeButton.setBounds(0, 240, 200, 50);
            gameLayout.add(spawnSpedUpgradeButton);

            restartButton = new JButton("Restart");
            restartButton.setBounds(600, 400, 200, 50);

            startButton= new JButton("Start Game");
            startButton.setBounds(600, 400, 200, 50);
            gameLayout.add(startButton);
            healthUpgradeButton.setFocusable(false);
            rocketDamageUpgradeButton.setFocusable(false);
            beamSpeedUpgradeButton.setFocusable(false);
            moneyUpgradeButton.setFocusable(false);
            spawnSpedUpgradeButton.setFocusable(false);
            restartButton.setFocusable(false);
            restartButton.setVisible(false);
            restartButton.addActionListener(_->Manager.restart());
            add(restartButton);
            startButton.setFocusable(false);
            loseLabel = new JLabel("YOU LOST!", SwingConstants.CENTER);
            loseLabel.setForeground(Color.RED);
            loseLabel.setBounds(300, 280, 800, 120);
            loseLabel.setOpaque(true);
            loseLabel.setBackground(Color.YELLOW);
            loseLabel.setFont(new Font("SansSerif", Font.BOLD, 96));
            loseLabel.setVisible(false);
            add(loseLabel);
            startButton.addActionListener(_->{
                startButton.setVisible(false);
                Manager.running= true;
                repaint();
                add(healthUpgradeButton);
                add(rocketDamageUpgradeButton);
                add(beamSpeedUpgradeButton);
                add(moneyUpgradeButton);
                add(spawnSpedUpgradeButton);

            });
            add(startButton);

            healthUpgradeButton.addActionListener(_ -> Upgrades.upgradeHealth());
            rocketDamageUpgradeButton.addActionListener(_ -> Upgrades.upgradeRocketDamage());
            beamSpeedUpgradeButton.addActionListener(_ -> Upgrades.upgradeBeamSpeed());
            moneyUpgradeButton.addActionListener(_ -> Upgrades.upgradeMoneyGain());
            spawnSpedUpgradeButton.addActionListener(_->Upgrades.upgradeSpawnSpeed());

        }
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            try{
                g.drawImage(background, 0, 0, super.getWidth(), super.getHeight(), this);
            }
            catch (NullPointerException ignored){}



        }
        @Deprecated
        public boolean isRunning(){
            return Manager.running;
        }


    }
