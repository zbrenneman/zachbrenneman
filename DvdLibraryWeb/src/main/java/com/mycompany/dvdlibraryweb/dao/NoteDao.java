/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Note;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface NoteDao {
    
    public Note create(Note note);
    public Note read(int id);
    public void update(Note note);
    public void delete(Note note);
    public List<Note> all();
    
}
