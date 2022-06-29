/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickgame;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class addd {
    Connection con = null;
    PreparedStatement pst = null;
    public void add(int sc, String name)
    {
   // ResultSet rs;
       try{
           String username = name;
           System.out.println(username+ " add ");
           String score=String.valueOf(sc);
       
        String query = "UPDATE `score` SET `scr`=? WHERE `uname`=?";
         con = DriverManager.getConnection("jdbc:mysql://localhost:3307/registration","root","");
         pst = con.prepareStatement(query);
         pst.setString(1, score);
         pst.setString(2, username);
        
         //rs = pst.executeQuery();
          pst.executeUpdate();
          //JOptionPane.showMessageDialog(null, "SCORE REGISTERED SUCCESSFULLY");
         
           JOptionPane.showMessageDialog(null, "SCORE REGISTERED SUCCESSFULLY");
          
         
           
    }catch(SQLException | HeadlessException ex)
        {
        JOptionPane.showMessageDialog(null, ex);
        }
    }
}
