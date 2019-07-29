/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author Nizar4790k
 */
public class Validation {
      public static boolean isValidEmail(String email) 
     {
   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
   return email.matches(regex);
    }
    
    
}
