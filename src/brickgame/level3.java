/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;
import static java.awt.Color.white;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

import static java.awt.Font.BOLD;
import java.util.Random;
/**
 *
 * @author Admin
 */
public class level3 extends JPanel implements KeyListener, ActionListener{
     public int score;
    private Timer timer;
    boolean play = false;
    boolean game_over = false;
    
    int brickx=70;
    int bricky=50;
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
    private int bricks1 [][];
    private int columns = 10;
    private int rows = 4;
    private int nofbricks = 42;
    
    Rectangle brick[] =new Rectangle[200];
    Rectangle brick1[] =new Rectangle[200];
    
    public int oldscore;
    public String usrname;
public level3(int score1, String unamee){                                                        // constructor
    oldscore=score1;
    score=score1;  
    Rectangle brick[] =new Rectangle[200];   
    bricks = new int[2*columns][2*rows];
    Rectangle brick1[] =new Rectangle[200];   
    bricks1 = new int[2*columns][2*rows];
        for (int i =0;i<columns;i++){
            Arrays.fill(bricks[i],1);
            Arrays.fill(bricks1[i],1);
        }
        addKeyListener(this);                                               // information that method responsible for
        setFocusable(true);                                                 // Keytyping is in this class
        timer = new Timer(15, this);
        usrname=unamee;
        System.out.println(usrname+ " level3 ");
}
public void paint(Graphics g){
        moveball();                                                         // method responsible for movement of ball

        g.setColor(new Color(0, 0, 0));
        g.fillRect(0,0, width,height);                  // rectangle in which is whole game

        drawbricks(g);                                                      // function that draws bricks

        g.setColor(Color.pink);
        g.fillOval(pos_ball_X,pos_ball_Y,30,30);                    // ball

        g.setColor(new Color(255, 255, 255));
        g.fillRect(pos_slid_X,pos_slid_Y,150,10);                    // slider
        score(g);                                                          // method that show score
        lost(game_over,g);                                                 // checks if ball is out of rectangle
        if (nofbricks ==0) {                                               // if there are no bricks u won
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
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
        g.drawString("You Won", width/2,height/2);
      
           addd a= new addd();                                           // if there are no bricks u won
           a.add(score ,usrname);
      graphic bg= new graphic(score,usrname);
            bg.f(score, usrname);


//  for (int i =0;i<columns;i++){
        //    Arrays.fill(bricks[i],1);
          //  Arrays.fill(bricks[i],1);
        //}
       // nofbricks = rows * columns;
       // pos_ball_X = 100;
       // pos_ball_Y = 200;
      //  ball_dirX = -3;
        //ball_dirY = -3;
    }

private void drawbricks(Graphics g) {
          /*Draws rectangle for each row and column*/
       for (int i =0; i< columns-1;i++){
              Random rand = new Random();
                float r = rand.nextFloat();
              float gg = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomcolor = new Color(r, gg, b);
            for (int j =0; j< i-2;j++){
               
                if (bricks[i][j]>0) {
                                      
                    g.setColor(randomcolor);
                    g.fillRect(220 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
                    g.setColor(Color.white);
                    g.drawRect(220 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
              }
            }
        
           for (int j =0; j<columns-i-4;j++){
       // for(int j=rows-i; j>1; j--){
            //for (int j =0; j<=rows-i+1;j++){
                if (bricks1[i][j]>0) {
                 
                    
                    g.setColor(randomcolor);
                    g.fillRect(40 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
                    g.setColor(Color.white);
                    g.drawRect(40 + i * brick_width + i * 5, 80 + j * brick_height + j * 2, brick_width, brick_height);
              }
            }
        }
    }

private void lost(boolean game_over, Graphics g) {
        /*If u lost than u lost :)*/
        if (game_over)
        {
              System.out.println(score);
             addd a1=new addd();
            a1.add(score,usrname);
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED,BOLD,18));
            g.drawString("GAME OVER", width/2,height/2);
            score = oldscore;
        }
        else this.game_over = false;
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
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


        A:for (int i =0; i<columns-1;i++){
            B:  for (int j =0; j<i-2;j++){
                if (bricks[i][j]>0) {
                    int brick_X_pos = 220 + i * brick_width + i * 5;
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
             C:  for (int j =0; j< columns-i-4;j++){
                if (bricks1[i][j]>0) {
                    int brick_X_pos = 40 + i * brick_width + i * 5;
                    int brick_Y_pos = 80 + j * brick_height + j * 2;
                    Rectangle brick_rect = new Rectangle(brick_X_pos, brick_Y_pos, brick_width, brick_height);
                    if (ball_rect.intersects(brick_rect)) {
                        bricks1[i][j] = 0;
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
    public void ll(String unamee) {
        // TODO code application logic here
       JFrame frame = new JFrame();
       
       
       level3 g = new level3(score,unamee);
       frame.setResizable(false);
       frame.setBounds(100,20,800,660);
       frame.setTitle("Brick Game");
       frame.add(g);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
        
    }

    private void Rectangle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
