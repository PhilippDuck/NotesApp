package duck.philippmarcel.notes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import java.io.IOException;

/**
 * Class of a custom ListCell
 */
public class NoteCell extends ListCell<Note> {
    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

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
            dateLabel.setText(note.getCreationDate());
            setText(null);
            setGraphic(pane);
        }
    }
}
