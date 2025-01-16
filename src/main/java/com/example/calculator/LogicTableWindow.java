package com.example.calculator;

import Utils.LogicCalc;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class LogicTableWindow {

    public static void display(TextField equation) {

        Stage stage = new Stage();
        VBox layout = new VBox();
        layout.setSpacing(10);
        layout.setAlignment(Pos.CENTER);

        String equationString;
        String binaryString;
        LogicCalc logicCalc = new LogicCalc();

        Scene scene;
        Label mainInfo;

        Button exitButton = new Button("Zamknij");
        exitButton.setOnAction(e -> stage.close());

        if(isCorrect(equation.getText())){
            stage.setTitle("Wynik wyrażenia");

            mainInfo = new Label("Tabela prawdy dla równania:");

            List<Character> variables = variablesInEquation(equation.getText());

            TableView<List<Integer>> table = new TableView<>();
            int index = 0;

            for (Character variable : variables){
                TableColumn<List<Integer>, Integer> column = new TableColumn<>(variable.toString());
                index = variables.indexOf(variable);

                int finalIndex = index;
                column.setCellValueFactory(cellData -> {
                    List<Integer> rowData = cellData.getValue();
                    if (rowData != null && finalIndex < rowData.size()) {
                        return new SimpleObjectProperty<>(rowData.get(finalIndex)); // Wrap the value in a property
                    } else {
                        return new SimpleObjectProperty<>(null); // Handle nulls appropriately
                    }
                });

                table.getColumns().add(column);
            }
            TableColumn<List<Integer>, Integer> equationColumn = new TableColumn<>(equation.getText()); // Zakładając, że `equation.getText()` jest poprawne
            equationColumn.setCellValueFactory(cellData -> {
                List<Integer> rowData = cellData.getValue();
                if (rowData != null && rowData.size() > variables.size()) { // Upewnij się, że mamy dane
                    return new SimpleObjectProperty<>(rowData.get(variables.size())); // Ostatnia kolumna
                } else {
                    return new SimpleObjectProperty<>(null); // Handle nulls appropriately
                }
            });
            table.getColumns().add(equationColumn);
            table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            table.itemsProperty().addListener((obs, old, items) -> {
                table.setFixedCellSize(30);
                table.prefHeightProperty().bind(
                        table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1)) // +1 for header
                );
            });


            ObservableList<List<Integer>> data = FXCollections.observableArrayList();

            int rows = (int) Math.pow(2, variables.size());
            for (int i = 0; i < rows; i++) {
                List<Integer> temp = new ArrayList<>();
                equationString = equation.getText();

                // Convert to binary with padding
                binaryString = String.format("%" + variables.size() + "s", Integer.toBinaryString(i)).replace(' ', '0');

                // Replace variables in the equation with binary values
                for (int j = 0; j < variables.size(); j++) {
                    char variable = variables.get(j);
                    char value = binaryString.charAt(j);
                    temp.add(Integer.parseInt(String.valueOf(value)));
                    equationString = equationString.replace(variable, value);
                }
                if(logicCalc.solveEquation(equationString)){
                    temp.add(1);
                }else{
                    temp.add(0);
                }

                data.add(temp);
            }
            table.setItems(data);

            layout.setAlignment(Pos.CENTER);
            layout.setSpacing(10);
            layout.setPadding(new Insets(10,10,10,10));
            layout.getChildren().addAll(mainInfo, table, exitButton);
            scene = new Scene(layout);
        }else{
            stage.setTitle("Błędne wyrażenie");
            mainInfo = new Label("Błędnie zapisane wyrażenie logiczne");
            layout.getChildren().addAll(mainInfo, exitButton);
            scene = new Scene(layout, 300, 100);
        }

        stage.setScene(scene);
        stage.showAndWait();
    }

    public static List<Character> variablesInEquation(String equation){
        List<Character> variables = new java.util.ArrayList<>();
        for (int i = 0; i < equation.length(); i++) {
            if (Character.isLowerCase(equation.charAt(i))){
                if(!variables.contains(equation.charAt(i))){
                    variables.add(equation.charAt(i));
                }
            }
        }
        return variables;
    }

    public static boolean isCorrect(String equation){
        if (!correctnessOfBrackets(equation)){
            return false;
        }
        for (int i = 0; i < equation.length()-1; i++){
            if (!correctnessOfNextChar(equation.charAt(i), equation.charAt(i+1))){ return false; }
        }
        return true;
    }

    private static boolean correctnessOfBrackets(String equation){
        int bracket1 = 0;
        int bracket2 = 0;
        for (int i = 0; i < equation.length(); i++){
            if (equation.charAt(i) == '('){
                bracket1++;
            }else if (equation.charAt(i) == ')'){
                bracket2++;
            }
        }
        return bracket1 == bracket2;
    }

    private static boolean correctnessOfNextChar(char first, char second){
        switch (first){
            case '~':
                if (!checkNegation(second)) return false;
                break;
            case '(':
            case '&':
            case '|':
            case '>':
            case '=':
                if (!checkAndOr(second)) return false;
                break;
            case ')':
            default:
                if (!checkLetterOrBracket(second)) return false;
        }
        return true;
    }

    private static boolean checkLetterOrBracket(char character){
        char[] tab = {')', '&', '|', '=', '>'};
        return new String(tab).indexOf(character) >= 0;
    }

    private static boolean checkAndOr(char character){
        boolean x = false;

        if (character == '('){
            x = true;
        }else if (character == '~'){
            x = true;
        }else if (Character.isLetter(character)){
            x = true;
        }

        return x;
    }

    private static boolean checkNegation(char znak){
        boolean x = false;
        if (znak == '('){
            x = true;
        }else if (Character.isLetter(znak)){
            x = true;
        }
        return x;
    }
}
