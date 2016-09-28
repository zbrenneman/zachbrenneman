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
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
@RequestMapping(value = "/dvd")
public class DvdController {

    private DvdDao dvdDao;
    private NoteDao noteDao;

    @Inject
    public DvdController(DvdDao dvdDao, NoteDao noteDao) {
        this.dvdDao = dvdDao;
        this.noteDao = noteDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DvdViewModel show(@PathVariable("id") Integer dvdId) {

        List<Note> notes = noteDao.all();

        List<Note> filterNotes = notes
                .stream()
                .filter(n -> n.getDvdId() == dvdId)
                .collect(
                        Collectors.toList());

        Dvd dvd = dvdDao.read(dvdId);

        DvdViewModel dvm = new DvdViewModel();

        dvm.setDvd(dvd);
        dvm.setNotes(filterNotes);

        return dvm;
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DvdViewModel edit(@PathVariable("id") Integer dvdId) {

        List<Note> notes = noteDao.all();

        List<Note> filterNotes = notes
                .stream()
                .filter(n -> n.getDvdId() == dvdId)
                .collect(
                        Collectors.toList());

        Dvd dvd = dvdDao.read(dvdId);

        DvdViewModel dvm = new DvdViewModel();

        dvm.setDvd(dvd);
        dvm.setNotes(filterNotes);

        return dvm;
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public Dvd add(@Valid@RequestBody DvdCommandObject dco) {

        Dvd dvd = new Dvd();

        dvd.setDirector(dco.getDirector());
        dvd.setRating(dco.getRating());
        dvd.setRelease(dco.getRelease());
        dvd.setStudio(dco.getStudio());
        dvd.setTitle(dco.getTitle());

        Dvd newDvd = dvdDao.create(dvd);

        Note note = new Note();

        note.setDvdId(newDvd.getId());
        note.setUserNote(dco.getNote());

        noteDao.create(note);

        return newDvd;
    }
    
 

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteSubmit(@RequestBody Dvd dvd) {

        dvdDao.delete(dvd);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Dvd editSubmit(@Valid@RequestBody Dvd dvd) {

        dvdDao.update(dvd);

        return dvd;

    }

}
