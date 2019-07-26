/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.LoginForm;
import view.SingUpForm;

/**
 *
 * @author Nizar4790k
 */
public class LoginFormListeners {
    
    public static ActionListener singUp (LoginForm form){
      
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                form.dispose();
                new SingUpForm().setVisible(true);
            }
           
        };
    
      
        return listener; 
    } 
    
      public static ActionListener singIn (LoginForm form){
      
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                form.dispose();
                new SingUpForm().setVisible(true);
            }
           
        };
    
      
        return listener; 
    } 
   
}
