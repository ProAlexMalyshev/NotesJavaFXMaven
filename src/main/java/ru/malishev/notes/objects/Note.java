package ru.malishev.notes.objects;

import javafx.beans.property.SimpleStringProperty;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class Note {
    private SimpleStringProperty data = new SimpleStringProperty(" ");
    private SimpleStringProperty textNote = new SimpleStringProperty(" ");
    private Integer Id;

    public Note(){
    }

    public Note( String data,  String textNote, int Id) {
        this.Id = Id;
        this.data = new SimpleStringProperty(data);
        this.textNote = new SimpleStringProperty(textNote);
    }

    public Integer getId (){return Id;}

    public String getData() {
        return data.get();
    }

    public void setData(String data) {
        this.data.set(data);
    }

    public String getTextNote() {
        return textNote.get();
    }

    public void setTextNote(String textNote) {
        this.textNote.set(textNote);
    }


    @Override
    public String toString() {
        return "Note{" +
                "data='" + data + '\'' +
                ", textNote='" + textNote + '\'' +
                ", Id='" + Id + '\'' +
                '}';
    }
}
