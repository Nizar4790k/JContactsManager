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
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
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
import org.hibernate.exception.ConstraintViolationException;
import view.AboutForm;
import view.CreateCompanyForm;
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
                   saveContact(form);
                   
                
                    }else if(deleteContact){
                       
                        Contact contact = form.getListSelectionContact().getSelectedValue();
                        
                    try {
                        if(form.getListSelectionContact().getSelectedIndex()==-1){
                             return;
                        }
                               
                        CRUD.<Contact>deleteObject(contact);
                        Dialog.susscessDialog("Contact removed succesfully", "Sucesss!");
                        form.setDefaultFields();
                        form.createListContact();
                    } catch (SQLException ex) {
                       Dialog.errorDialog("An error has ocurred when deleting a contact", "ERROR");
                    }
                        
                      
                    } else {
                      editContact(form);
                      
                      
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
            DefaultTableModel model = new DefaultTableModel();
           
            model.addColumn("Phone");
            model.addColumn("Type");
            
           
            
            for(Phone ph : listPhone){
                String [] array = {ph.getPhone(),ph.getPhoneType()};
                model.addRow(array);
            }
            
            form.getTblPhone().setModel(model);
           
            
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
                form.setDefaultFields();
                form.setDefaultFields();
                form.getListSelectionContact().setEnabled(false);
                form.getLblPhoto().setIcon(new ImageIcon("src/main/resources/img/icons/iconfinder_user_man_678132.png"));

                
            }
            
        };
        
        return listener;
    }
      public static ActionListener radMenuEditContact(MainForm form){
        
        ActionListener listener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
           
                form.setEditableContactsField(true);
               
               
                form.getListSelectionContact().setEnabled(true);
            }
            
        };
        
        return listener;
    }
    
      private static void saveContact(MainForm form){
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
                            CRUD.saveObject(phone);
                        }
                        
                        Dialog.susscessDialog("Contact added succesfully", "SUCCESS!");
                        form.setDefaultFields();
                        
                        form.createListContact();
                    } catch (SQLException ex) {
                        Dialog.errorDialog("An error has ocurred when adding the contact","ERROR");
                    }
      }
      
      
         private static void editContact(MainForm form){
                    
                    if(form.getListSelectionContact().getSelectedIndex()==-1){
                     return;   
                    }
             
             
                    Contact contact = form.getListSelectionContact().getSelectedValue();
                    String name = form.getTxtName1().getText();
                    String lastName = form.getTxtLastName1().getText();
                    String email = form.getTxtEmail().getText();
                    Note note = contact.getNote();
                    
                    
                    Photo photo= contact.getPhoto();
                    
                    
                    
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
                   
                    
                    Company company = (Company)form.getCmbCompany1().getSelectedItem();
                    Position position = (Position)form.getCmbPosition().getSelectedItem();
                    
                  
                    note.setContent(noteContent);
                    photo.setPath(path);
                    
                    
             
                try {
                    CRUD.updateObject(photo);
                    CRUD.updateObject(note);
                
                 
                    
                    
                    
                    contact.setFirstName(name);
                    contact.setLastName(lastName);
                    contact.setEmail(email);
                    contact.setPhoto(photo);
                    contact.setNote(note);
                    
                    
                    
                        CRUD.updateObject(contact);
                        List<Phone> listPhones = getAddedPhones(form.getTblPhone().getModel(),contact);
                      
                        for(Phone ph: listPhones){
                               CRUD.updateObject(contact);
                        }
                     
                        
                        
                        Dialog.susscessDialog("Contact updated succesfully", "SUCCESS!");
                        
                        
                        form.createListContact();
                    } catch (SQLException ex) {
                        Dialog.errorDialog("An error has ocurred when adding the contact","ERROR");
                    }
     
         
                
               
         
        
         
         
         
         
         
         }
      
       public static ActionListener enableSearchBy(MainForm form){
             ActionListener listener=  new ActionListener(){
                 @Override
                 public void actionPerformed(ActionEvent ae) {
                 
                  
                    JRadioButtonMenuItem checkBox = (JRadioButtonMenuItem) ae.getSource();
                   
                    JRadioButton radByName= form.getRadBtnByName();
                    JRadioButton radByLastName=form.getRadBtnLastName();
                    JRadioButton radByFullName = form.getRadBtnBtnByFullNAme();
                    JRadioButton radByCompany = form.getRadBtnByCompany();
                  
                    JButton buttonSearch = form.getBtnSearchBy();
                    
                    JTextField txtByName = form.getTxtByName();
                    JTextField txtByLastName = form.getTxtByLastName();
                    JTextField txtByName2= form.getTxtByName1();
                    JTextField txtByLastName2= form.getTxtByLastName1();
                    
                    
                    radByFullName.setEnabled(checkBox.isSelected());
                    radByName.setEnabled(checkBox.isSelected());
                    radByLastName.setEnabled(checkBox.isSelected());
                    radByCompany.setEnabled(checkBox.isSelected());
                    buttonSearch.setEnabled(checkBox.isSelected());
                   
                    
                    JComboBox comboBox = form.getCmbByCompany();
                    
                    
                    if(checkBox.isSelected()){
                        txtByLastName.setEnabled(false);
                        txtByLastName2.setEnabled(false);
                        txtByName.setEnabled(false);
                        comboBox.setEnabled(false);
                        txtByName.setEnabled(true);
                        comboBox.setEnabled(false);
                        radByName.setSelected(true);
                        
                    }
                    
                    clearCustomFields(form);
                    
                  
                    
                  
                 
             }
             
            
         };
         
          return listener;
       }           
      
       
       public static ActionListener searchByRadioListener(MainForm form){
         
           ActionListener listener = new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent ae) {
                    
                    form.getListSelectionContact().setEnabled(true);
                    JRadioButton radByName= form.getRadBtnByName();
                    JRadioButton radByLastName=form.getRadBtnLastName();
                    JRadioButton radByFullName = form.getRadBtnBtnByFullNAme();
                    JRadioButton radByCompany = form.getRadBtnByCompany();
                   
                    
                      JTextField txtByName = form.getTxtByName();
                    JTextField txtByLastName = form.getTxtByLastName();
                    JTextField txtByName2= form.getTxtByName1();
                    JTextField txtByLastName2= form.getTxtByLastName1();
                    
                         
                    JComboBox comboBox = form.getCmbByCompany();
                   
                   
                     if(radByName.isSelected()){
                        txtByName.setEnabled(true);
                        txtByLastName.setEnabled(false);
                        txtByName2.setEnabled(false);
                        txtByLastName2.setEnabled(false);
                        comboBox.setEnabled(false);
                        
                    }else if(radByLastName.isSelected()){
                        txtByName.setEnabled(false);
                        txtByLastName.setEnabled(true);
                        txtByName2.setEnabled(false);
                        txtByLastName2.setEnabled(false);
                        comboBox.setEnabled(false);
                        
                    } else if (radByFullName.isSelected()){
                         txtByName.setEnabled(false);
                        txtByLastName.setEnabled(false);
                        txtByName2.setEnabled(true);
                        txtByLastName2.setEnabled(true);
                        comboBox.setEnabled(false);
                    }else {
                         txtByName.setEnabled(false);
                        txtByLastName.setEnabled(false);
                        txtByName2.setEnabled(false);
                        txtByLastName2.setEnabled(false);
                        comboBox.setEnabled(true);
                    }
                   clearCustomFields(form);
                   
               }
               
           };
           
           return listener;
           
       }
       
       private static void clearCustomFields(MainForm form){
           JTextField txtByName = form.getTxtByName();
                    JTextField txtByLastName = form.getTxtByLastName();
                    JTextField txtByName2= form.getTxtByName1();
                    JTextField txtByLastName2= form.getTxtByLastName1();
                    
                    txtByName.setText("");
                    txtByLastName.setText("");
                    txtByName2.setText("");
                    txtByLastName2.setText("");
                    
                    
       }
       
       public static ActionListener customQueryListener(MainForm form){
           
                 ActionListener listener = new ActionListener(){
                     @Override
                     public void actionPerformed(ActionEvent ae) {
                       
                    JRadioButton radByName= form.getRadBtnByName();
                    JRadioButton radByLastName=form.getRadBtnLastName();
                    JRadioButton radByFullName = form.getRadBtnBtnByFullNAme();
                    JRadioButton radByCompany = form.getRadBtnByCompany();
                    
                     JTextField txtByName = form.getTxtByName();
                    JTextField txtByLastName = form.getTxtByLastName();
                    JTextField txtByName2= form.getTxtByName1();
                    JTextField txtByLastName2= form.getTxtByLastName1();
                    
                        
                    JComboBox comboBox = form.getCmbByCompany();
                      List<Contact> contacts;
                    
                      if(radByName.isSelected()){
                        contacts  =  CRUD.executeQuery("FROM Contact C where C.firstName='"+txtByName.getText()+"'", Contact.class);
                         
                      }else if(radByLastName.isSelected()){
                           contacts  =  CRUD.executeQuery("FROM Contact C where C.lastName='"+txtByLastName.getText()+"'", Contact.class);
                     
                      }else if(radByFullName.isSelected()){
                              contacts  =  CRUD.executeQuery("FROM Contact C where C.firstName='"+txtByName2.getText()+"'AND  C.lastName='"+txtByLastName2.getText()+"'", Contact.class);
                     
                      }else {
                          Company company = (Company)comboBox.getSelectedItem();
                          
                           contacts  =  CRUD.executeQuery("FROM Contact C where C.company='"+String.valueOf(company.getCompanyId())+"'", Contact.class);
                      }
        
                      
                      
                         
                      DefaultListModel<Contact> model = new DefaultListModel<Contact>();
                     
                     for(Contact c : contacts){
                         model.addElement(c);
                     }
                     
                     form.getListSelectionContact().setModel(model);   
                      
                      
                         
                         
                     }
                     
                 
                     
                 
           
                    
           
           
           
                   };
                         return listener;
           
       
                         }
       
       
       
       public ActionListener addCompany(MainForm form){
           
           ActionListener listener = new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent ae) { 
                  
                 new CreateCompanyForm().setVisible(true); 
              
                   
                   
               }
              
           };
         return listener;  
       }
       
       public static ActionListener AboutListener(){
        
          ActionListener listener = new ActionListener(){
              @Override
            
              public void actionPerformed(ActionEvent ae) {
                 new AboutForm().setVisible(true); 
              }
             
           };
           
           return listener;
           
       }
       
}
