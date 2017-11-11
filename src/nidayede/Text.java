package nidayede;

public class Text implements Runnable{    
	  static int x;    
	  static int y;    
	  static String info="";    
	  static boolean isLive=false;    
	  static int times;    
	  public Text(){}    
	  public Text(int x,int y,String info){    
	      this.x=x;    
	      this.y=y;    
	      this.info=info;    
	  }    
	  @Override    
	  public void run() {    
	      // TODO Auto-generated method stub    
	      while(true){    
	          if(isLive){    
	              try {    
	                  Thread.sleep(3000);    
	              } catch (InterruptedException e) {    
	                  // TODO Auto-generated catch block    
	                  e.printStackTrace();    
	              }    
	              info="";    
	              isLive=false;    
	          }    
	          if(isLive==false){    
	              break;    
	          }    
	      }    
	  }    
	}   