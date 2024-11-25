module com.example.tableviewserializable {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tableviewserializable to javafx.fxml;
    exports com.example.tableviewserializable;
}