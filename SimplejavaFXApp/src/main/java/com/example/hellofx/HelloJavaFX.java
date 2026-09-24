package com.example.hellofx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        Label message = new Label("Welcome, Anthony Munenga ID 20250991 !");
        Button button = new Button("press");

        button.setOnAction(event ->
                message.setText("Great job Mr Anthony.")
        );
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(event->message.setText("Welcome, Anthony Munenga ID 20250991"));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(message, button, resetButton);

        Scene scene = new Scene(layout, 500, 300);

        stage.setTitle("My First JavaFX Application");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
