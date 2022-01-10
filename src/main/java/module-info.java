module duck.philippmarcel.notes {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens duck.philippmarcel.notes to javafx.fxml;
    exports duck.philippmarcel.notes;
}