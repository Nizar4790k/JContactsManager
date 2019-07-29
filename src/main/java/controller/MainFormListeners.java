/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.objects.Company;
import model.objects.Contact;
import model.objects.Note;
import model.objects.Phone;
import model.objects.Photo;
import model.objects.Position;
import model.objects.User;
import view.Dialog;
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
                boolean deleteContact= form.getRadMenuDeleteContact().isSelected();
                if(addContact){
                   
                    
                  
                    String name = form.getTxtName1().getText();
                    String lastName = form.getTxtLastName1().getText();
                    String email = form.getTxtEmail().getText();
                    
                    
                    
                    if(email.equals("") || name.equals("") || lastName.equals("")){
                        Dialog.errorDialog("You must fill out the requied textFields", "ERROR");
                        return;
                    }
                    
                     if(!Validation.isValidEmail(email)){
                        Dialog.errorDialog("This email is not valid", "Error");
                        return;
                    }
                    
                   if( form.getTblPhone().getModel().getRowCount()==0){
                       Dialog.errorDialog("You must add al least one phone", "error");
                       return;
                   }
                   
                    String noteContent = form.getTxtAreaContact().getText();
                    String path = form.getTxtPhoto().getText();
                   
                    path= path.equals("default") ? path="src/main/resources/img/icons/iconfinder_user_man_678132.png":path;
                   
                    User user = form.getCurrentUser();
                    Company company = (Company)form.getCmbCompany1().getSelectedItem();
                    Position position = (Position)form.getCmbPosition().getSelectedItem();
                    Note note = null;
                    Photo photo= null;
                  
                   
                    
                    note= new Note(noteContent);
                    photo = new Photo(path);
                try {
                    CRUD.saveObject(photo);
                    CRUD.saveObject(note);
                
                 
                    
                    
                    Contact contact = new Contact(name,lastName,email,user,company,position,note,photo);
                    
                    
                        CRUD.saveObject(contact);
                        List<Phone> listPhones = getAddedPhones(form.getTblPhone().getModel(),contact);
                        
                        for(Phone phone : listPhones){
                        }
                        
                        Dialog.susscessDialog("Contact added succesfully", "SUCCESS!");
                        form.setDefaultFields();
                        
                        form.createListContact();
                    } catch (SQLException ex) {
                        Dialog.errorDialog("An error has ocurred when adding the contact","ERROR");
                    }
                    }else if(deleteContact){
                       
                        Contact contact = form.getListSelectionContact().getSelectedValue();
                        
                    try {
                        CRUD.<Contact>deleteObject(contact);
                        Dialog.susscessDialog("Contact removed succesfully", "Sucesss!");
                        form.setDefaultFields();
                        form.createListContact();
                    } catch (SQLException ex) {
                       Dialog.errorDialog("An error has ocurred when deleting a contact", "ERROR");
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
             
                new PhoneForm((DefaultTableModel) form.getTblPhone().getModel(),form).setVisible(true);
                form.setEnabled(false);
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
    
    
    
    public static ActionListener removePhone(JTable table){
        
        ActionListener listener  = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               
                int selectedRow = table.getSelectedRow();
                
                if(selectedRow==-1){
                    Dialog.errorDialog("You must select one al least one phone", "ERROR!");
                    return;
                }
                
                
      
             
               DefaultTableModel model = (DefaultTableModel)table.getModel() ;
              
               
               model.removeRow(selectedRow);
               table.setModel(model);
               
               
                
                
            }
            
        };
        
        return listener;
    }
    
    private static List<Phone>getAddedPhones(TableModel model,Contact contact){
       
        List<Phone> phones = new ArrayList<Phone>();
        
        for(int i=0;i<model.getRowCount();i++){
           String phone = (String) model.getValueAt(i, 0);
           String phoneType= (String)model.getValueAt(i, 1);
            
            phones.add(new Phone(phone,contact,phoneType));
        }
        
        return phones;
        
    }
    
    
    public static ListSelectionListener  selectContactListener(MainForm form){
        
        ListSelectionListener listener = new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
              
               JList<Contact> listSelectionContact = form.getListSelectionContact();
                
               Contact contact=  listSelectionContact.getSelectedValue();
               
               if(contact==null){
                   return;
               }
              
              form.getTxtName1().setText(contact.getFirstName());
              form.getTxtLastName1().setText(contact.getLastName());
              form.getTxtEmail().setText(contact.getEmail());
              
             DefaultComboBoxModel<Company> model1 =((DefaultComboBoxModel<Company>) form.getCmbCompany1().getModel());
             model1.setSelectedItem(contact.getCompany());
             
             DefaultComboBoxModel<Position> model2 =(DefaultComboBoxModel<Position>) form.getCmbPosition().getModel();
             model2.setSelectedItem(contact.getPosition());
             form.getTxtAreaContact().setText(contact.getNote().getContent());
             
            List<Phone> listPhone = CRUD.executeQuery("FROM Phone ph where ph.contact='"+contact.getContactId()+"'", Phone.class);
            DefaultTableModel model = (DefaultTableModel) form.getTblPhone().getModel();
            
            for(int i=0;i<model.getRowCount();i++){
                model.removeRow(i);
            }
            
            for(Phone ph : listPhone){
                String [] array = {ph.getPhone(),ph.getPhoneType()};
                model.addRow(array);
            }
           
            String path = contact.getPhoto().getPath();
            form.getLblPhoto().setIcon(new ImageIcon(path));
            form.getTxtPhoto().setText(path);
            
            
            }
          
        };
          return listener;
    }
    
    public static ActionListener radMenuDeleteContact(MainForm form){
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
           
                form.setEditableContactsField(false);
                form.getListSelectionContact().setEnabled(true);
            }
            
        };
        
        return listener;
    }
    
       public static ActionListener radMenuAddContact(MainForm form){
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
           
                form.setEditableContactsField(true);
                form.setDefaultFields();;
                form.getListSelectionContact().setEnabled(false);
            }
            
        };
        
        return listener;
    }
      public static ActionListener radMenuEditContact(MainForm form){
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
           
                form.setEditableContactsField(true);
                form.setDefaultFields();;
                form.getListSelectionContact().setEnabled(true);
            }
            
        };
        
        return listener;
    }
    
}
