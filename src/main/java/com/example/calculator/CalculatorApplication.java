package com.example.calculator;

import Utils.ConvertNumberSystem;
import Utils.Operation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalculatorApplication extends Application {
    private static final int PADDING = 10;
    private static final Insets INSETS = new Insets(PADDING, PADDING, PADDING, PADDING);

    public void start(Stage stage) throws IOException {
        TextField display = createDisplay();
        GridPane gridPane = createCalcPane(stage, display);

        VBox calcMainContainer = new VBox();
        calcMainContainer.setPadding(INSETS);
        calcMainContainer.getChildren().addAll(display, gridPane);

        Button changeToBinaries = new Button("Konwersja systemów liczbowych");
        changeToBinaries.getStyleClass().add("change-button");

        Button changeToCalc = new Button("Kalkulator prosty");
        changeToCalc.getStyleClass().add("change-button");

        Button changeToLogic = new Button("Kalkulator logiczny");
        changeToLogic.getStyleClass().add("change-button");

        Button changeToBinaries1 = new Button("Konwersja systemów liczbowych");
        changeToBinaries1.getStyleClass().add("change-button");

        Button changeToCalc1 = new Button("Kalkulator prosty");
        changeToCalc1.getStyleClass().add("change-button");

        Button changeToLogic1 = new Button("Kalkulator logiczny");
        changeToLogic1.getStyleClass().add("change-button");

        Label info = new Label("Konwersja systemów liczbowych");

        VBox conversionMainContainer = new VBox();
        GridPane convGrid = createConversionPane();

        conversionMainContainer.getChildren().addAll(info, convGrid);
        conversionMainContainer.setPadding(INSETS);

        VBox logicMainContainer = new VBox();
        Button logicInfo = new Button("Jak pisać?");
        logicInfo.setOnAction(e -> {LogicInfoWindow.displayInfo();});

        TextField logicalEquationField = new TextField();

        Button logicalEquationButton = new Button("Rozwiąż");
        logicalEquationButton.setOnAction(e -> {LogicTableWindow.display(logicalEquationField);});



        logicMainContainer.getChildren().addAll(logicInfo, logicalEquationField, logicalEquationButton);
        logicMainContainer.setPadding(INSETS);
        logicMainContainer.setAlignment(Pos.CENTER);
        logicMainContainer.setSpacing(10);

        HBox calcHeader = new HBox();
        calcHeader.getChildren().addAll(changeToBinaries, changeToLogic);

        HBox conversionHeader = new HBox();
        conversionHeader.getChildren().addAll(changeToCalc, changeToLogic1);

        HBox logicHeader = new HBox();
        logicHeader.getChildren().addAll(changeToCalc1, changeToBinaries1);

        BorderPane calcLayout = new BorderPane();
        calcLayout.setCenter(calcMainContainer);
        calcLayout.setTop(calcHeader);
        Scene calcScene = new Scene(calcLayout);
        calcScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("calcScene.css")).toExternalForm());

        BorderPane conversionLayout = new BorderPane();
        conversionLayout.setCenter(conversionMainContainer);
        conversionLayout.setTop(conversionHeader);
        Scene conversionScene = new Scene(conversionLayout);
        conversionScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("convertScene.css")).toExternalForm());

        BorderPane logicLayout = new BorderPane();
        logicLayout.setCenter(logicMainContainer);
        logicLayout.setTop(logicHeader);
        Scene logicScene = new Scene(logicLayout, 300,300);
        logicScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("logicScene.css")).toExternalForm());

        changeToCalc.setOnAction(e -> {stage.setScene(calcScene);});
        changeToBinaries.setOnAction(e -> {stage.setScene(conversionScene);});
        changeToLogic.setOnAction(e -> {stage.setScene(logicScene);});
        changeToCalc1.setOnAction(e -> {stage.setScene(calcScene);});
        changeToBinaries1.setOnAction(e -> {stage.setScene(conversionScene);});
        changeToLogic1.setOnAction(e -> {stage.setScene(logicScene);});

        stage.setTitle("Kalkulator");
        stage.setScene(calcScene);
        stage.show();
    }

    private TextField createDisplay() {
        TextField display = new TextField();
        display.setEditable(false);
        display.setText("0");
        display.getStyleClass().add("display");
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

    private GridPane createConversionPane() {

        GridPane grid = new GridPane();

        Label decimalToBinaryInfo = new Label("Podaj liczbę decymalnie");
        TextField decimalToBinaryField = new TextField();
        Button decimalToBinaryButton = new Button("Wylicz binarną");
        decimalToBinaryButton.setOnAction(e-> ConvertNumberSystem.decimalToBinary(decimalToBinaryField));

        Label binaryToDecimalInfo = new Label("Podaj liczbę binarnie");
        TextField binaryToDecimalField = new TextField();
        Button binaryToDecimalButton = new Button("Wylicz decymalną");
        binaryToDecimalButton.setOnAction(e-> ConvertNumberSystem.binaryToDecimal(binaryToDecimalField));

        Label decimalToHexInfo = new Label("Podaj liczbę decymalnie");
        TextField decimalToHexField = new TextField();
        Button decimalToHexButton = new Button("Wylicz heksadecymalną");
        decimalToHexButton.setOnAction(e-> ConvertNumberSystem.decimalToHex(decimalToHexField));

        Label hexToDecimalInfo= new Label("Podaj liczbę heksadecymalnie");
        TextField hexToDecimalField = new TextField();
        Button hexToDecimalButton = new Button("Wylicz decymalną");
        hexToDecimalButton.setOnAction(e-> ConvertNumberSystem.hexToDecimal(hexToDecimalField));

        Label hexToBinaryInfo= new Label("Podaj liczbę heksadecymalnie");
        TextField hexToBinaryField = new TextField();
        Button hexToBinaryButton = new Button("Wylicz binarną");
        hexToBinaryButton.setOnAction(e-> ConvertNumberSystem.hexToBinary(hexToBinaryField));

        Label binaryToHexInfo = new Label("Podaj liczbę binarnie");
        TextField binaryToHexField = new TextField();
        Button binaryToHexButton = new Button("Wylicz heksadecymalną");
        binaryToHexButton.setOnAction(e-> ConvertNumberSystem.binaryToHex(binaryToHexField));


        grid.setPadding(new Insets(PADDING, PADDING, PADDING, PADDING));

        grid.add(decimalToBinaryInfo, 1, 1);
        grid.add(decimalToBinaryField, 2, 1);
        grid.add(decimalToBinaryButton, 3, 1);

        grid.add(binaryToDecimalInfo, 1, 2);
        grid.add(binaryToDecimalField, 2, 2);
        grid.add(binaryToDecimalButton, 3, 2);

        grid.add(decimalToHexInfo, 1, 3);
        grid.add(decimalToHexField, 2, 3);
        grid.add(decimalToHexButton, 3, 3);

        grid.add(hexToDecimalInfo, 1, 4);
        grid.add(hexToDecimalField, 2, 4);
        grid.add(hexToDecimalButton, 3, 4);

        grid.add(binaryToHexInfo, 1, 5);
        grid.add(binaryToHexField, 2, 5);
        grid.add(binaryToHexButton, 3, 5);

        grid.add(hexToBinaryInfo, 1, 6);
        grid.add(hexToBinaryField, 2, 6);
        grid.add(hexToBinaryButton, 3, 6);

        return grid;
    }

    public static void main(String[] args) {
        launch();
    }
}
