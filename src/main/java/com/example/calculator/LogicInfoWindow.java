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
        stage.setTitle("Pisanie równań logicznych");

        Label mainInfo = new Label("Przyjęty sposób zapisu równań logicznych:");

        TextArea info = new TextArea();
        info.setEditable(false);
        info.setText("Zmienne to małe litery \n Zapis koniunkcji: & \n Zapis alternatywy: | \n Zapis zaprzeczenia: ~ \n Zapis równoważności: = \n Zapis implikacji: >");

        Button exitButton = new Button("Zamknij");
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
