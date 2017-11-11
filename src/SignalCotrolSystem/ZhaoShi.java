package SignalCotrolSystem;

public class ZhaoShi extends Car implements Runnable{    
	  Text text;    
	  public ZhaoShi(int x, int y, int direct) {    
	      super(x, y, direct);    
	      // TODO Auto-generated constructor stub    
	  }    
	  public void panDuan(int x,int y,int direct){    
	      // 碰到边界，从arrAcr删除    
	      if (x < 0 || x > 700 || y < 0 || y > 700) {    
	          this.isStop = false;    
	          MainFrame.mainPanel.init();    
	      }    
	      //是否碰到灯       
	    for(int i=0;i<MainFrame.mainPanel.vtLight.size();i++){      
	        Light light=MainFrame.mainPanel.vtLight.get(i);      
	        if(direct%2==0){      
	            if(x>=light.x&&x<=light.x+50&&y>=light.y&&y<=light.y+15){    
	              if(light.redLight==true&&light.direct==direct){    
	                    text=new Text(100, 200, "报告长官：有人闯红灯！");    
	                    new Thread(text).start();    
	              }    
	            }      
	        }else{      
	            if(x>=light.x&&x<=light.x+15&&y>=light.y&&y<=light.y+50){    
	              if(light.redLight==true&&light.direct==direct){    
	                    text=new Text(100, 200, "报告长官：有人闯红灯！");    
	                    new Thread(text).start();    
	              }    
	            }      
	        }      
	    }     
	  }    
	  @Override    
	  public void run() {    
	      // TODO Auto-generated method stub    
	      while(true){    
	          if(zanTing){    
	              switch(direct){      
	                case 0:      
	                    if(stopCar){     
	                      y+=speed;       
	                    }    
	                    panDuan(x, y+35, direct);      
	                    break;      
	                case 1:      
	                    if(stopCar){    
	                      x+=speed;     
	                    }    
	                    panDuan(x+35, y, direct);      
	                    break;      
	                case 2:      
	                    if(stopCar){    
	                      y-=speed;      
	                    }    
	                    panDuan(x, y, direct);      
	                    break;      
	                case 3:      
	                    if(stopCar){    
	                      x-=speed;      
	                    }    
	                    panDuan(x, y, direct);      
	                    break;      
	                }    
	          }    
	          //如果死亡，退出线程    
	          if(isStop==false){    
	              break;    
	          }    
	          try {    
	              Thread.sleep(50);    
	          } catch (InterruptedException e) {    
	              // TODO Auto-generated catch block    
	              e.printStackTrace();    
	          }    
	      }    
	  }    
	}  