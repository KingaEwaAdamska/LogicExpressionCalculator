package com.example.calculator;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogicInfoWindow {

    public static void displayInfo() {
        Stage stage = new Stage();
        stage.setTitle("Writing Logical Equations");

        Label mainInfo = new Label("Accepted notation for writing logical equations:");

        TextArea info = new TextArea();
        info.setEditable(false);
        info.setText("Variables are lowercase letters \n Conjunction notation: & \n Disjunction notation: | \n Negation notation: ~ \n Equivalence notation: = \n Implication notation: >");

        Button exitButton = new Button("Close");
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(mainInfo, info, exitButton);
        layout.setSpacing(10);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        Scene scene = new Scene(layout);

        stage.setScene(scene);
        stage.showAndWait();
    }
}
