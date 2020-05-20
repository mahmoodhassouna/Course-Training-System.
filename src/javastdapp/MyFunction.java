/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javastdapp;

import com.mysql.jdbc.PreparedStatement;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class MyFunction {
    public static int countData(String tableName)
    {
      int total =0;
       Connection con = MyConnection.getConnection();
       java.sql.Statement st;
       try{
           st = con.createStatement();
           ResultSet rs = st.executeQuery("SELECT COUNT(*) AS `total` FROM "+tableName);
             while(rs.next()){
                total = rs.getInt(1);
             }
          }catch(SQLException ex){
             Logger.getLogger(MyFunction.class.getName()).log(Level.SEVERE,null,ex);
          
          }
     return total;
    }
    

}
