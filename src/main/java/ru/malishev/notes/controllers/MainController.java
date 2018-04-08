package ru.malishev.notes.controllers;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.malishev.notes.interfaces.impls.DBNote;
import ru.malishev.notes.objects.Note;
import java.io.IOException;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */

public class MainController {
    private DBNote dbNote = new DBNote();
    private Stage mainStage;

    private Parent fxmlEditRoot;
    FXMLLoader fxmlLoader = new FXMLLoader();
    private DialogController dialogController;
    private Stage dialogControllerStage;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    @FXML
    private Label labelCreatNewNote;

    @FXML
    private TableView tableNotes;

    @FXML
    private TableColumn <Note, String> columnNotes;

    @FXML
    private TableColumn <Note, String> columnNotesData;

    @FXML
    private void initialize(){
        tableNotes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnNotesData.setCellValueFactory(new PropertyValueFactory<Note, String>("data"));
        columnNotes.setCellValueFactory(new PropertyValueFactory<Note, String>("textNote"));
        tableNotes.setItems(dbNote.getNoteList());

        clickTable();

        try {
            fxmlLoader.setLocation(getClass().getResource("/fxml/dialogWindowNote.fxml"));
            fxmlEditRoot = fxmlLoader.load();
            dialogController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbNote.getNoteList().addListener(new ListChangeListener<Note>() {
            @Override
            public void onChanged(Change<? extends Note> c) {
                tableNotes.getSelectionModel().clearSelection();
                btnDelete.setDisable(true);
                btnEdit.setDisable(true);
            }
        });
    }

    public void setMainStage (Stage mainStage){this.mainStage = mainStage;}

    private void clickTable(){
        tableNotes.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Note selectedNote = (Note) tableNotes.getSelectionModel().getSelectedItem();
                if(selectedNote!=null) {
                    btnDelete.setDisable(false);
                    if(tableNotes.getSelectionModel().getSelectedIndices().size()== 1){
                        btnEdit.setDisable(false);
                    }else {
                        btnEdit.setDisable(true);
                    }
                }

                if(event.getClickCount() == 2){
                    dialogController.setNote((Note) tableNotes.getSelectionModel().getSelectedItem());
                    dialogController.btnSave.setDisable(false);
                    showDialogWindowNote();
                    dbNote.update(dialogController.getNote());
                    tableNotes.getSelectionModel().clearSelection();
                    btnDelete.setDisable(true);
                    btnEdit.setDisable(true);
                }
            }
        });
    }

    public void clickButton(ActionEvent actionEvent) {

        Object sourse = actionEvent.getSource();

        if(!(sourse instanceof Button)){
            return;
        }
        Button clickButton = (Button) sourse;

        switch (clickButton.getId()){
            case "btnCreate":
                tableNotes.getSelectionModel().clearSelection();
                dialogController.setNote(new Note());
                dialogController.btnSave.setDisable(false);
                showDialogWindowNote();
                dbNote.create(dialogController.getNote());
                break;

            case "btnDelete":
                dbNote.delete((Note) tableNotes.getSelectionModel().getSelectedItem());
                btnDelete.setDisable(true);
                btnEdit.setDisable(true);
                break;

            case "btnEdit":
                dialogController.setNote((Note) tableNotes.getSelectionModel().getSelectedItem());
                dialogController.btnSave.setDisable(false);
                showDialogWindowNote();
                dbNote.update(dialogController.getNote());
                tableNotes.getSelectionModel().clearSelection();
                btnDelete.setDisable(true);
                btnEdit.setDisable(true);
                break;
        }

    }

    public void showDialogWindowNote(){

        if(dialogControllerStage == null) {
            dialogControllerStage = new Stage();
            dialogControllerStage.setTitle("Моя заметка");
            dialogControllerStage.setMinWidth(300);
            dialogControllerStage.setMinHeight(200);
            dialogControllerStage.setWidth(300);
            dialogControllerStage.setHeight(200);
            dialogControllerStage.setResizable(false);
            dialogControllerStage.setScene(new Scene(fxmlEditRoot));
            dialogControllerStage.initModality(Modality.WINDOW_MODAL);
            dialogControllerStage.initOwner(mainStage);
        }
        dialogControllerStage.showAndWait();

    }

    public void clickMouse(MouseEvent mouseEvent) {
        tableNotes.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        btnEdit.setDisable(true);
    }
}
