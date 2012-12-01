
package hackathon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
//-------------------main body-----------------------
public class GameWindow extends JFrame implements Runnable{
    private Graphics db;
    private Image dbi;
    private String s="";
    private int x=170,y=120,xDirection,yDirection;
    private Image room = new ImageIcon(getClass().getResource("background.jpg")).getImage();
    private Image bacteria1 = new ImageIcon(getClass().getResource("germs 1.png")).getImage();
    private Image player = new ImageIcon(getClass().getResource("right.png")).getImage();
    private Rectangle[] recs =  new Rectangle[25];
    private Rectangle p;
    private Rectangle getFirstEnemies;
    private Rectangle bacte;
    boolean isCollision = false;
    int xPrevious=x,yPrevious=y;
    boolean isLeft,isRight,isUp,isDown;
    boolean isCrossed = false;
    boolean keepMoving = false;
    boolean gameOver = false;
    int bx=0,by=0;
    Random r ;
    Rectangle tip1;
    Rectangle tip2;
    Rectangle tip3;
     Rectangle tip4;
    String st = "Good Morning brush you teeth now\nor germs will attack";
    Font f = new Font("Arial",Font.BOLD,20);
    Font f2 = new Font("Arial",Font.BOLD,16);
    Font f3 = new Font("Arial",Font.BOLD,18);
    Font f4 = new Font("Times New Roman",Font.BOLD,32);
    
    boolean tipStart= true;
    boolean tipEnd = false;
    boolean brush = false;
    
    //----------------- constructor------------------------
    GameWindow(){
        setTitle("Healthy Day");
        setSize(700,700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new TAdapter());
        //---------------temp
        addMouseListener(new MListener());
        //---------------temp
        recs[0] = new Rectangle(321,486,328,15);
        recs[1] = new Rectangle(47,486,225,10);
        recs[2] = new Rectangle(51,363,180,10);
        recs[3] = new Rectangle(48,216,179,10);
        recs[4] = new Rectangle(90,344,40,10);
        recs[5] = new Rectangle(429,346,220,10);
        recs[6] = new Rectangle(316,31,10,329);
        recs[7] = new Rectangle(214,46,84,41);
        recs[8] = new Rectangle(59,49,87,120);
        recs[9] = new Rectangle(242,89,30,15);
        recs[10] = new Rectangle(621,240,50,40);
        recs[11] = new Rectangle(331,136,42,60);
        recs[12] = new Rectangle(132,314,50,38);
        recs[13] = new Rectangle(48,295,34,53);
        recs[14] = new Rectangle(460,115,85,90);
        recs[15] = new Rectangle(489,208,30,30);
        recs[16] = new Rectangle(485,82,30,30);
        recs[17] = new Rectangle(437,148,30,26);
        recs[18] = new Rectangle(547,150,30,30);
        recs[19] = new Rectangle(91,382,40,40);
        recs[20] = new Rectangle(63,440,89,50);
        recs[21] = new Rectangle(212,297,20,70);
        recs[22] = new Rectangle(340,217,23,26);
        recs[23] = new Rectangle(340,103,23,26);
        recs[24] = new Rectangle(158,51,23,24);
        p = new Rectangle(x,y,40,60);
        getFirstEnemies = new Rectangle(250,80,10,10);
        tip1 = new Rectangle(36,46,180,90);
        tip2 = new Rectangle(165,298,60,30);
        tip3 = new Rectangle(116,313,100,60);
        tip4 = new Rectangle(290,280,200,150);
        bacte = new Rectangle(bx,by,10,10);
    }
    //-------------Buffred Image--------------------
    @Override
    public void paint(Graphics g){
        dbi = createImage(getWidth(),getHeight());
        db = dbi.getGraphics();
        paintComponent(db);
        g.drawImage(dbi, 0, 0, this);  
    }
    //-------------painting JFrame------------------
    public void paintComponent(Graphics g){
       g.drawImage(room, 0, 0, null);
       g.drawImage(player, x, y, null);
       g.setColor(Color.black);
       g.drawString(s,350,350);
       g.setColor(new Color(50,50,50,0));
       g.drawRect(p.x+10, p.y, p.width, p.height);
       g.drawRect(getFirstEnemies.x, getFirstEnemies.y, getFirstEnemies.width, getFirstEnemies.height);
       g.drawImage(bacteria1, bx, by, rootPane);
       g.drawRect(bacte.x, bacte.y, bacte.width, bacte.height);
       if(tipStart){
        g.setColor(Color.pink);
        g.fillRect(tip1.x,tip1.y,tip1.width,tip1.height);
        g.setColor(Color.black);
        g.setFont(f);       
        g.drawString("Hurry Up Brush",43,65);
        g.drawString("Your teeth OR",43,90);
        g.drawString("Germs will kill you",43,120);
       }
       g.setColor(Color.yellow);
       g.fillRect(tip2.x, tip2.y, tip2.width, tip2.height);
       g.setColor(Color.black);
       g.setFont(f2);
       g.drawString("Brush",174,315);
       if(tip2.intersects(p)){
           brush = true;
       }
       
       if(brush){
            bacte = new Rectangle(0,0,0,0);
            bacteria1 = null;
            g.setColor(Color.cyan);
            g.fillRect(tip3.x, tip3.y, tip3.width, tip3.height);
            g.setColor(Color.black);
            g.setFont(f3);
            g.drawString("Hurray !!!",120,330);
            g.drawString("Wonderful",120,350);
            g.drawString("Job",145,370);
       }
       if(p.intersects(bacte)){
           gameOver = true;
       }
       if(gameOver){
            g.setColor(Color.white);
            g.fillRect(tip4.x, tip4.y, tip4.width, tip4.height);
            g.setColor(Color.red);
            g.setFont(f4);
            g.drawString(" -_- !",330,310);
            g.drawString("You have a",330,350);
            g.drawString("Disease",330,390);
       }
       repaint();
    }
    //-------------------set direction----------------------
    public void setXDirection(int xDir){
            xDirection = xDir ;
    }
    public void setYDirection(int yDir){
        yDirection = yDir;
    }
    //-------------------getrandom direction
    
    //-------------------move the player----------------------
    public void move(){
            if(getFirstEnemies.intersects(p)){
                isCrossed = true;
            }
            for(int i=0 ; i<recs.length;i++){
                if(recs[i].intersects(p)){
                    isCollision = true;
                    break;
                }
                else
                    isCollision = false;
            }
            if(isCollision){
                x = xPrevious;
                p.x = x;
                y = yPrevious;
                p.y = y;
            }
            else{
                x += xDirection;
                if(isLeft)
                    xPrevious = x+1;
                if(isRight)
                    xPrevious = x-1;
                p.x += xDirection;
                y += yDirection;
                if(isUp)
                    yPrevious = y+1;
                if(isDown)
                    yPrevious = y-1;
                p.y += yDirection;
            }
            reset();
    }
    public void reset(){
        isLeft = false;
        isRight = false;
        isUp = false;
        isDown = false;
    }
    public void findPath(){
        if(bx<x){
            bx++;
            bacte.x++;
        }
        if(bx>x){
            bx--;
            bacte.x--;
    }
        if(by<y){
            by++;
            bacte.y++;
        }
        if(by>y){
            by--;
            bacte.y--;
        }
        
    }
    //--------------------run thread-------------------------------
    @Override
    public void run() {
        while(true){
    
            try{
                if(tipStart){
                    Thread.sleep(3000);
                    tipStart = false;
                }
                else if(gameOver){
                    Thread.interrupted();
                }
                else{
                    findPath();
                    move();
                    Thread.sleep(5);
                }
                    }catch(Exception e){System.out.println(e.getMessage());}
                }
        
    }
    //---------------------keyboard listener----------------------------
    public class TAdapter extends KeyAdapter{
        //-----------------keypressed----------------------------
        @Override
        public void keyPressed(KeyEvent e){     
            if(e.getKeyCode()==e.VK_LEFT){
                isLeft = true;
                player = new ImageIcon(getClass().getResource("char-5.gif")).getImage();
                if(x<=0){
                  x = 0;
                  p.x = 0;
                }
                else
                    setXDirection(-2);
            }
            if(e.getKeyCode()==e.VK_RIGHT){
                isRight = true;
                player = new ImageIcon(getClass().getResource("player.gif")).getImage();
              if(x>=630){
                    x = 630;
                    p.x = 630;
              }
                else
                    setXDirection(2);
            }
            if(e.getKeyCode()==e.VK_UP){
                isUp = true;
                player = new ImageIcon(getClass().getResource("char-4.gif")).getImage();
                if(y<=30){
                    y = 30;
                    p.y = 30;
                }
                    else 
                        setYDirection(-2);
            }
            if(e.getKeyCode()==e.VK_DOWN){
                 isDown = true;
                 player = new ImageIcon(getClass().getResource("char-6.gif")).getImage();
                 if(y>=630){
                    y = 630;
                    p.y = 630;
                 }
                 
                else
                    setYDirection(2);
            }
        }
        //-----------------keyReleased-----------------
        @Override
        public void keyReleased(KeyEvent e){
            if(e.getKeyCode()==e.VK_LEFT){
                player = new ImageIcon(getClass().getResource("left.png")).getImage();
                setXDirection(0);
            }
            if(e.getKeyCode()==e.VK_RIGHT){
                player = new ImageIcon(getClass().getResource("right.png")).getImage();
                setXDirection(0);
            }
            if(e.getKeyCode()==e.VK_UP){
                player = new ImageIcon(getClass().getResource("up.png")).getImage();
                setYDirection(0);
            }
            if(e.getKeyCode()==e.VK_DOWN){
                player = new ImageIcon(getClass().getResource("down.png")).getImage();
                setYDirection(0);
            }
        }
    }
    //--------temp mouse listener class-------------
    public class MListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            s = "X: "+e.getX()+" Y: "+e.getY();
        }
    }
    
}
