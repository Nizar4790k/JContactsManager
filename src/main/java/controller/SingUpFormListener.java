/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.objects.User;
import org.hibernate.exception.ConstraintViolationException;
import view.Dialog;
import view.LoginForm;
import view.SingUpForm;

/**
 *
 * @author Nizar4790k
 */
public class SingUpFormListener {
    
    public static ActionListener register(SingUpForm form){
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
            
             String name = form.getTxtName().getText();
             String phone = form.getTxtPhone().getText();
             String lastName = form.getTxtLastName().getText();
             String firstName = form.getTxtName().getText();
             String email = form.getTxtEmail().getText();
             String password1= new String (form.getTxtPassword1().getPassword());
             String password2= new String (form.getTxtPassword2().getPassword());
             
             
             boolean areEmpty = name.trim().equals("") || phone.trim().equals("") ||	 lastName.trim().equals("") ||	 firstName.trim().equals("") ||	 email.trim().equals("" ) ||	 password1.trim().equals("") ||	password2.trim().equals("");
             
             if(areEmpty){
                 Dialog.errorDialog("The field(s) must not be empty", "Field(s) empty");
                 return;
             }
             
             if(!Validation.isValidEmail(email)){
                 Dialog.errorDialog("The email is not valid", "Invalid Email");
                 return;
             }
             if(CRUD.getObject(email, User.class)!=null){
                  Dialog.errorDialog("The email is registred", "Invalid Email");
                  return;
             }
             
            if(!isValidPassword(password1)){
                 Dialog.errorDialog("The password is not valid: '\n'"
                         + "Be between 8 and 40 characters long\n" +
                            "Contain at least one digit.\n" +
                            "Contain at least one lower case character.\n" +
                            "Contain at least one upper case character.\n" +
                            "Contain at least on special character from [ @ # $ % ! . _ ]."
                            , "Wrong Password");
                  return;
            }
             
             
             boolean psEquals = password1.equals(password2);
           
             if(!psEquals)
             {
                 Dialog.errorDialog("The password are not the same ","Wrong password");
                 return;
             }
            
             User user = new User(email,password1,firstName,lastName);
               
             try {
                    CRUD.saveObject(user);
                     JOptionPane.showMessageDialog(null, "Used created Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
                     form.dispose();
                     new LoginForm().setVisible(true);
                     
                     
                     
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error when saving user","ERROR",JOptionPane.ERROR_MESSAGE);
                    return;
                }catch(ConstraintViolationException ex2){
                    
                      JOptionPane.showMessageDialog(null, "This email exist","ERROR",JOptionPane.ERROR_MESSAGE);
                      return;
                }
                 
             
             
                
            }
            
        };
   
        return listener;
    }
    

       public static ActionListener goLogin(SingUpForm form){
         
           ActionListener listener = new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent ae) {
                   form.dispose();
                     new LoginForm().setVisible(true);
               }
               
           };
           
           return listener;
       }
    
    
    
    
    
      private static boolean isValidPassword(String password) {
   String regex = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!_]).{8,40})";
   return password.matches(regex);
    }
    
}
