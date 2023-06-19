package hi;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;


public class WinUI implements Resizing {
    private static Pane rootPane;
    private Player player;
    private HBox hb;

    public WinUI(Player player) {
        this.player = player;
        hb = new HBox();
        Label label = new Label("You  are a winner!!!!");
        Button back = new Button("Start Over");
        back.setOnAction(e -> {
            Welcome welcome = new Welcome();
            back.getScene().setRoot(welcome.getRootPane());
        });
        sizeAndLocateButton(50, 50, 2, 100, 20, back);
        rootPane = new StackPane();
        rootPane.getChildren().addAll(hb, label, back);
    }

    public static Pane getRootPane() {
        return rootPane;
    }
    private void sizeAndLocateButton(int width, int height, int x, int y,
                                     int fontSize, Button button) {
        button.prefWidth(width);
        button.prefHeight(height);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setFont(Font.font("Stencil", fontSize));
    }
}