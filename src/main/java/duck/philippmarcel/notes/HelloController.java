package duck.philippmarcel.notes;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;

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
        if (title != "") {
            Note note = new Note(title, text);
            listView.getItems().add(note);
            database.insert(title, text, note.getUuid(), note.getCreationDate());
        }
        // Select the last/new note in listView
        listView.getSelectionModel().select(listView.getItems().size()-1);
    }

    @FXML
    protected void deleteNote() {
        Note note = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(note);
        database.delete(note.getUuid());
    }

    @FXML
    protected void updateNote() {
        // Get text for title in listView (max. 20 chars)
        try {
            Note note = listView.getSelectionModel().getSelectedItem();
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
            if (title != "") {
                note.setText(text);
                note.setTitle(title);
                int index = listView.getItems().indexOf(note);
                listView.getItems().set(index, note);
                database.update(note.getUuid(), note.getTitle(), note.getText());

            }
        } catch (Exception ex) {
            System.out.println("No noteobject to update!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        database = new Database();
        notesList = database.getAllNotes();
        listView.getItems().addAll(notesList);
        listView.setCellFactory(new NoteCellFactory());


        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Note>() {
            @Override
            public void changed(ObservableValue<? extends Note> arg0, Note arg1, Note arg2) {
                try {
                    textArea.setText(listView.getSelectionModel().getSelectedItem().getText());
                } catch (Exception ex) {
                    System.out.println("no Elements in listView!");
                    textArea.setText("");
                }

            }

        });

    }
}

