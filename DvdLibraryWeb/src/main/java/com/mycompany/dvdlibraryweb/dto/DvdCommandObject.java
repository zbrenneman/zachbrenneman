/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dto;

import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class DvdCommandObject {

    int dvdId;
    
    @NotEmpty(message="Please supply a title")
    String title;
    @NotEmpty(message="Please supply a release date")
    String release;
    @NotEmpty(message="Please supply an MPAA rating")
    String rating;
    @NotEmpty(message="Please supply a director name")
    String director;
    @NotEmpty(message="Please supply a studio name")
    String studio;
    @NotEmpty(message="Please supply a note")
    String note;

    public int getDvdId() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId = dvdId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
