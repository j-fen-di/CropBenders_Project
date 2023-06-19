package hi;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class InitConfig {

    // UI variables
    private final Pane rootPane;
    private TextField name;
    private Button easy;
    private Button medium;
    private Button hard;
    private Button carrot;
    private Button cabbage;
    private Button corn;
    private Button spring;
    private Button summer;
    private Button fall;
    private Button winter;
    private Button startGame;
    private HBox hbox;
    private Image backgroundImage;
    private ImageView iv;

    // initial configuration variables
    private String inputName;
    private String difficulty;
    private String seedType;
    private String season;

    // constructor method that builds 'Initial Configuration' UI
    public InitConfig() {

        // instantiates root pane for initConfig UI
        rootPane = new StackPane();

        // set default configuration (left-most options) except for name
        difficulty = "easy";
        seedType = "carrot";
        season = "spring";

        // field to type in name
        name = new TextField();
        name.setTranslateX(455);
        name.setTranslateY(160);
        name.setPrefWidth(150);
        name.setPrefHeight(32);
        name.setStyle("-fx-background-color: transparent;");
        name.setFont(Font.font("Stencil", 14));

        // pattern checker to limit name to 20 characters
        Pattern pattern = Pattern.compile(".{0,20}");
        TextFormatter formatter =
                new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
                    return pattern.matcher(
                            change.getControlNewText()).matches() ? change : null; });
        name.setTextFormatter(formatter);

        // chooses 'easy' as difficulty
        easy = new Button("Easy");
        sizeAndLocate(easy, 180, 245, 70, 32);
        easy.setStyle("-fx-background-color: transparent;");
        easy.setFont(Font.font("Stencil", 11));
        easy.setOnAction(e -> {
            difficulty = "easy"; });

        // chooses 'medium' as difficulty
        medium = new Button("Medium");
        sizeAndLocate(medium, 263, 245, 100, 32);
        medium.setStyle("-fx-background-color: transparent;");
        medium.setFont(Font.font("Stencil", 11));
        medium.setOnAction(e -> {
            difficulty = "medium"; });

        // chooses 'hard' as difficulty
        hard = new Button("Hard");
        sizeAndLocate(hard, 345, 245, 70, 32);
        hard.setStyle("-fx-background-color: transparent;");
        hard.setFont(Font.font("Stencil", 11));
        hard.setOnAction(e -> {
            difficulty = "hard"; });

        // chooses 'carrot' as seed type
        carrot = new Button("Carrot");
        sizeAndLocate(carrot, -5, 332, 80, 32);
        carrot.setStyle("-fx-background-color: transparent;");
        carrot.setFont(Font.font("Stencil", 11));
        carrot.setOnAction(e -> {
            seedType = "carrot"; });

        // chooses 'cabbage' as seed type
        cabbage = new Button("Cabbage");
        sizeAndLocate(cabbage, 75, 332, 95, 32);
        cabbage.setStyle("-fx-background-color: transparent;");
        cabbage.setFont(Font.font("Stencil", 11));
        cabbage.setOnAction(e -> {
            seedType = "cabbage"; });

        // chooses 'corn' as seed type
        corn = new Button("Corn");
        sizeAndLocate(corn, 145, 332, 80, 32);
        corn.setStyle("-fx-background-color: transparent;");
        corn.setFont(Font.font("Stencil", 11));
        corn.setOnAction(e -> {
            seedType = "corn"; });

        // chooses 'spring' as season
        spring = new Button("Spring");
        sizeAndLocate(spring, -250, 428, 80, 32);
        spring.setStyle("-fx-background-color: transparent;");
        spring.setFont(Font.font("Stencil", 11));
        spring.setOnAction(e -> {
            season = "spring"; });

        // chooses 'summer' as season
        summer = new Button("Summer");
        sizeAndLocate(summer, -202, 428, 90, 32);
        summer.setStyle("-fx-background-color: transparent;");
        summer.setFont(Font.font("Stencil", 11));
        summer.setOnAction(e -> {
            season = "summer"; });

        // chooses 'fall' as season
        fall = new Button("Fall");
        sizeAndLocate(fall, -163, 428, 80, 32);
        fall.setStyle("-fx-background-color: transparent;");
        fall.setFont(Font.font("Stencil", 11));
        fall.setOnAction(e -> {
            season = "fall"; });

        // chooses 'winter' as season
        winter = new Button("Winter");
        sizeAndLocate(winter, -109, 428, 80, 32);
        winter.setStyle("-fx-background-color: transparent;");
        winter.setFont(Font.font("Stencil", 11));
        winter.setOnAction(e -> {
            season = "winter"; });

        /** starts game and sends initial configuration variables in order
         *  to set up the initial farm UI
         */
        startGame = new Button("Start Game");
        sizeAndLocate(startGame, -417, 498, 180, 32);
        startGame.setStyle("-fx-background-color: transparent;");
        startGame.setFont(Font.font("Stencil", 17));
        startGame.setOnAction(e -> {
            inputName = name.getText();
            if (inputName != null && !inputName.isEmpty()) {
                InitFarmUI game = new InitFarmUI(new Player(inputName, difficulty,
                        seedType, season));
                startGame.getScene().setRoot(game.getRootPane());
            } else {
                AlertBox.displayAlert("Error", "Please type in your name.");
            }
        });

        // adds nodes to hbox
        hbox = new HBox();
        hbox.getChildren().addAll(name, easy, medium, hard, carrot, cabbage, corn,
                spring, summer, fall, winter, startGame);

        // sets up initial configuration background image
        backgroundImage = new Image("file:src/hi/img/InitConfig.gif",
                1000, 600, false, false);
        iv = new ImageView();
        iv.setImage(backgroundImage);

        // overlays nodes on top of background image
        rootPane.getChildren().addAll(iv, hbox);
    }

    // function to size and locate buttons on screen
    public void sizeAndLocate(Button button, int width, int height, int x, int y) {
        button.setTranslateX(width);
        button.setTranslateY(height);
        button.setPrefWidth(x);
        button.setPrefHeight(y);
    }

    // getter method for initConfig root pane
    public Pane getRootPane() {
        return rootPane;
    }

    public void setName(String name1) {
        if (name1 != null && name1.length() <= 20) {
            inputName = name1;
        } else {
            inputName = " ";
        }
    }

    public String getName() {
        return inputName;
    }


    public String checkButton(String id) {
        String buttonId = id;
        if (id != buttonId) {
            return null;
        }
        return buttonId;
    }
}
