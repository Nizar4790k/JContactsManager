/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author Nizar4790k
 */
public class Dialog {
    
    public static void errorDialog(String message,String title){
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
        
        
    }
    
}
