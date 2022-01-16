package duck.philippmarcel.notes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class NoteCell extends ListCell<Note> {
    @FXML
    private Label titleLabel;

    @FXML
    private GridPane pane;

    public NoteCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cell.fxml"));
            loader.setController(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Note note, boolean empty) {
        super.updateItem(note, empty);

        if(empty || note == null) {
            setText(null);
            setGraphic(null);
        }
        else {
            titleLabel.setText(note.getTitle());
            setText(null);
            setGraphic(pane);
        }
    }
}
