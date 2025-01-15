package com.example.calculator;

import Utils.BinaryConvert;
import Utils.Operation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    private static final int PADDING = 10;

    public void start(Stage stage) throws IOException {
        TextField display = createDisplay();
        GridPane gridPane = createCalcPane(stage, display);

        VBox container1 = new VBox();
        container1.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));
        container1.getChildren().addAll(display, gridPane);

        Button changeToBinaries = new Button("Binary converter");
        changeToBinaries.getStyleClass().add("change-button");

        Button changeToCalc = new Button("Calculator");
        changeToCalc.getStyleClass().add("change-button");

        BorderPane root = new BorderPane();
        root.setCenter(container1);
        root.setTop(changeToBinaries);

        Scene scene1 = new Scene(root);
        scene1.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        Label info = new Label("Konwersja systemów liczbowych");

        Label decimalToBinaryInfo = new Label("Podaj liczbę decymalnie");
        TextField decimalToBinaryField = new TextField();
        Button decimalToBinaryButton = new Button("Wylicz binarną");
        decimalToBinaryButton.setOnAction(e-> BinaryConvert.decimalToBinary(decimalToBinaryField));

        Label binaryToDecimalInfo = new Label("Podaj liczbę binarnie");
        TextField binaryToDecimalField = new TextField();
        Button binaryToDecimalButton = new Button("Wylicz decymalną");
        binaryToDecimalButton.setOnAction(e-> BinaryConvert.binaryToDecimal(binaryToDecimalField));

        VBox container2 = new VBox();
        GridPane gridPane2 = new GridPane();
        gridPane2.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        gridPane2.add(decimalToBinaryInfo, 1, 1);
        gridPane2.add(decimalToBinaryField, 2, 1);
        gridPane2.add(decimalToBinaryButton, 3, 1);

        gridPane2.add(binaryToDecimalInfo, 1, 2);
        gridPane2.add(binaryToDecimalField, 2, 2);
        gridPane2.add(binaryToDecimalButton, 3, 2);

        container2.getChildren().addAll(info, gridPane2);
        container2.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        BorderPane root2 = new BorderPane();
        root2.setCenter(container2);
        root2.setTop(changeToCalc);
        Scene scene2 = new Scene(root2);
        scene2.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        changeToBinaries.setOnAction(e -> {stage.setScene(scene2);});
        changeToCalc.setOnAction(e -> {stage.setScene(scene1);});

        stage.setTitle("Calculator");
        stage.setScene(scene1);
        stage.show();
    }

    private TextField createDisplay() {
        TextField display = new TextField();
        display.setEditable(false);
        display.setText("0");
        return display;
    }

    private Button[] createNumberButtons(TextField display) {
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            int number = i;
            numberButtons[i].setOnAction(e -> Operation.handleNumberInput(display, number));
        }
        return numberButtons;
    }

    private GridPane createCalcPane(Stage stage, TextField display) {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        Button[] numberButtons = createNumberButtons(display);
        Button equalButton = new Button("=");
        Button plusButton = new Button("+");
        Button minusButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");
        Button clearButton = new Button("Clear");
        Button dotButton = new Button(".");
        Button sqrtButton = new Button("sqrt");
        Button offButton = new Button("off");

        equalButton.getStyleClass().add("equal-button");

        offButton.setOnAction(event -> stage.close());
        equalButton.setOnAction(e -> Operation.handleEquals(display));
        plusButton.setOnAction(e -> Operation.handleOperator(display, "+"));
        minusButton.setOnAction(e -> Operation.handleOperator(display, "-"));
        multiplyButton.setOnAction(e -> Operation.handleOperator(display, "*"));
        divideButton.setOnAction(e ->Operation. handleOperator(display, "/"));
        clearButton.setOnAction(e -> Operation.handleClear(display));
        sqrtButton.setOnAction(e -> Operation.handleSqrt(display));
        dotButton.setOnAction(e -> Operation.handleDotInput(display)); // Obsługuje kropkę

        grid.add(clearButton, 1, 1);
        grid.add(plusButton, 4, 4);
        grid.add(minusButton, 4, 3);
        grid.add(multiplyButton, 4, 2);
        grid.add(divideButton, 4, 1);
        grid.add(sqrtButton, 3, 1);
        grid.add(offButton, 2, 1);
        grid.add(dotButton, 2, 5);
        grid.add(equalButton, 3, 5, 2, 1);

        for (int i = 0; i < numberButtons.length; i++) {
            int row = 2 + (9 - i) / 3;
            int col = 1 + (i - 1) % 3;
            if (i == 0) grid.add(numberButtons[i], 1, 5); // Place 0 separately
            else grid.add(numberButtons[i], col, row);
        }

        return grid;
    }



    public static void main(String[] args) {
        launch();
    }
}
