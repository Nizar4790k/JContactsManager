/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.objects.User;
import view.Dialog;
import view.LoginForm;
import view.MainForm;
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
                
                String email = form.getTxtEmail().getText();
                
                
                String password = new String(form.getTxtPassword().getPassword());
                User user = CRUD.getObject(email, User.class);
                
                if(user==null){
                       Dialog.errorDialog("It appears that the email or de password are wrong", "ERROR");
                       return;
                }
                
                if(user.getEmail().equals(email) && user.getPassword().equals(password)){
                    form.dispose();
                    MainForm form1 =   new MainForm();
                    form1.setVisible(true);
                   
                    form1.getLblName().setText(user.getFirstName());
                    form1.getLblLastName().setText(user.getLastName());
                    form1.getLblUser().setText(user.getEmail());
                    form1.setCurrentUser(user);
                          
                }else {
                    Dialog.errorDialog("It appears that the email or de password are wrong", "ERROR");
                    return;
                }
                
                
                
                
            }
           
        };
    
      
        return listener; 
    } 
      
      
   
   
}
