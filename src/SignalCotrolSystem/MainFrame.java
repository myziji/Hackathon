package SignalCotrolSystem;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
import java.util.*;  
import java.util.concurrent.CopyOnWriteArrayList;  
  
public class MainFrame extends JFrame implements ActionListener {  
    static MainPanel mainPanel;  
    JMenuBar jMenuBar;  
    JMenu jMenu1, jMenu2;  
    JMenuItem jMenuItem1, jMenuItem2, jMenuItem3, jMenuItem4;  
    StartPanel startPanel;  
  
    public static void main(String[] args) {  
        new MainFrame();  
    }  
  
    public MainFrame() {  
        jMenuBar = new JMenuBar();  
        jMenu1 = new JMenu("Control System");  
        jMenuItem1 = new JMenuItem("Start");  
        jMenuItem1.addActionListener(this);  
        jMenuItem2 = new JMenuItem("Pause");  
        jMenuItem2.addActionListener(this);  
        jMenuItem3 = new JMenuItem("Exit");  
        jMenuItem3.addActionListener(this);  
        jMenu1.add(jMenuItem1);  
        jMenu1.add(jMenuItem2);  
        jMenu1.add(jMenuItem3);  
        jMenu2 = new JMenu("Control");  
        jMenuItem4 = new JMenuItem("Settings");  
        jMenuItem4.addActionListener(this);  
        jMenu2.add(jMenuItem4);  
        jMenuBar.add(jMenu1);  
        jMenuBar.add(jMenu2);  
        this.setJMenuBar(jMenuBar);  
  
        startPanel = new StartPanel();  
        new Thread(startPanel).start();  
        this.add(startPanel);  
        this.setTitle("Simulate signal");  
        this.setSize(700, 700);  
        this.setResizable(false);  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setVisible(true);  
    }  
  
    @Override  
    public void actionPerformed(ActionEvent e) {  
        // TODO Auto-generated method stub  
        if (e.getSource() == jMenuItem1) {  
            startPanel.isLive = false;  
            this.remove(startPanel);  
            mainPanel = new MainPanel();  
            this.add(mainPanel);  
            this.setVisible(true);  
        } else if (e.getSource() == jMenuItem2) {  
            if (jMenuItem2.getText().equals("Pause")) {  
                Car.zanTing = false;  
                mainPanel.stop = false;  
                Light.kaiGuan = false;  
                jMenuItem2.setText("Next");  
            } else if (jMenuItem2.getText().equals("Next")) {  
                Car.zanTing = true;  
                mainPanel.stop = true;  
                Light.kaiGuan = true;  
                jMenuItem2.setText("Pause");  
            }  
        } else if (e.getSource() == jMenuItem3) {  
            System.exit(0);  
        } else if (e.getSource() == jMenuItem4) {  
            new Set(this, "Settings", true);  
        }  
    }  
}  
  
class StartPanel extends JPanel implements Runnable {  
    int info = 0;  
    boolean isLive = true;  
  
    public void paint(Graphics g) {  
        super.paint(g);  
        g.fillRect(0, 0, 700, 700);  
        g.setColor(Color.red);  
        g.setFont(new Font("微软雅黑", Font.BOLD, 30));  
        if (info % 2 == 0) {  
            g.drawString("Control system", 230, 300);  
        }  
    }  
  
    @Override  
    public void run() {  
        // TODO Auto-generated method stub  
        while (true) {  
            try {  
                Thread.sleep(100);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            info++;  
            this.repaint();  
            if (isLive == false) {  
                break;  
            }  
        }  
    }  
}  
  
class MainPanel extends JPanel implements Runnable {  
    static CopyOnWriteArrayList<MyCar> vtCar = new CopyOnWriteArrayList<MyCar>();  
    static CopyOnWriteArrayList<Light> vtLight = new CopyOnWriteArrayList<Light>();   
    Random r = new Random();  
    boolean stop = true;  
    static int carNum = 15;  
    boolean creatMyCar = true;  
  
    public MainPanel() {  
        init();  
        vtLight.add(new Light(306, 285, 0));  
        vtLight.add(new Light(285, 365, 1));  
        vtLight.add(new Light(365, 406, 2));  
        vtLight.add(new Light(406, 305, 3));  
        // 启动灯线程  
        Thread t = new Thread(new Light());  
        t.start();  
        // 启动生成对象车线程  
        new Thread(this).start();  
    }  
  
    // 生成肇事车  
    public void init() {  
         
    }  
  
    // JFrame自动调用paint，paint是JPanel父类的方法，  
    // 所以先super调用父类的paint方法  
    public void paint(Graphics g) {  
        super.paint(g);  
        // 画十字车道  
        g.setColor(Color.darkGray);  
        g.fillRect(0, 300, 700, 6);  
        g.fillRect(0, 400, 700, 6);  
        g.fillRect(300, 0, 6, 700);  
        g.fillRect(400, 0, 6, 700);  
        g.setColor(Color.gray);  
        g.fillRect(0, 350, 700, 2);  
        g.fillRect(350, 0, 2, 700);  
        g.setColor(Color.blue);  
        // 画灯  
        for (int i = 0; i < vtLight.size(); i++) {  
            Light light = vtLight.get(i);  
            g.setColor(Color.black);  
            if (light.direct % 2 == 0) {  
                g.fillRect(light.x, light.y, 35, 15);  
            } else {  
                g.fillRect(light.x, light.y, 15, 35);  
            }  
            // 绿灯  
            g.setColor(Color.green);  
            if (light.greenLight == true) {  
                if (light.direct % 2 == 0) {  
                    g.fillOval(light.x, light.y + 2, 10, 10);  
                } else {  
                    g.fillOval(light.x + 2, light.y, 10, 10);  
                }  
            }  
            // 红灯  
            g.setColor(Color.red);  
            if (light.redLight == true) {  
                if (light.direct % 2 == 0) {  
                    g.fillOval(light.x + 20, light.y + 2, 10, 10);  
                } else {  
                    g.fillOval(light.x + 2, light.y + 20, 10, 10);  
                }  
            }  
        }  
        // 画车  
        Iterator<MyCar> iter = MainFrame.mainPanel.vtCar.iterator();  
        while (iter.hasNext()) {  
            MyCar mycar = iter.next();  
            g.setColor(mycar.colorCar);  
            if (mycar.direct % 2 == 0) {  
                g.fill3DRect(mycar.x, mycar.y, 20, 35, true);  
            } else {  
                g.fill3DRect(mycar.x, mycar.y, 35, 20, true);  
            }  
        }  

    }  
  
    // 得到到十字路口要转的方向  
    public int getDirect(int d) {  
        int i = r.nextInt(4);  
        while (true) {  
            if (i == d) {  
                i = r.nextInt(4);  
            } else {  
                break;  
            }  
        }  
        return i;  
    }  
  
    @Override  
    public void run() {  
        MyCar myCar = null;  
        int newDirect;  
        // TODO Auto-generated method stub  
        while (true) {  
            if (stop) {  
                if (vtCar.size() < carNum) {  
                    if (this.creatMyCar) {  
                        switch (r.nextInt(4)) {  
                        case 0:  
                            newDirect = getDirect(0);  
                            myCar = new MyCar(315, 0, 0, newDirect, Color.blue);  
                            vtCar.add(myCar);  
                            break;  
                        case 1:  
                            newDirect = getDirect(1);  
                            myCar = new MyCar(0, 365, 1, newDirect, Color.green);  
                            vtCar.add(myCar);  
                            break;  
                        case 2:  
                            newDirect = getDirect(2);  
                            myCar = new MyCar(365, 700, 2, newDirect, Color.cyan);  
                            vtCar.add(myCar);  
                            break;  
                        case 3:  
                            newDirect = getDirect(3);  
                            myCar = new MyCar(700, 315, 3, newDirect, Color.orange);  
                            vtCar.add(myCar);  
                            break;  
                        }  
                        new Thread(myCar).start();  
                    }  
                }  
            }  
  
            try {  
                Thread.sleep(500);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
        }  
    }  
}