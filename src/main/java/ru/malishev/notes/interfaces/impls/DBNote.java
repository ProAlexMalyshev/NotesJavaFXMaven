package ru.malishev.notes.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ru.malishev.notes.db.SQLiteConnection;
import ru.malishev.notes.interfaces.AddNote;
import ru.malishev.notes.objects.DateNote;
import ru.malishev.notes.objects.Note;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class DBNote implements AddNote {
    Statement statement = null;
    ResultSet resultSet = null;
    Connection connection = SQLiteConnection.getConnection();

    public DBNote(){
        select();
    }

    void select(){
        try {
            String sql = "SELECT * FROM note";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                noteList.add(new Note(new DateNote().getDateMilliseconds((Long) resultSet.getObject("data")),
                        resultSet.getString("note"),
                        resultSet.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(resultSet != null){resultSet.close();}
            }catch (Exception ex){

            }
        }
    }

    private ObservableList<Note> noteList = FXCollections.observableArrayList();

    @Override
    public void create(Note note) {
        if(note==null){
            return;
        }
        noteList.add(note);
        String dateStr = note.getData();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E. dd:MM:yyyy, kk:mm");
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String notes = note.getTextNote();
        String dates = String.valueOf(date.getTime());

        try {
            statement = connection.createStatement();
            String query = "INSERT INTO note (note, data) " +
                    "VALUES ('" + notes + "', '" + dates + "')";
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Note note) {
        String noteText = note.getTextNote();
        try{
            String idn = String.valueOf(note.getId());
            String query = "UPDATE note SET note =" +"'"+noteText+"'"+"WHERE id =" + idn;
            statement.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Note note) {
        noteList.remove(note);
        try{
            String idn = String.valueOf(note.getId());
            String query = "DELETE FROM note WHERE id =" + idn;
            statement.executeUpdate(query);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Note> getNoteList() {
        return noteList;
    }

    public void closeDb(){

        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
