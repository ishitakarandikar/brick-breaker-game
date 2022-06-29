/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import static java.awt.Font.BOLD;
import static java.awt.SystemColor.window;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javax.imageio.ImageIO;
/**
 *
 * @author Admin
 */
public class graphic extends JPanel implements KeyListener, ActionListener {
    
    
     public int score;
    private Timer timer;
    boolean play = false;
    boolean game_over = false;
    
    // dimensions of frame
    
    private int height = 640;
    private int width = 800;

    //position of slide
    
    private int pos_slid_X = 400;
    final private int pos_slid_Y = 590;


    //position of ball
    
    private int pos_ball_X = 200;
    private int pos_ball_Y = 250;

    //direction of ball
    
    private int ball_dirX = -2;
    private int ball_dirY = -3;

    //brick dimensions and stuff
    
    private int brick_width = 55;
    private int brick_height= 35;
    private int bricks [][];
    private int columns = 10;
    private int rows = 4;
    private int nofbricks = columns *rows;
    public String unamee;
    public int oldscore;
     Image img;
//public int newscore;
public String usrname;
    public graphic(int s, String uname){ 
         // constructor
         oldscore=s;
    score=s;   
 
 // ImageIcon obj=new ImageIcon   ("src/brickgame/img/wall.jpg");
   // img=obj.getImage();
    bricks = new int[columns][rows];
        for (int i =0;i<columns;i++){
            Arrays.fill(bricks[i],1);
        }
        addKeyListener(this);                                               // information that method responsible for
        setFocusable(true);                                                 // Keytyping is in this class
        timer = new Timer(15, this);
         unamee = uname;
         System.out.println(unamee+ " graphic ");
    }

    public void paint(Graphics g){
                                                            
super.paintComponent(g);
         //   g.drawImage(myPicture, 1,1, null);
       // g.drawImage(img, width, height, this);
      //  setVisible(true);

//BufferedImage background = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
         moveball();  
       g.setColor(new Color(0, 0, 0));
       g.fillRect(0,0, width,height);                  // rectangle in which is whole game
   
    //Image img = Toolkit.getDefaultToolkit().getImage("src/brickgame/img/wall.jpg");
   
     
            
        
        drawbricks(g);                                                      // function that draws bricks

        g.setColor(Color.pink);
        g.fillOval(pos_ball_X,pos_ball_Y,30,30);                    // ball

        g.setColor(new Color(255, 255, 255));
        g.fillRect(pos_slid_X,pos_slid_Y,150,10);                    // slider
        score(g);                                                          // method that show score
        lost(game_over,g);                                                 // checks if ball is out of rectangle
        if (nofbricks ==0) { 
           
            win(g);
        }

        Toolkit.getDefaultToolkit().sync();                                // without this method it is lagging
        g.dispose();
        
    }

    private void score(Graphics g) {
        /*Updates score on screen*/
        g.setColor(Color.white);
        g.setFont(new Font(Font.SERIF, BOLD,20));
        g.drawString("Score: " + score, width-200,height-20);
    }

    private void win(Graphics g) {
        /*If there are no bricks this method is called and u can start again*/
        timer.stop();
        play = false;
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
        g.drawString("You Won", width/2,height/2);
        //winn w = new winn();
       // add(score);
        System.out.println(score + "win");
        addd a= new addd();                       // if there are no bricks u won
           a.add(score ,unamee);
          g.dispose();
       level2 l= new level2(score,unamee);
       l.play(unamee);
       
     //   dispose();
       // for (int i =0;i<columns;i++){
         //   Arrays.fill(bricks[i],1);
        //}
        //nofbricks = rows * columns;
        //pos_ball_X = 100;
        //pos_ball_Y = 200;
        //ball_dirX = -3;
        //ball_dirY = -3;
    }

    private void drawbricks(Graphics g) {
        /*Draws rectangle for each row and column*/
       
       
        for (int i =0; i< columns;i++){
              Random rand = new Random();
                  float r = rand.nextFloat();
                  float gg = rand.nextFloat();
                  float b = rand.nextFloat();
                Color randomcolor = new Color(r, gg, b);
            for (int j =0; j< rows;j++){
               
                if (bricks[i][j]>0) {
                   //  final float hue = rand.nextFloat();
// Saturation between 0.1 and 0.3
       // final float saturation = (rand.nextInt(2000) + 1000) / 10000f;
   //final float luminance = 0.9f;
    // final Color color = Color.getHSBColor(hue, saturation, luminance);
                    
                    g.setColor(randomcolor);
                    g.fillRect(100 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
                    g.setColor(Color.white);
                    g.drawRect(100 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
                }
            }
        }
    }

    private void lost(boolean game_over, Graphics g) {
        /*If u lost than u lost :)*/
        if (game_over)
        {   
            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.MONOSPACED,BOLD,18));
            g.drawString("GAME OVER", width/2,height/2);
            addd a1=new addd();
        a1.add(score,unamee);
            score = oldscore;
        }
        else this.game_over = false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    public void keyPressed(KeyEvent keyEvent) {
        /*If game loads u have to press any button to start*/

        if (!play) timer.start();

        game_over = false;

        /*Here starts code responsible for movement of slider */

        if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            if (pos_slid_X>=this.width-50) {
                pos_slid_X = this.width-50;
            }
            else {
            pos_slid_X += 20;
            }
        }

        if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
        {
            if (pos_slid_X < 5) {
                pos_slid_X = 5;
            }
            else {
                pos_slid_X -= 20;
            }
        }

        /*If u press P u Pause the game*/

        if (keyEvent.getKeyCode() == KeyEvent.VK_P){
            if (play) {
                play = false;
                timer.stop();
            }
            else if (!play){
                play=true;
                timer.start();
            }
        }
        if (keyEvent.getKeyCode() == KeyEvent.VK_Q){
            if (play) {
                play = false;
                timer.stop();
                int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Do you wanna quit?", 
                                  "Choose", 
                                  JOptionPane.YES_NO_OPTION); 
if (selectedOption == JOptionPane.YES_OPTION) {
   
    mainmenu m=new mainmenu();
    m.t();
    Graphics g = this.getGraphics();
    
    System.out.println(g.getClass());
   //                
               g.dispose();
}
           if (selectedOption == JOptionPane.NO_OPTION) {
   
                play=true;
                timer.start();
            }
        }
    }
    }
    @Override
    public void keyReleased(KeyEvent keyEvent) {}

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    /*If u press any button game resumes*/
        play = true;
        if (play) {
            repaint();
        }
    }

    private void moveball(){
    /* This method is responsible for changig direction of ball. It checks all possible collisions and respectively 
    * changes the direction*/

        Rectangle ball_rect = new Rectangle(pos_ball_X,pos_ball_Y,30,30);
        Rectangle slider = new Rectangle(pos_slid_X,pos_slid_Y,150,10);

        if (pos_ball_X <6) ball_dirX = -ball_dirX;


        if (pos_ball_X > width-22) ball_dirX = -ball_dirX;

        if (pos_ball_Y > height-22) {
            play = false;
            game_over = true;
            pos_ball_X = 100;
            pos_ball_Y = 250;
            for (int i =0;i<columns;i++){
                Arrays.fill(bricks[i],1);
            }
            timer.stop();
        }


        if (pos_ball_Y < 6) ball_dirY = -ball_dirY;

        if  (ball_rect.intersects(slider)) ball_dirY = -ball_dirY;


        A:for (int i =0; i< columns;i++){
            for (int j =0; j< rows;j++){
                if (bricks[i][j]>0) {
                    int brick_X_pos = 100 + i * brick_width + i * 5;
                    int brick_Y_pos = 80 + j * brick_height + j * 2;
                    Rectangle brick_rect = new Rectangle(brick_X_pos, brick_Y_pos, brick_width, brick_height);
                    if (ball_rect.intersects(brick_rect)) {
                        bricks[i][j] = 0;
                        if (pos_ball_X+19 <= brick_X_pos || pos_ball_X+1>=brick_X_pos + brick_width ){

                            ball_dirX = -ball_dirX;
                        }
                        else {
                            ball_dirY = -ball_dirY;
                        }
                        score +=2;
                        nofbricks--;
                        break A;
                    }

                }
            }
        }


        this.pos_ball_X += ball_dirX;
        this.pos_ball_Y += ball_dirY;
    }
    public void f(int score, String uname)
    {
     int s=score;
        String u=uname;
        System.out.println(u+ " brickgame ");
       JFrame frame = new JFrame();
     //String imagePath = ;
       

   graphic g = new graphic(s, u);
         frame.setResizable(false);
       frame.setBounds(100,20,800,660);
       frame.setTitle("Brick Game");
       frame.add(g);
       frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
      
       frame.setVisible(true);
    
    }
  public void windowClosing(WindowEvent e) {

        // TODO Auto-generated method stub
         Window window = e.getWindow();
    }
}

