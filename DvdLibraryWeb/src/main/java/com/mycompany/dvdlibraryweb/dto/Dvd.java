/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dto;

import java.util.List;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author apprentice
 */
public class Dvd {

    @NotEmpty(message = "Please supply a title")
    @Length(max = 75, message = "Title cant be more than 75 characters")
    private String title;

    @NotEmpty(message = "Please supply a release date")
    @Length(max = 75, message = "Release date cant be more than 75 characters")
    private String release;

    private String rating;

    @NotEmpty(message = "Please supply a director name")
    @Length(max = 75, message = "Director name cant be more than 75 characters")
    private String director;

    @NotEmpty(message = "Please supply a studio name")
    @Length(max = 75, message = "Studio name cant be more than 75 characters")
    private String studio;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
