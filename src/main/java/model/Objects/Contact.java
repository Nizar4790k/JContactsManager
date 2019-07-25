/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Objects;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name="contactId")
    private int contactId;
    
    @Column(name="firstName")
    private String firstName;
    
    @Column(name="lastName")
    private String lastName;
    
    @Column(name="email")
    private String email;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Company company;
    
    @OneToMany
    @JoinColumn(name="phoneId")
    private Set<Phone> phones;
    
   @ManyToOne
   private Position position;
   
   @OneToOne
   @JoinColumn(name = "noteId")
   private Note note;
   
      
   @OneToOne
   @JoinColumn(name = "photoId")
   private Photo photo;
   
    
}
