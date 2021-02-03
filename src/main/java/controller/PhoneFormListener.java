/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;
import model.objects.Phone;
import view.Dialog;
import view.MainForm;
import view.PhoneForm;

/**
 *
 * @author Nizar4790k
 */
public class PhoneFormListener {
    
    public static ActionListener createPhone(PhoneForm form){
       
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                DefaultTableModel model = form.getModel();
                String phone = form.getTxtPhone().getText();
                
                for(int i=0;i<model.getRowCount();i++){
                    if(phone.equals((String)model.getValueAt(i,0))){
                        Dialog.errorDialog("This number is already added", "ERROR");
                        return;
                    }
                }
                
                String type = (String) form.getCmbType().getSelectedItem();

                String [] array = {phone,type};
                  model.addRow(array);
                 form.getMainForm().setEnabled(true);
                 
                form.dispose();
            }
        };
        
        
          return  listener;
    }
    
    public static ActionListener btnBack(PhoneForm form){
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
            MainForm form1 = form.getMainForm();
             form1.setEnabled(true);
             form.dispose();
              
            }
            
        };
        
        return listener;
    }
  
    public static WindowListener windowEvent(){
      
      WindowListener listener = new WindowListener(){
          @Override
          public void windowOpened(WindowEvent we) {
              
          }

          @Override
          public void windowClosing(WindowEvent we) {
          MainForm form = ((PhoneForm)(we.getSource())).getMainForm();
          form.setEnabled(true);

          }

          @Override
          public void windowClosed(WindowEvent we) {
           
          }

          @Override
          public void windowIconified(WindowEvent we) {
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public void windowDeiconified(WindowEvent we) {
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public void windowActivated(WindowEvent we) {
              //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }

          @Override
          public void windowDeactivated(WindowEvent we) {
             // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          }
          
      }  ;
        
        
        return listener;
    }
    
    
}
