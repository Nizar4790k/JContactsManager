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
import model.Objects.User;
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
             
             boolean psEquals = password1.equals(password2);
           
             if(!psEquals)
             {
                 JOptionPane.showMessageDialog(null, "The password are not the same","ERROR",JOptionPane.ERROR_MESSAGE);
                 return;
             }
            
             User user = new User(email,password1,firstName,lastName);
               
             try {
                    CRUD.saveObject(user);
                     JOptionPane.showMessageDialog(null, "Used created Sucessfully","Success!",JOptionPane.INFORMATION_MESSAGE);
                     
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error when saving user","ERROR",JOptionPane.ERROR_MESSAGE);
                }
                 
             
             
                
            }
            
        };
   
        return listener;
    }
    
   
    
    
}
