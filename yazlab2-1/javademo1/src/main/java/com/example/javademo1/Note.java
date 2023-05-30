package com.example.javademo1;

public class Note {

    public String noteDetail;

    // Method
    public String getNoteDetail() { return noteDetail; }

    // Method
    public void setNoteDetail(String noteDetail)
    {
        this.noteDetail = noteDetail;
    }
    @Override
    public String toString() {
        return "Note [noteDetail=" + noteDetail + " ]";
    }
}
