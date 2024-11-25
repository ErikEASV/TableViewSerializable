package com.example.tableviewserializable;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PersonAdresseApp extends Application {

    private ObservableList<Person> persons = FXCollections.observableArrayList();
    private ObservableList<Adresse> addresses = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        // Indlæs data fra fil
        List<Person> loadedPersons = SerializationHelper.loadData();
        if (loadedPersons != null)
            persons.setAll(loadedPersons);

        // Person TableView
        TableView<Person> personTable = new TableView<>(persons);

        TableColumn<Person, String> personIdColumn = new TableColumn<>("Id");
        personIdColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        personTable.getColumns().add(personIdColumn);
        TableColumn<Person, String> personNameColumn = new TableColumn<>("Navn");
        personNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNavn()));
        personTable.getColumns().add(personNameColumn);

        // Adresse TableView
        TableView<Adresse> addressTable = new TableView<>(addresses);

        TableColumn<Adresse, String> vejColumn = new TableColumn<>("Vej");
        vejColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVej()));
        TableColumn<Adresse, String> postnrColumn = new TableColumn<>("Postnr");
        postnrColumn.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPostnr())));
        TableColumn<Adresse, String> byColumn = new TableColumn<>("By");
        byColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBy()));
        addressTable.getColumns().addAll(vejColumn, postnrColumn, byColumn);

        // Opdater adresser når en person vælges
        personTable.getSelectionModel().selectedItemProperty().addListener((obs, oldPerson, newPerson) -> {
            if (newPerson != null) {
                addresses.setAll(newPerson.getAdresser());
            } else {
                addresses.clear();
            }
        });

        FlowPane fp = new FlowPane();
        TextField id = new TextField("Id");
        TextField navn = new TextField("Navn");
        Button tilføjPerson = new Button("Tilføj");
        tilføjPerson.setOnAction(e -> {
            persons.add(new Person(Integer.valueOf(id.getText()), navn.getText()));
            }
        );
        fp.getChildren().addAll(id,navn,tilføjPerson);

        FlowPane fp2 = new FlowPane();
        TextField vej = new TextField("Vej");
        TextField postnr = new TextField("Postnr");
        TextField by = new TextField("By");
        Button tilføjAdresse = new Button("Tilføj");
        tilføjAdresse.setOnAction(e -> {
                    Person p = (Person) personTable.getSelectionModel().getSelectedItem();
                    if (p != null) {
                        Adresse a = new Adresse(vej.getText(), Integer.valueOf(postnr.getText()), by.getText());
                        p.addAdresse(a);
                        addresses.add(a);
                    }
                    vej.clear();
                    postnr.clear();
                    by.clear();
                }
        );
        fp2.getChildren().addAll(vej,postnr,by,tilføjAdresse);

        // Layout
        VBox root = new VBox(10, personTable, fp, addressTable, fp2);
        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Person og Adresse");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Gem data ved lukning
        primaryStage.setOnCloseRequest(event -> SerializationHelper.saveData(new ArrayList<>(persons)));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
