/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import model.objects.Company;
import model.objects.Contact;
import model.objects.Note;
import model.objects.Photo;
import model.objects.Position;
import model.objects.User;
import view.MainForm;
import view.PhoneForm;

/**
 *
 * @author Nizar4790k
 */
public class MainFormListeners {
    
    
    public  static ActionListener btnApplyListener(MainForm form) {
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                boolean addContact = form.getRadMenuAddContact().isSelected();
                if(addContact){
                   
                    
                  
                    String name = form.getTxtName1().getText();
                    String lastName = form.getTxtLastName1().getText();
                    String email = form.getTxtEmail().getText();
                    String companyName = (String)form.getCmbCompany1().getSelectedItem();
                    String positionName = (String)form.getCmbPosition().getSelectedItem();
                    String noteContent = form.getTxtAreaContact().getText();
                    String path = form.getTxtPhoto().getText();
                   
                    User user = form.getCurrentUser();
                    Company company = null;
                    Position position = null;
                    Note note = null;
                    Photo photo= null;
                  
                    List<Company> listCompany = CRUD.executeQuery("FROM Company C WHERE C.name='"+companyName+"'", Company.class);
                    
                    for(Company c: listCompany){
                        if(c.getName().equals(companyName)){
                            company=c;
                        }
                    }
                   
                    List<Position> listPosition = CRUD.executeQuery("FROM Position P WHERE P.name='"+positionName+"'", Position.class);
                    
                    for(Position p: listPosition){
                        if(p.getName().equals(positionName)){
                            position=p;
                        }
                    }
                    
                    note= new Note(noteContent);
                    photo = new Photo(path);
                try {
                    CRUD.saveObject(photo);
                    CRUD.saveObject(note);
                } catch (SQLException ex) {
                    Logger.getLogger(MainFormListeners.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                    
                    
                    Contact contact = new Contact(name,lastName,email,user,company,position,note,photo);
                    
                    try {
                        CRUD.saveObject(contact);
                    } catch (SQLException ex) {
                        Logger.getLogger(MainFormListeners.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                    
                
              
                
            }
            
        };
        return listener;
    }
    
    public static ActionListener btnAddPhoneListener(MainForm form){
        
        ActionListener listener = new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent ae) {
             
                new PhoneForm((DefaultTableModel) form.getTblPhone().getModel()).setVisible(true);
                       
            }
            
        };
        
                return listener;
    }
    
    
    public static ActionListener btnAddPhoto(MainForm form){
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               JFileChooser fileChooser = new JFileChooser();
               int returnValue = fileChooser.showOpenDialog(form);
               
               if(returnValue==JFileChooser.APPROVE_OPTION){
                   
                   String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
                   form.getTxtPhoto().setText(absolutePath);
                   
                   form.getLblPhoto().setIcon(new ImageIcon(absolutePath));
               }
            }
            
        };
        
        return listener;
    }
    
    
    
    
    
    
    
    
}
