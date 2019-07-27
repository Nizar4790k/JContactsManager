/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.objects;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Contact")
public class Contact implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contactId")
    private int contactId;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @ManyToOne
    @JoinColumn(name ="userEmail")
    private User user;
    
   @ManyToOne
   @JoinColumn(name="companyId")
   private Company company;
    
    
    /*
    @OneToMany
    @JoinColumn(name="phoneId")
    private Set<Phone> phones;
    */

   @ManyToOne
   @JoinColumn(name ="positionId")
   private Position position;
   
   @OneToOne
   @JoinColumn(name="noteId")
   private Note note;
   
      
   @OneToOne
   @JoinColumn(name = "photoId")
   private Photo photo;

    public Contact(String firstName, String lastName, String email, User user, Company company, Position position, Note note, Photo photo) {
       
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user = user;
        this.company = company;
        this.position = position;
        this.note = note;
        this.photo = photo;
    }
   
    public Contact(){
        
    }
   
   
   
   
}
