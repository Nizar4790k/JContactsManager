/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Objects;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import javax.persistence.Table;


/**
 *
 * @author Nizar4790k
 */
@Entity
@Table(name="Position")
public class Position implements Serializable {
    
    @Id
    @Column(name="positionId")
     private int positionId;
    
    @Column(name="name")
    @Id
    private String name;
    
    @OneToMany
    @JoinColumn(name="positionId")
    private  Set<Contact> contacts;
    
  


    /**
     * @return the positionId
     */
    public int getPositionId() {
        return positionId;
    }

    /**
     * @param positionId the positionId to set
     */
    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    
}
