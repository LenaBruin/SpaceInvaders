package utils.wrappers;

public class ElapsedTime {
    long startTime;
    public ElapsedTime(){
        reset();
    }
    public void reset(){
        startTime= System.nanoTime();
    }
    long calculate(){
        return System.nanoTime()- startTime;
    }
    public long getNanoseconds(){
        return calculate();
    }
    public double getMilliseconds(){
        return calculate()/1e6;
    }
    public double getSeconds(){
        return calculate()/1e9;
    }
    public long getStartTime(){
        return this.startTime;
    }
    public void setSeconds(double seconds){
        this.startTime= (long) ((long)seconds * 1e9);
    }
    public void setMilliseconds(double millis){
        this.startTime= (long)((long)millis * 1e9);
    }
    public void setNanoseconds(long nanoseconds){
        this.startTime= nanoseconds;
    }

}
