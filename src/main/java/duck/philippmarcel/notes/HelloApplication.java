/*
 * TODO:
 * - delete notes with ID
 * - no error when last note is deleted
 * - show text of first note on start
 * - add icon
 * - creationdate?
 * -
 */

package duck.philippmarcel.notes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("stylesheet.css").toString());
        stage.setTitle("Notes");
        stage.getIcons().add(new Image(getClass().getResource("icons/note.png").toString()));
        stage.setWidth(900);
        stage.setHeight(700);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}