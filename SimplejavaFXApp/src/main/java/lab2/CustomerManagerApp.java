package lab2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Customer Name");

        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Central",
                "Lusaka",
                "Copperbelt"
        );

        Button saveButton = new Button("Save Customer");
        Button deleteButton = new Button("Delete Customer");

        Label status = new Label();

        ObservableList<Customer> customers =
                FXCollections.observableArrayList();

        TableView<Customer> table = new TableView<>();

        TableColumn<Customer, String> nameCol =
                new TableColumn<>("Customer Name");

        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> provinceCol =
                new TableColumn<>("Province");

        provinceCol.setCellValueFactory(
                new PropertyValueFactory<>("province"));

        table.getColumns().addAll(nameCol, provinceCol);
        table.setItems(customers);

        saveButton.setOnAction(e -> {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                status.setText("Enter customer name.");
                return;
            }

            String province = provinceBox.getValue();

            if (province == null) {
                status.setText("Choose a province.");
                return;
            }

            customers.add(new Customer(name, province));

            status.setText("Customer saved successfully.");

            nameField.clear();
            provinceBox.setValue(null);
        });

        deleteButton.setOnAction(e -> {

            Customer selected =
                    table.getSelectionModel().getSelectedItem();

            if (selected == null) {
                status.setText("Select a customer first.");
                return;
            }

            Alert alert = new Alert(
                    Alert.AlertType.CONFIRMATION);

            alert.setTitle("Confirm Delete");
            alert.setHeaderText("Delete Customer");
            alert.setContentText(
                    "Are you sure you want to delete this customer?"
            );

            if (alert.showAndWait().get()
                    == ButtonType.OK) {

                customers.remove(selected);
                status.setText("Customer deleted.");
            }
        });

        root.getChildren().addAll(
                nameField,
                provinceBox,
                saveButton,
                deleteButton,
                table,
                status
        );

        Scene scene = new Scene(root, 700, 500);

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}