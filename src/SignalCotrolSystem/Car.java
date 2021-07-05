package SignalCotrolSystem;

public class Car{    
    int x;    
    int y;    
    static int speed=10;    
    int direct;    
    //是否停车      
    boolean stopCar=true;    
    //停止线程    
    boolean isStop=true;    
    //暂停线程    
    static boolean zanTing=true;    
    boolean isLive=true;    
    public Car(int x,int y,int direct){    
        this.x=x;    
        this.y=y;    
        this.direct=direct;    
    }    
}  
