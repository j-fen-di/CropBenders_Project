package hi;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
public class InitFarmUI implements Resizing {
    // UI variables
    private final Pane rootPane;
    private ComboBox combo;
    private HBox buttons;
    private HBox userName;
    private HBox moneyDisplay;
    private HBox seedTypeDisplay;
    private HBox seasonDisplay;
    private HBox diffDisplay;
    private HBox drhb;
    private HBox inventoryDisplay;
    private Button goToInventory;
    private HBox gridBox;
    private Image backgroundImage;
    private ImageView iv;
    private Label days;
    private int plotNumber;
    private int deadPlants;
    // constructor method that builds 'Initial Configuration' UI
    public InitFarmUI(Player player) {
        // instantiates root pane for initConfig UI
        rootPane = new StackPane();
        buttons = new HBox();
        deadPlants = 0;
        // welcomes user by saying hello "playerName"
        Label welcomeName = new Label("hello, " + player.getName() + "!");
        sizeAndLocate(welcomeName, 300, 50, 55, 350, 20);
        welcomeName.setTextFill(Color.WHITE);
        // tells how much money player has
        Label money = new Label(Integer.toString(player.getBalance()));
        sizeAndLocate(money, 150, 32, 70, 19, 12);
        // tells current difficulty level
        Label diffLevel = new Label(player.getDifficulty());
        sizeAndLocate(diffLevel, 150, 50, 72, 74, 12);

        // tells current seed choice
        Label seedChoice = new Label(player.getInventory()[0]);
        sizeAndLocate(seedChoice, 150, 32, 268, 83, 12);

        // brings player back to welcome page
        Button back = new Button("Back");
        sizeAndLocate(back, 90, 28, 490, 548, 16);
        back.setOnAction(e -> {
            Welcome welcome = new Welcome();
            back.getScene().setRoot(welcome.getRootPane());
        });

        // will bring player back to welcome screen
        Button market = new Button("Market");
        MarketUI marketUI = new MarketUI(player);
        market.setOnAction(e -> {
            market.getScene().setRoot(marketUI.getRootPane());
        });
        sizeAndLocate(market, 90, 28, 608, 549, 16);

        Button invent = new Button("Inventory");
        InventoryUI inventoryUI = new InventoryUI(player);
        invent.setOnAction(e -> {
            invent.getScene().setRoot(inventoryUI.getRootPane());
        });
        sizeAndLocate(invent, 115, 28, 690, 550, 16);

        //adds rectangle to each box in gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 0, 470));
        grid.setVgap(2);
        grid.setHgap(2);

        int i = 0;
        int addRows = player.getLandRowNum();
        Text[] text = new Text[100];
        Button[] buttons1 = new Button[100];
        Button[] watering = new Button[100];
        Text[] waterLevels = new Text[100];
        Button[] fertilizing = new Button[100];
        for (int p = 0; p < 100; p++) {
            text[p] = new Text();
            buttons1[p] = new Button();
            text[p].setStyle("-fx-background-color: transparent;");
            text[p].setFont(Font.font("Stencil", 8));
            waterLevels[p] = new Text();
            waterLevels[p].setFont(Font.font("Stencil", 8));
            watering[p] = new Button();
            fertilizing[p] = new Button();
        }

        for (int row = 0; row < 5 + addRows; row++) {
            for (int col = 0; col < 10; col++) {
                if (addRows > 0 && row > 4) {
                    player.getPlot()[i] = "Empty";
                    if (row == player.getAdditionalLandRows() && player.getRowJustAdded()) {
                        text[i].setText(player.getPlot()[i]);
                        player.setRowJustAdded(false);
                    }
                }
                if (player.getPlot()[i].equals("Immature plant")) {
                    text[i] = new Text("Immature \n plant");
                } else if ((player.getPlot()[i].equals("Dead"))) {
                    player.setDeadPlants(player.getDeadPlants() + 1);
                } else if (player.getPlot()[i].equals("Mature plant")) {
                    text[i] = new Text("Mature \n plant");
                } else {
                    text[i] = new Text(player.getPlot()[i]);
                }
                waterLevels[i] = new Text(String.valueOf(player.getWaterLevels()[i]));
                if (player.getFertilizerLevels(i) == 0) {
                    fertilizing[i].setText("+");
                } else if (player.getFertilizerLevels(i) < 3) {
                    fertilizing[i].setText(String.valueOf(player.getFertilizerLevels(i)));
                } else {
                    fertilizing[i].setText("Max");
                }
                fertilizing[i].setFont(Font.font("Stencil", 6));
                Rectangle rec = new Rectangle();
                rec.setStroke(Color.BLACK);
                rec.setWidth(49);
                rec.setHeight(49);
                buttons1[i].setText(text[i].getText());
                buttons1[i].setFont(Font.font("Stencil", 6));
                buttons1[i].setTranslateY(-10);
                rec.setFill(Color.TRANSPARENT);
                //System.out.print(i);
                int finalI = i;
                int finalI1 = i;
                fertilizing[i].setOnAction(e -> {
                    player.fertilizeCrop(finalI);
                    if (player.getFertilizerLevels(finalI) == 0) {
                        fertilizing[finalI].setText("+");
                    } else if (player.getFertilizerLevels(finalI) < 3) {
                        fertilizing[finalI].setText(String.valueOf(player.getFertilizerLevels(finalI)));
                    } else {
                        fertilizing[finalI].setText("Max");
                    }
                });
                watering[i].setOnAction(e -> player.waterCrop(finalI, 1, "irrigate"));
                buttons1[i].setOnAction(e -> {
                    player.harvestCrop(finalI);
                    buttons1[finalI].setText(player.getPlot()[finalI]);
                });
                watering[i].setTranslateY(14);
                waterLevels[i].setTranslateY(14);
                fertilizing[i].setTranslateX(16);
                waterLevels[i].setTranslateY(16);
                StackPane stack = new StackPane();
                stack.getChildren().addAll(rec, buttons1[i],
                        watering[i], waterLevels[i], fertilizing[i]);
                GridPane.setColumnIndex(stack, col);
                GridPane.setRowIndex(stack, row);
                grid.getChildren().add(stack);
                i++;
            }
        }

        MenuButton pesticideButton = new MenuButton("Apply Pesticide");
        pesticideButton.setTranslateX(-150);
        pesticideButton.setTranslateY(105);
        pesticideButton.setFont(Font.font("Stencil", 12));
        MenuItem[] allInventoryItems = new MenuItem[player.getPlot().length];
        for (int g = 0; g < player.getPlot().length; g++) {
            allInventoryItems[g] = new MenuItem("Plot #" + String.valueOf(g));
        }
        pesticideButton.getItems().addAll(allInventoryItems);

        int ok = 0;
        for (final MenuItem item : pesticideButton.getItems()) {
            int finalOk = ok;
            item.setOnAction((event) -> {
                //System.out.println(finalOk);
                this.plotNumber = finalOk;
            });
            ok++;
        }

        Button plantSeedBtn = new Button("Apply Pesticide");
        plantSeedBtn.setTranslateX(-150);
        plantSeedBtn.setTranslateY(140);
        plantSeedBtn.setFont(Font.font("Stencil", 12));
        plantSeedBtn.setOnAction(e -> {
            player.applyPesticide(this.plotNumber);
        });

        Button advanceDay = new Button("Day " + String.valueOf(player.getDays())
                + ", " + player.getSeason() + " (+1 day)");
        advanceDay.setTranslateX(-40);
        advanceDay.setTranslateY(20);
        advanceDay.setFont(Font.font("Stencil", 12));
        //advanceDay.setStyle("-fx-background-color: transparent;");
        advanceDay.setOnAction(e -> player.advanceDay());
        HBox day = new HBox();
        day.getChildren().addAll(advanceDay);

        // adds nodes to hboxes
        buttons.getChildren().addAll(back, market, invent,  advanceDay);

        userName = new HBox();
        userName.getChildren().add(welcomeName);
        moneyDisplay = new HBox();
        moneyDisplay.getChildren().add(money);
        seasonDisplay = new HBox();
        diffDisplay = new HBox();
        diffDisplay.getChildren().add(diffLevel);
        seedTypeDisplay = new HBox();
        seedTypeDisplay.getChildren().add(seedChoice);
        gridBox = new HBox();
        gridBox.getChildren().add(grid);

        // sets up initial configuration background image
        backgroundImage = new Image("file:src/hi/img/FarmUI.gif", 1000, 600, false, false);
        iv = new ImageView();
        iv.setImage(backgroundImage);

        // overlays hboxes on top of background image
        rootPane.getChildren().addAll(iv, day, gridBox, userName, moneyDisplay, seasonDisplay,
                diffDisplay, seedTypeDisplay, buttons, pesticideButton, plantSeedBtn);

    }
    private void sizeAndLocateButton(int width, int height, int x, int y, int fontSize, Button button) {
        button.prefWidth(width);
        button.prefHeight(height);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setFont(Font.font("Stencil", fontSize));
    }

    // method to size and locate labels
    public void sizeAndLocate(Label label, int width, int height, int x, int y, int fontSize) {
        label.setTranslateX(x);
        label.setTranslateY(y);
        label.setMinWidth(width);
        label.setMinHeight(height);
        label.setStyle("-fx-background-color: transparent;");
        label.setFont(Font.font("Stencil", fontSize));
    }

    // method to size and locate labels
    public void sizeAndLocate(Button button, int width, int height, int x, int y, int fontSize) {
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setPrefWidth(width);
        button.setPrefHeight(height);
        button.setStyle("-fx-background-color: transparent;");
        button.setFont(Font.font("Stencil", fontSize));
    }
    public int getDeadPlants() {
        return deadPlants;
    }

    // getter method for initConfig root pane
    public Pane getRootPane() {
        return rootPane;
    }

}