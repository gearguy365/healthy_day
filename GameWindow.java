
package hackathon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameWindow extends JFrame implements Runnable{
    private Graphics db;
    private Image dbi;
    private String s="";
    private int x=400,y=300,xDirection,yDirection;
    private Image room = new ImageIcon(getClass().getResource("background.jpg")).getImage();
    private Image bacteria1;
    private Image bacteria2;
    private Image bacteria3;
    private Image player = new ImageIcon(getClass().getResource("right.png")).getImage();
    private Rectangle r1,r2,r3,r4,r5,r6,r7;
    
    GameWindow(){
        setTitle("Healthy Day");
        setSize(700,700);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new TAdapter());
        addMouseListener(new MListener());
        r1 = new Rectangle(321,486,328,0);
        r2 = new Rectangle(47,486,225,0);
        
        
    }
    @Override
    public void paint(Graphics g){
        dbi = createImage(getWidth(),getHeight());
        db = dbi.getGraphics();
        paintComponent(db);
        g.drawImage(dbi, 0, 0, this);
        
    }
    public void paintComponent(Graphics g){
       g.drawImage(room, 0, 0, null);
       g.drawImage(player, x, y, null);
       g.setColor(Color.black);
       g.drawString(s,350,350);
       g.setColor(Color.red);
       g.drawRect(r1.x, r1.y, r1.width, r1.height);
       g.drawRect(r2.x, r2.y, r2.width, r2.height);
       repaint();
    }
    //set direction
    public void setXDirection(int xDir){
            xDirection = xDir ;
    }
    public void setYDirection(int yDir){
        yDirection = yDir;
    }
    // move the player
    public void move(){
        x += xDirection;
        y += yDirection;
    
    }

    // run thread
    @Override
    public void run() {
        while(true){
            try{
            move();
            Thread.sleep(5);
            }catch(Exception e){System.out.println(e.getMessage());}
        }
        
    }
    // keyboard listener
    public class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            if(e.getKeyCode()==e.VK_LEFT){
                player = new ImageIcon(getClass().getResource("char-5.gif")).getImage();
                 if(x<=0)
                    x = 0;
                    else 
                        setXDirection(-1);
            }
            if(e.getKeyCode()==e.VK_RIGHT){
                player = new ImageIcon(getClass().getResource("player.gif")).getImage();
                if(x>=630)
                    x = 630;
                else
                    setXDirection(1);
            }
            if(e.getKeyCode()==e.VK_UP){
                player = new ImageIcon(getClass().getResource("char-4.gif")).getImage();
                if(y<=30)
                    y = 30;
                    else 
                        setYDirection(-1);
            }
            if(e.getKeyCode()==e.VK_DOWN){
                 player = new ImageIcon(getClass().getResource("char-6.gif")).getImage();
                 if(y>=630)
                    y = 630;
                else
                    setYDirection(1);
            }
        }
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
    public class MListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e){
            s = "X: "+e.getX()+" Y: "+e.getY();
        }
    }
    
}
