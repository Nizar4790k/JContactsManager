/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.objects;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import model.objects.Contact;

/**
 *
 * @author Nizar4790k
 */
@Entity
@Table(name="Phone")
public class Phone implements Serializable {
  
    @Id
    @Column(name="phone")
    private String phone;
    
    @ManyToOne
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Contact contact;
    
    @Column(name="phoneType")
    private String phoneType;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
