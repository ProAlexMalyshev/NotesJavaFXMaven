package ru.malishev.notes.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.malishev.notes.objects.DateNote;
import ru.malishev.notes.objects.Note;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class DialogController {
    MainController mainController;

    @FXML
    Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    private Label labelDate;

    @FXML
    private TextArea textAreaNote;

    @FXML
    private void initialize(){
        final int maxLength = 100;
        textAreaNote.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {

                if (textAreaNote.getText().length() > maxLength) {
                    String s = textAreaNote.getText().substring(0, maxLength);
                    textAreaNote.setText(s);
                }
            }
        });

    }

    private Note note;


    public void setNote(Note note){
        if (note == null){
            return;
        }
        this.note = note;
        labelDate.setText(new DateNote().getDate());
        textAreaNote.setText(note.getTextNote());

        if(!isEmptyTextArea()){
            textAreaNote.setPromptText("Введите текст");
        }
    }

    public void close(ActionEvent actionEvent) {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        if(!isEmptyTextArea()){
            note = null;
        }
        stage.close();

    }

    public void save(ActionEvent actionEvent) {
        if(!isEmptyTextArea()){
            return;
        }
        note.setData(labelDate.getText());
        note.setTextNote(textAreaNote.getText());
        close(actionEvent);
    }
    public Note getNote(){return note;}

    private boolean isEmptyTextArea(){
        if(textAreaNote.getText().trim().length()==0){
            textAreaNote.setPromptText("Введите текст");
            System.out.println(textAreaNote.getText().trim().length());
            return false;
        }
        return true;
    }
}
