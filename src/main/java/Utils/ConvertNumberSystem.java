package Utils;

import javafx.scene.control.TextField;

public class ConvertNumberSystem {

    public static void binaryToDecimal(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z binarnego na dziesiętny
            String value = input.getText();
            int result = Integer.parseInt(value, 2);
            input.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            input.setText("Invalid binary input");
        }
    }

    public static void decimalToBinary(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z dziesiętnego na binarny
            String value = input.getText();
            int decimalValue = Integer.parseInt(value);
            String result = Integer.toBinaryString(decimalValue);
            input.setText(result);
        } catch (NumberFormatException e) {
            input.setText("Invalid decimal input");
        }
    }

    public static void hexToDecimal(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z szesnastkowego na dziesiętny
            String value = input.getText();
            int result = Integer.parseInt(value, 16);
            input.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            input.setText("Invalid hexadecimal input");
        }
    }

    public static void decimalToHex(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z dziesiętnego na szesnastkowy
            String value = input.getText();
            int decimalValue = Integer.parseInt(value);
            String result = Integer.toHexString(decimalValue).toUpperCase();
            input.setText(result);
        } catch (NumberFormatException e) {
            input.setText("Invalid decimal input");
        }
    }

    public static void hexToBinary(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z szesnastkowego na binarny
            String value = input.getText();
            int decimalValue = Integer.parseInt(value, 16);
            String result = Integer.toBinaryString(decimalValue);
            input.setText(result);
        } catch (NumberFormatException e) {
            input.setText("Invalid hexadecimal input");
        }
    }

    public static void binaryToHex(TextField input) {
        try {
            // Pobierz wartość tekstową i przekonwertuj z binarnego na szesnastkowy
            String value = input.getText();
            int decimalValue = Integer.parseInt(value, 2);
            String result = Integer.toHexString(decimalValue).toUpperCase();
            input.setText(result);
        } catch (NumberFormatException e) {
            input.setText("Invalid binary input");
        }
    }
}
