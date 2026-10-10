package game;

import game_objects.Asteroids;
import game_objects.Rocketship;
import utils.math.Vector;
import utils.wrappers.ElapsedTime;

public class Main{

Manager manager;
public static void main(String[] args){
        new Main().run();
    }
    public void run(){
        manager= new Manager();
        ElapsedTime timer= new ElapsedTime();
        while (true){
            if(Manager.running) {
                if(timer.getMilliseconds()> 10) {
                    manager.update();
                    timer.reset();
                }
            }
//            for(Vector corner: Rocketship.hitbox.corners)
//                System.out.println(corner.getXComponent()+" "+ corner.getYComponent());
//            System.out.println("\n\n\n");
            boolean collides= false;
            for(Asteroids a: Asteroids.asteroids)
                if(Rocketship.hitbox.collidesWith(a.hitbox))
                    collides= true;
            System.out.println(Rocketship.health);
            if(Rocketship.health<= 0) {
                //break;
                //implement you lose screen
            }

            }
    }
}