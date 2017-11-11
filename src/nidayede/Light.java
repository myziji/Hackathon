package nidayede;

public class Light implements Runnable{    
	  int x;    
	  int y;    
	  boolean redLight;    
	  boolean greenLight;    
	  int direct;    
	  //红绿灯来回切换    
	  static boolean kaiGuan=true;    
	  static boolean zanTing=true;    
	  public Light(){}     
	  public Light(int x,int y,int direct){    
	      this.x=x;      
	    this.y=y;      
	    this.direct=direct;     
	  }    
	  @Override    
	  public void run() {    
	      // TODO Auto-generated method stub    
	      while(true){    
	          if(kaiGuan){    
	              for(int i=0;i<HuPanel.vtLight.size();i++){    
	                  Light light=HuPanel.vtLight.get(i);     
	                  if(light.direct%2==0){      
	                      light.greenLight=false;      
	                      light.redLight=true;      
	                  }else{      
	                      light.greenLight=true;      
	                      light.redLight=false;      
	                  }    
	              }    
	              //切换灯    
	              this.kaiGuan=false;    
	          }else{    
	              for (int i = 0; i < HuPanel.vtLight.size(); i++) {    
	                  Light light = HuPanel.vtLight.get(i);    
	                  if (light.direct % 2 == 0) {    
	                      light.greenLight = true;    
	                      light.redLight = false;    
	                  } else {    
	                      light.greenLight = false;    
	                      light.redLight = true;    
	                  }    
	              }    
	              //切换灯    
	              this.kaiGuan=true;    
	          }    
	          try {    
	              Thread.sleep(5000);    
	          } catch (InterruptedException e) {    
	              // TODO Auto-generated catch block    
	              e.printStackTrace();    
	          }    
	      }    
	  }    
	}    
