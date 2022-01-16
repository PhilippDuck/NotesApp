package duck.philippmarcel.notes;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class NoteCellFactory implements Callback<ListView<Note>, ListCell<Note>> {

    @Override
    public ListCell<Note> call(ListView<Note> param) {
        return new NoteCell();
    }
}
