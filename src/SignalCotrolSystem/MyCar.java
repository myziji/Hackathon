package SignalCotrolSystem;
import java.awt.Color;
import java.util.Iterator;  

public class MyCar extends Car implements Runnable {  
    // 到十字路口，往哪个方向  
    int newDirect;  
    Color colorCar;  
    public boolean exit = true;  
  
    public MyCar(int x, int y, int direct, int newDirect, Color colorCar) {  
        // TODO Auto-generated constructor stub  
        super(x, y, direct);  
        this.newDirect = newDirect;  
        this.colorCar = colorCar;  
    }  
  
    // 换方向  
    @Override  
    public void run() {  
        while (exit) {  
            if (zanTing) {  
                switch (direct) {  
                case 0:  
                    switch (newDirect) {  
                    case 1:  
                        if (y > 358)  
                            direct = newDirect;  
                        break;  
                    case 3:  
                        if (y > 302)  
                            direct = newDirect;  
                        break;  
                    }  
                    ////////  
                    if (stopCar) {  
                        y += speed;  
                    }  
                    panDuan(x, y + 35, direct);  
                    break;  
                case 1:  
                    switch (newDirect) {  
                    case 0:  
                        if (x > 302)  
                            direct = newDirect;  
                        break;  
                    case 2:  
                        if (x > 358)  
                            direct = newDirect;  
                        break;  
                    }  
                    ////////  
                    if (stopCar) {  
                        x += speed;  
                    }  
                    panDuan(x + 35, y, direct);  
                    break;  
                case 2:  
                    switch (newDirect) {  
                    case 1:  
                        if (y < 385)  
                            direct = newDirect;  
                        break;  
                    case 3:  
                        if (y < 335)  
                            direct = newDirect;  
                        break;  
                    }  
                    ////////  
                    if (stopCar) {  
                        y -= speed;  
                    }  
                    panDuan(x, y, direct);  
                    break;  
                case 3:  
                    switch (newDirect) {  
                    case 0:  
                        if (x < 335)  
                            direct = newDirect;  
                        break;  
                    case 2:  
                        if (x < 385)  
                            direct = newDirect;  
                        break;  
                    }  
                    ////////  
                    if (stopCar) {  
                        x -= speed;  
                    }  
                    panDuan(x, y, direct);  
                    break;  
                }  
            }  
            // 碰到边界，删除  
            if (x < -100 || x > 910 || y < -100 || y > 910) {  
                MainFrame.mainPanel.vtCar.remove(this);  
                this.exit = false;// 如果死亡，退出线程  
                this.isStop=false;  
            }  
            MainFrame.mainPanel.repaint();  
            try {  
                Thread.sleep(50);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    public void panDuan(int x, int y, int direct) {  
        // 车碰车  
        Iterator<MyCar> iter = MainFrame.mainPanel.vtCar.iterator();  
        while (iter.hasNext()) {  
            MyCar mycar = iter.next();  
            if (direct % 2 == 0) {  
                // if(x>=car.x&&x<=car.x+20&&y>=car.y&&y<=car.y+35){  
                if (x >= mycar.x && x <= mycar.x + 20 && y >= mycar.y && y <= mycar.y + 40) {  
                    if (mycar.stopCar == false) {  
                        stopCar = false;  
                    } else {  
                        stopCar = true;  
                    }  
                }  
            } else {  
                if (x >= mycar.x && x <= mycar.x + 40 && y >= mycar.y && y <= mycar.y + 20) {  
                    if (mycar.stopCar == false) {  
                        stopCar = false;  
                    } else {  
                        stopCar = true;  
                    }  
                }  
            }  
            if(this.isStop==false){  
                MainFrame.mainPanel.vtCar.remove(mycar);  
            }  
        }  
        // 是否碰到灯  
        for (int i = 0; i < MainFrame.mainPanel.vtLight.size(); i++) {  
            Light light = MainFrame.mainPanel.vtLight.get(i);  
            if (direct % 2 == 0) {  
                if (x >= light.x && x <= light.x + 50 && y >= light.y && y <= light.y + 15) {  
                    if (light.direct == direct) {  
                        if (light.redLight == true) {  
                            stopCar = false;  
                        } else {  
                            stopCar = true;  
                            Iterator<MyCar> iter1 = MainFrame.mainPanel.vtCar.iterator();  
                            while (iter1.hasNext()) {  
                                MyCar mycar = iter1.next();  
                                if (light.direct == mycar.direct) {  
                                    mycar.stopCar = true;  
                                }  
                            }  
                        }  
                    }  
                }  
            } else {  
                if (x >= light.x && x <= light.x + 15 && y >= light.y && y <= light.y + 50) {  
                    if (light.direct == direct) {  
                        if (light.redLight == true) {  
                            stopCar = false;  
                        } else {  
                            stopCar = true;  
                            Iterator<MyCar> iter2 = MainFrame.mainPanel.vtCar.iterator();  
                            while (iter2.hasNext()) {  
                                MyCar mycar = iter2.next();  
                                if (light.direct == mycar.direct) {  
                                    mycar.stopCar = true;  
                                }  
                            }  
                        }  
                    }  
                }  
            }  
        }  
  
    }  
}  