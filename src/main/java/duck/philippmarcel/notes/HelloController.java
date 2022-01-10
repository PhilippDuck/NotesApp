package duck.philippmarcel.notes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private TextArea textArea;

    @FXML
    private ListView<Note> listView;

    private Database database;
    private ArrayList<Note> notesList = new ArrayList<Note>();

    @FXML
    protected void addNote() {
        // Get text for title in listView (max. 20 chars)
        String text = textArea.getText();
        String title;
        if (text.contains("\n")) {
            title = text.substring(0, text.indexOf("\n"));
        } else {
            title = text;
        }
        if (title.length() > 30) {
            title = title.substring(0, 30);
        }
        Note note = new Note(title, text);
        listView.getItems().add(note);
        database.insert(title, text);
    }

    @FXML
    protected void deleteNote() {
        Note note = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(note);
        database.delete(note.getTitle());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        database = new Database();
        notesList = database.getAllNotes();
        listView.getItems().addAll(notesList);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {
            @Override
            public void changed(ObservableValue<? extends Note> arg0, Note arg1, Note arg2) {
                textArea.setText(listView.getSelectionModel().getSelectedItem().getText());
            }

        });

    }
}