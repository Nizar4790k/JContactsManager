/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nizar4790k
 */
public class DBManipulation {
    
    public static void testConnection() throws SQLException{
      Connection connection = null;
       
      
      connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jcontactsmanager?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT","root","root12");
      System.out.println("Connection Sussesfull");
      
      }
    
    
}
