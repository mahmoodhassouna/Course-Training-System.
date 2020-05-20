/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class user {
    
    private final MessageDigest md;
      public user() throws SecurityException {
    try {
      md = MessageDigest.getInstance("MD5", "SUN");
    }catch(NoSuchAlgorithmException | NoSuchProviderException se) {
      throw new SecurityException("In MD5 constructor " + se);
    }
  }
    
     public void insertUser(String username,String password){
         
         Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         
            try 
               {
                   ps = con.prepareStatement("INSERT INTO user(username,password) VALUES (?,?)");
                   ps.setString(1, username);
                   ps.setString(2, password);                   
                    if(ps.executeUpdate() > 0){
                       JOptionPane.showMessageDialog(null, "New User Added");
                     }
                } 
            catch (SQLException ex) 
                {
                     Logger.getLogger(user.class.getName()).log(Level.SEVERE,null,ex);
                }
        
        
}
      public boolean isUserExist(String userName){
           boolean isExist= false;
             Connection con = MyConnection.getConnection();
            PreparedStatement ps;
         try{
           ps = con.prepareStatement("SELECT * FROM `user` WHERE `username` = ?");
           ps.setString(1,userName);
           ResultSet rs =ps.executeQuery();        
         
           if(rs.next())
                {
               isExist= true;
                }
            } catch (SQLException ex) {
              Logger.getLogger(user.class.getName()).log(Level.SEVERE, null, ex);
          }
           return isExist;
       
       }

    public static String getMD5Hash(String s) throws NoSuchAlgorithmException {

        String result = s;
        if (s != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); // or "SHA-1"
            md.update(s.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) { // 40 for SHA-1
                result = "0" + result;
            }
        }
        return result;
    }
  
   
   
    }
    
