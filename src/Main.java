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
            System.out.println(manager.rocket.getPosition().getX() + " " + manager.rocket.getPosition().getY());

            }
    }
}