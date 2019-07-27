/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
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
                String type = (String) form.getCmbType().getSelectedItem();
                 String [] array = {phone,type};
                  model.addRow(array);
                  
                  
                form.dispose();
            }
        };
        
        
          return  listener;
    }
  
}
