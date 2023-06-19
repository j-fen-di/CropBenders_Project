package hi;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class Welcome {
    // UI variables
    private final Pane rootPane;
    private Button start;
    private HBox hbox;
    private Image backgroundImage;
    private ImageView iv;
    private String imageText;
    private String buttonId;
    // constructor method that builds 'Welcome' UI
    public Welcome() {
        imageText = "file:src/hi/img/Welcome.png";
        buttonId = "Get Started";
        System.out.println(imageText);
        // instantiates root pane for Welcome UI
        rootPane = new StackPane();
        // brings user from 'Welcome' scene to 'initConfig' scene
        start = new Button("Get Started");
        start.setId(buttonId);
        start.setId("firstName");
        start.setTranslateX(410);
        start.setTranslateY(475);
        start.setPrefWidth(180);
        start.setPrefHeight(32);
        //make button transparent
        start.setStyle("-fx-background-color: transparent;");
        start.setFont(Font.font("Stencil", 17));
        start.setOnAction(e -> {
            InitConfig setup = new InitConfig();
            start.getScene().setRoot(setup.getRootPane());
        });
        // adds nodes to hbox
        hbox = new HBox();
        hbox.getChildren().addAll(start);
        // sets up initial configuration background image
        backgroundImage = new Image("file:src/hi/img/Welcome.gif", 1000, 600, false, false);
        iv = new ImageView();
        iv.setImage(backgroundImage);
        // overlays nodes on top of background image
        rootPane.getChildren().addAll(iv, hbox);
    }
    // getter method for Welcome root pane
    public Pane getRootPane() {
        return rootPane;
    }
    public String getImage(String name)  {
        if (imageText != name) {
            return null;
        }
        return name;
    }
    public String checkButton(String id) {
        if (id != buttonId) {
            return null;
        }
        return buttonId;
    }
}