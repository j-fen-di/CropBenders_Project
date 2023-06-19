package hi;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

interface Resizing {

    // method to size and locate labels
    public static void sizeAndLocate(Label label, int width, int height, int x, int y, int fontSize) {
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setMinWidth(width);
        label.setMinHeight(height);
        label.setStyle("-fx-background-color: transparent;");
        label.setFont(Font.font("Stencil", fontSize));
    }

    // method to size and locate labels
    public static void sizeAndLocate(Button button, int width, int height, int x, int y, int fontSize) {
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setStyle("-fx-background-color: transparent;");
        button.setFont(Font.font("Stencil", fontSize));
    }
}
