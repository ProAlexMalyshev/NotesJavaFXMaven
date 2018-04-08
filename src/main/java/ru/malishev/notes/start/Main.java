package ru.malishev.notes.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ru.malishev.notes.interfaces.impls.DBNote;
import ru.malishev.notes.controllers.MainController;
/**
 * JavaFXMavenSQLite. "Notes"
 * @author Aleksey Malyshev
 * @version 1.0 dated April 08, 2018
 */
public class Main extends Application {
    @Override
    public void init() throws Exception {
        super.init();
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/fxml/mainSample.fxml"));
        Parent fxmlMain = fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle("Заметки");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        Scene scene = new Scene(fxmlMain, 600, 400);
        scene.getStylesheets().add(String.valueOf(getClass().getResource("/styles/styles.css")));// подключение стилей
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DBNote dbNote = new DBNote();
        dbNote.closeDb();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
