package Utils;

import javafx.scene.control.TextField;

public class BinaryConvert {

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
}