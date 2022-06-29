/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;

import java.awt.Graphics;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 *
 * @author Admin
 */
public class getrank {
    
  
    public void paint(Graphics g) {
     int width=562;
     int height=477;
   Connection con = null;
    PreparedStatement pst = null;
     int rank;
         Statement statement = null;
    ResultSet resultSet = null;
    try {
        con = DriverManager.getConnection("jdbc:mysql://localhost:3307/registration","root","");
        statement = con.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM `score` order by scr desc ");
         
        //rank = 1;
        while(resultSet.next()) {
            String unamee = resultSet.getString("uname");
            g.drawString("Score: " + unamee, width-200,height-20);

            //rank++;
           
                }
    }catch(Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);
        

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    }}
