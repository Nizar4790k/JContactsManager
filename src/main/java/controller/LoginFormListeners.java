/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.SingUpForm;

/**
 *
 * @author Nizar4790k
 */
public class LoginFormListeners {
    
    public static ActionListener singUp (){
      
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               new SingUpForm().setVisible(true);
            }
           
        };
    
      
        return listener; 
    } 
    
      public static ActionListener singIn (){
      
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               new SingUpForm().setVisible(true);
            }
           
        };
    
      
        return listener; 
    } 
   
}
