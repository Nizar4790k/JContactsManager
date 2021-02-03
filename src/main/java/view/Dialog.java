/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Nizar4790k
 */
public class Dialog {
    
    public static void errorDialog(String message,String title){
        JOptionPane.showMessageDialog(null,message,title,JOptionPane.ERROR_MESSAGE);
        
    }
    
    public static void susscessDialog(String message,String title){
        
        try {
            JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE,new ImageIcon(new URL("https://i.imgur.com/hryiyjv.png")));
        } catch (MalformedURLException ex) {
          JOptionPane.showMessageDialog(null,message,title,JOptionPane.INFORMATION_MESSAGE);
        }

        
    }
    
    
}
