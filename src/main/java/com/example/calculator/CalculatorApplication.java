package com.example.calculator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CalculatorApplication extends Application {
    private static final int PADDING = 10;
    private static final Insets INSETS = new Insets(PADDING, PADDING, PADDING, PADDING);

    public void start(Stage stage) throws IOException {
        TextField display = createDisplay();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);


        VBox logicMainContainer = new VBox();
        Button logicTutorial = new Button("How to write?");
        logicTutorial.setOnAction(e -> {LogicInfoWindow.displayInfo();});
        TextField logicalEquationField = new TextField();

        Button logicalEquationButton = new Button("Solve");
        logicalEquationButton.setOnAction(e -> {LogicTableWindow.display(logicalEquationField);});

        logicMainContainer.getChildren().addAll(logicTutorial, logicalEquationField, logicalEquationButton);
        logicMainContainer.setPadding(INSETS);
        logicMainContainer.setAlignment(Pos.CENTER);
        logicMainContainer.setSpacing(10);

        BorderPane logicLayout = new BorderPane();
        logicLayout.setCenter(logicMainContainer);
        Scene logicScene = new Scene(logicLayout, 400,300);
        logicScene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("logicScene.css")).toExternalForm());

        stage.setTitle("Logic Expression Calculator");
        stage.setScene(logicScene);
        stage.show();
    }

    private TextField createDisplay() {
        TextField display = new TextField();
        display.setEditable(false);
        display.setText("0");
        display.getStyleClass().add("display");
        return display;
    }


    public static void main(String[] args) {
        launch();
    }
}
