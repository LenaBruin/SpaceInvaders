import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel {
    Image background;
    JButton startButton;
    String backgroundPath="C:\\Users\\czran\\Downloads\\SpaceInvaders2\\SpaceInvaders\\SpaceInvaders\\src\\utils\\models\\background3.jpg";
    public GUI(){

        setLayout(new GridBagLayout());
        try{
            background= ImageIO.read(new File(backgroundPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startButton= new JButton("Start Game");
        startButton.setBounds(300, 250, 200, 50);
        startButton.addActionListener(e->{
            startButton.setVisible(false);
            Manager.running= true;
            repaint();
        });
        add(startButton);


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
