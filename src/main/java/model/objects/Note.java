/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Nizar4790k
 */
@Entity
@Table(name= "Note")
public class Note {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="noteId")
    private int noteId;
    
    @Column(name= "content")
    private String content;

    /**
     * @return the noteId
     */
    public int getNoteId() {
        return noteId;
    }

    /**
     * @param noteId the noteId to set
     */
    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    public Note(String content) {
        this.content = content;
    }
            
    
}
