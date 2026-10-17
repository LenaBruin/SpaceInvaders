package game;

import game_objects.Rocketship;

public class Main{

volatile Manager manager;
 static void main(){
        new Main().run();
    }
    public void run(){
        manager= new Manager();
        while (true){
            if(Manager.running){
                new Thread(()->{
                    while (Manager.running) {
                        if(Rocketship.health<= 0) {
                            Manager.running = false;
                        }
                        manager.update();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ignored) {}
                    }

                    manager.showLoseScreen();
                }).start();

                break;

            }
        }



    }
}
