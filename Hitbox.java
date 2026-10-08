import utils.math.Pose;

public class Hitbox {
    public int length, height;
    Pose pose;
    public Hitbox(int length, int height){
        this.length= length;
        this.height= height;
        pose= new Pose();
    }
    public Hitbox(int length, int height, Pose pose){
        this(length, height);
        this.pose= pose;
    }

    public void setPosition(Pose pose){
        this.pose= pose;
    }
}
