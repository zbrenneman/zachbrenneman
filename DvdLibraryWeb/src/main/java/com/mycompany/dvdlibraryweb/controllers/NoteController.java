/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.controllers;

import com.mycompany.dvdlibraryweb.dao.DvdDao;
import com.mycompany.dvdlibraryweb.dao.NoteDao;
import com.mycompany.dvdlibraryweb.dto.Dvd;
import com.mycompany.dvdlibraryweb.dto.DvdCommandObject;
import com.mycompany.dvdlibraryweb.dto.DvdViewModel;
import com.mycompany.dvdlibraryweb.dto.Note;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apprentice
 */
@Controller
@RequestMapping(value = "/note")
public class NoteController {

    private NoteDao noteDao;
    private DvdDao dvdDao;

    @Inject
    public NoteController(NoteDao noteDao, DvdDao dvdDao) {
        this.noteDao = noteDao;
        this.dvdDao = dvdDao;
    }

    @RequestMapping(value= "", method = RequestMethod.POST)
    @ResponseBody
    public Note add(@RequestBody Note note){
        
         Note newNote = noteDao.create(note);
        
         return newNote;
    }
   

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DvdViewModel editNote(@PathVariable("id") Integer noteId) {

        Note note = noteDao.read(noteId);
        Dvd dvd = dvdDao.read(note.getDvdId());
        List<Note> notes = noteDao.all();

        List<Note> filterNotes = notes
                .stream()
                .filter(n -> n.getDvdId() == dvd.getId())
                .collect(
                        Collectors.toList());

        DvdViewModel dvm = new DvdViewModel();

        dvm.setNote(note);
        dvm.setDvd(dvd);
        dvm.setNotes(filterNotes);

        return dvm;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Note submitEditNote(@RequestBody Note note) {

        noteDao.update(note);

        return note;

    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void submitDeleteNote(@RequestBody Note note){
        
        noteDao.delete(note);
    }

}
