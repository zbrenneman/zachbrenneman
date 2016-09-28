/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibraryweb.dao;

import com.mycompany.dvdlibraryweb.dto.Note;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class NoteDaoImpl implements NoteDao {

    private static final String TOKEN = "::";
    private static String FILENAME = "notes.txt";

    private List<Note> notes = null;
    private int nextId = 1;

    public NoteDaoImpl() {

        notes = decode();

        for (Note n : notes) {

            if (n.getNoteId() >= nextId) {

                nextId = n.getNoteId() + 1;
            }
        }
    }

    @Override
    public Note create(Note note) {

        note.setNoteId(nextId);

        notes.add(note);

        nextId++;

        encode();

        return note;

    }

    @Override
    public Note read(int id) {

        for (Note n : notes) {

            if (n.getNoteId() == id) {
                return n;
            }

        }

        return null;

    }

    @Override
    public void update(Note note) {

        ListIterator litr = notes.listIterator();

        while (litr.hasNext()) {

            Note n = (Note) litr.next();

            if (n.getNoteId() == note.getNoteId()) {

                litr.set(note);

            }
        }

        encode();

    }

    @Override
    public void delete(Note note) {

        ListIterator litr = notes.listIterator();

        while (litr.hasNext()) {

            Note n = (Note) litr.next();

            if (n.getNoteId() == note.getNoteId()) {

                litr.remove();

            }

        }

        encode();
    }

    @Override
    public List<Note> all() {

        return new ArrayList(notes);
    }

    private void encode() {

        PrintWriter out = null;

        try {
            out = new PrintWriter(new FileWriter(FILENAME));

            for (Note n : notes) {

                out.print(n.getNoteId());
                out.print(TOKEN);

                out.print(n.getDvdId());
                out.print(TOKEN);

                out.print(n.getUserNote());
                out.println("");

            }

            out.flush();

        } catch (IOException ex) {

        } finally {
            out.close();

        }

    }

    private List<Note> decode() {

        List<Note> tempNoteList = new ArrayList();

        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(FILENAME)));

            while (sc.hasNextLine()) {

                String currentLine = sc.nextLine();

                String[] stringParts = currentLine.split(TOKEN);

                Note myNote = new Note();

                int noteId = Integer.parseInt(stringParts[0]);
                int dvdId = Integer.parseInt(stringParts[1]);

                myNote.setNoteId(noteId);
                myNote.setDvdId(dvdId);
                myNote.setUserNote(stringParts[2]);

                tempNoteList.add(myNote);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(NoteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return tempNoteList;

    }

}
