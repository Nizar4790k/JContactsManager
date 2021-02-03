/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import model.objects.Contact;

/**
 *
 * @author Nizar4790k
 */
public class ContactRenderer extends JPanel implements ListCellRenderer<Contact>
{
    private JLabel lblImage = new JLabel();
    private JLabel lblFullName = new JLabel();
    
    public void ContactRenderer(){
    
        //  JPanel panelText = new JPanel(new GridLayout(0,1)); 
        //panelText.add(lblFullName);
     
     
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Contact> jlist, Contact e, int position, boolean isSelected, boolean cellHasFocus) {
        Contact contact = e;
        setLayout(new BorderLayout());
        lblImage.setIcon(new ImageIcon(e.getPhoto().getPath()));
        lblFullName.setText( e.getFirstName()+" "+e.getLastName());
         add(lblImage,BorderLayout.WEST);
         add(lblFullName,BorderLayout.CENTER);
         
         if(isSelected && jlist.isEnabled()){
             setBackground(jlist.getSelectionBackground());
             setForeground(jlist.getSelectionForeground());
         } else{
             setBackground(jlist.getBackground());
             setForeground(jlist.getForeground());
         }
         
         
        return this;
       
    }
    
}
