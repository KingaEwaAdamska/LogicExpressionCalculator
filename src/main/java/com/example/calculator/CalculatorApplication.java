package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        TextField display = new TextField();
        display.setEditable(false);

        Button[] numberButtons = new Button[10];
        for(int i = 0; i<10; i++){
            numberButtons[i] = new Button(String.valueOf(i));
        }
        Button equalButton = new Button("=");
        equalButton.getStyleClass().add("equal-button");
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");
        Button clearButton = new Button("Clear");
        Button dotButton = new Button(".");
        Button sqrtButton = new Button("sqrt");
        Button offButton = new Button("off");
        offButton.setOnAction(event -> stage.close());

        VBox container = new VBox();
        container.setPadding(new Insets(10, 10, 10, 10));

        GridPane grid = new GridPane();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.add(clearButton, 1, 1);
        grid.add(plusButton, 4, 4);
        grid.add(minusButton, 4, 3);
        grid.add(multiplyButton, 4, 2);
        grid.add(divideButton, 4, 1);
        grid.add(sqrtButton, 3, 1);
        grid.add(offButton, 2, 1);
        grid.add(dotButton, 2, 5);
        grid.add(equalButton, 3, 5, 2, 1);

        grid.add(numberButtons[7], 1, 2);
        grid.add(numberButtons[8], 2, 2);
        grid.add(numberButtons[9], 3, 2);
        grid.add(numberButtons[4], 1, 3);
        grid.add(numberButtons[5], 2, 3);
        grid.add(numberButtons[6], 3, 3);
        grid.add(numberButtons[1], 1, 4);
        grid.add(numberButtons[2], 2, 4);
        grid.add(numberButtons[3], 3, 4);
        grid.add(numberButtons[0], 1, 5);



        container.getChildren().addAll(display, grid);


        BorderPane root = new BorderPane();
        root.setCenter(container);

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());



        stage.setTitle("Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}