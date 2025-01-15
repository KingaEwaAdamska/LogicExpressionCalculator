package Utils;

import javafx.scene.control.TextField;

public class Operation {
    private static double firstOperand = 0;
    private static String currentOperator = "";
    private static boolean startNewInput = true;


    public static void handleNumberInput(TextField display, int number) {
        if (startNewInput) {
            display.setText("");
            startNewInput = false;
        }
        display.setText(display.getText() + number);
    }

    public static void handleDotInput(TextField display) {
        if (startNewInput) {
            display.setText("0.");
            startNewInput = false;
        } else {
            String currentText = display.getText();
            if (!currentText.contains(".")) {
                display.setText(currentText + ".");
            }
        }
    }

    public static void handleOperator(TextField display, String operator) {
        firstOperand = Double.parseDouble(display.getText());
        currentOperator = operator;
        startNewInput = true;
    }

    public static void handleEquals(TextField display) {
        double secondOperand = Double.parseDouble(display.getText());
        double result = 0;
        switch (currentOperator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "*":
                result = firstOperand * secondOperand;
                break;
            case "/":
                if (secondOperand != 0) {
                    result = firstOperand / secondOperand;
                } else {
                    display.setText("Error");
                    return;
                }
                break;
        }
        display.setText(String.valueOf(result));
        startNewInput = true;
    }

    public static void handleClear(TextField display) {
        display.setText("0");
        firstOperand = 0;
        currentOperator = "";
        startNewInput = true;
    }

    public static void handleSqrt(TextField display) {
        double value = Double.parseDouble(display.getText());
        if (value >= 0) {
            display.setText(String.valueOf(Math.sqrt(value)));
        } else {
            display.setText("Error");
        }
        startNewInput = true;
    }
}
