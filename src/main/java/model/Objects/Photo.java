/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nizar4790k
 */
@Entity
@Table(name="Photo")
public class Photo {
   
   @Id
   @Column(name = "photoId")
   private int photoId;
   
    @Column(name = "path")
    private String path;

    /**
     * @return the photoId
     */
    public int getPhotoId() {
        return photoId;
    }

    /**
     * @param photoId the photoId to set
     */
    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    /**
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }




}
