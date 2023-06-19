package hi;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MarketUI {
    private final Pane rootPane;
    private Player player;
    private Image backgroundImage;
    private ImageView iv;

    public MarketUI(Player play) {
        this.player = play;
        rootPane = new StackPane();

        Button back = new Button("Back");
        sizeAndLocateButton(90, 28, 40, 0, 16, back);
        back.setOnAction(e -> {
            InitFarmUI initFarm = new InitFarmUI(player);
            back.getScene().setRoot(initFarm.getRootPane());
        });

        Market market = new Market(player.getDifficulty());

        Label carrotPrice = new Label("Carrot | " + market.getSellingPrices("carrot"));
        carrotPrice.setTranslateX(608);
        carrotPrice.setTranslateY(400);
        carrotPrice.setFont(Font.font("Stencil", 12));
        Button cell1 = new Button("Buy Carrot");
        sizeAndLocateButton(40, 28, 650, 180, 12, cell1);
        cell1.setOnAction(e -> player.buyItem("carrot"));

        Label strawberryPrice = new Label("Strawberry | " + market.getSellingPrices("strawberry"));
        strawberryPrice.setTranslateX(320);
        strawberryPrice.setTranslateY(470);
        strawberryPrice.setFont(Font.font("Stencil", 12));
        Button cell2 = new Button("Buy Strawberry");
        sizeAndLocateButton(90, 28, 650, 181, 12, cell2);
        cell2.setOnAction(e -> player.buyItem("strawberry"));

        Label flowerPrice = new Label("Flower | " + market.getSellingPrices("flower"));
        flowerPrice.setTranslateX(320);
        flowerPrice.setTranslateY(400);
        flowerPrice.setFont(Font.font("Stencil", 12));
        Button cell3 = new Button("Buy Flower");
        sizeAndLocateButton(90, 28, 650, 182, 12, cell3);
        cell3.setOnAction(e -> player.buyItem("flower"));

        Label cabbagePrice = new Label("Cabbage | " + market.getSellingPrices("cabbage"));
        cabbagePrice.setTranslateX(320);
        cabbagePrice.setTranslateY(540);
        cabbagePrice.setFont(Font.font("Stencil", 12));
        Button cell4 = new Button("Buy Cabbage");
        sizeAndLocateButton(40, 28, 650, 183, 12, cell4);
        cell4.setOnAction(e -> player.buyItem("cabbage"));

        Label fertPrice = new Label("Fertilizer | " + market.getSellingPrices("fertilizer"));
        fertPrice.setTranslateX(320);
        fertPrice.setTranslateY(340);
        fertPrice.setFont(Font.font("Stencil", 12));
        Button cell5 = new Button("Buy Fertilizer");
        sizeAndLocateButton(40, 28, 650, 185, 12, cell5);
        cell5.setOnAction(e -> player.buyItem("fertilizer"));

        Label pestPrice = new Label("Pesticide | " + market.getSellingPrices("pesticide"));
        pestPrice.setTranslateX(320);
        pestPrice.setTranslateY(360);
        pestPrice.setFont(Font.font("Stencil", 12));
        Button cell6 = new Button("Buy Pesticide");
        sizeAndLocateButton(40, 28, 542, 162, 12, cell6);
        cell6.setOnAction(e -> player.buyItem("pesticide"));

        Label irrigationPrice = new Label("Irrigation | " + market.getSellingPrices("irrigation"));
        irrigationPrice.setTranslateX(320);
        irrigationPrice.setTranslateY(360);
        irrigationPrice.setFont(Font.font("Stencil", 12));
        Button cell7 = new Button("Buy Irrigation");
        sizeAndLocateButton(40, 28, 810, -10, 12, cell7);
        cell7.setOnAction(e -> player.buyItem("irrigation"));

        Label tractorPrice = new Label("Tractor | " + market.getSellingPrices("tractor"));
        tractorPrice.setTranslateX(320);
        tractorPrice.setTranslateY(360);
        tractorPrice.setFont(Font.font("Stencil", 12));
        Button cell8 = new Button("Buy tractor");
        sizeAndLocateButton(40, 28, 810, -60, 12, cell8);
        cell8.setOnAction(e -> player.buyItem("tractor"));

        Label plotPrice = new Label("+10 Plots | " + market.getSellingPrices("land"));
        plotPrice.setTranslateX(400);
        plotPrice.setTranslateY(420);
        plotPrice.setFont(Font.font("Stencil", 12));
        Button cell9 = new Button("Buy +10 Plots");
        sizeAndLocateButton(40, 28, 810, -30, 12, cell9);
        cell9.setOnAction(e -> player.buyItem("land"));

        HBox cell1Box = new HBox();
        cell1Box.getChildren().addAll(cell1);
        HBox carrotPricee = new HBox();
        carrotPricee.getChildren().addAll(carrotPrice);
        HBox cell2Box = new HBox();
        cell2Box.getChildren().addAll(cell2);
        HBox strawberryLabel = new HBox();
        strawberryLabel.getChildren().addAll(strawberryPrice);
        HBox flowerPriceLabel = new HBox();
        flowerPriceLabel.getChildren().addAll(flowerPrice);
        HBox cabbageLabel = new HBox();
        cabbageLabel.getChildren().addAll(cabbagePrice);
        HBox fertLabel = new HBox();
        fertLabel.getChildren().addAll(fertPrice);
        HBox pestLabel = new HBox();
        pestLabel.getChildren().addAll(pestPrice);
        HBox irrigationLabel = new HBox();
        irrigationLabel.getChildren().addAll(irrigationPrice);
        HBox tractorLabel = new HBox();
        tractorLabel.getChildren().addAll(tractorPrice);

        Label balance = new Label(String.valueOf(player.getBalance()));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(125, 20, 3, 270));
        grid.setVgap(1);
        grid.setHgap(1);
        Button[] buttons1 = new Button[100];

        HBox box = new HBox();

        Label balanceAmt;
        final int[] i = {0};

        grid.setPadding(new Insets(125, 20, 3, 270));
        grid.setVgap(1);
        grid.setHgap(1);

        for (int col = 0; col < 8; col++) {
            Button button = new Button(player.getInventory()[i[0]]);
            button.setStyle("-fx-background-color: transparent;");
            button.setFont(Font.font("Stencil", 12));
            int finalI = i[0];
            Rectangle rec = new Rectangle();
            rec.setStroke(Color.BLACK);
            rec.setWidth(65);
            rec.setHeight(65);
            rec.setFill(Color.TRANSPARENT);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(rec, button);
            button.setOnAction(e -> {
                InitFarmUI init = new InitFarmUI(player);
                button.getScene().setRoot(init.getRootPane());
            });
            GridPane.setColumnIndex(stack, col);
            grid.getChildren().add(stack);
            i[0]++;
        }

        balanceAmt = new Label("Balance $" + String.valueOf(player.getBalance()));
        balanceAmt.setFont(Font.font("Stencil", 12));
        box.getChildren().addAll(balanceAmt);
        box.setTranslateY(13);
        box.setTranslateX(900);

        HBox gridBox = new HBox();
        gridBox.getChildren().add(grid);

        // sets up initial configuration background image
        backgroundImage = new Image("file:src/hi/img/Market.gif", 1000, 600, false, false);
        iv = new ImageView();
        iv.setImage(backgroundImage);

        Button sell1 = new Button("Sell Item 1");
        sizeAndLocateButton(90, 28, 470, 372, 12, sell1);
        sell1.setOnAction(e -> player.sellItem(0));
        Button sell2 = new Button("Sell Item 2");
        sizeAndLocateButton(90, 28, 560, 348, 12, sell2);
        sell2.setOnAction(e -> player.sellItem(1));
        Button sell3 = new Button("Sell Item 3");
        sizeAndLocateButton(90, 28, 470, 349, 12, sell3);
        sell3.setOnAction(e -> player.sellItem(2));
        Button sell4 = new Button("Sell Item 4");
        sizeAndLocateButton(90, 28, 560, 325, 12, sell4);
        sell4.setOnAction(e -> player.sellItem(3));
        Button sell5 = new Button("Sell Item 5");
        sizeAndLocateButton(90, 28, 470, 326, 12, sell5);
        sell5.setOnAction(e -> player.sellItem(4));
        Button sell6 = new Button("Sell Item 6");
        sizeAndLocateButton(90, 28, 560, 302, 12, sell6);
        sell6.setOnAction(e -> player.sellItem(5));
        Button sell7 = new Button("Sell Item 7");
        sizeAndLocateButton(90, 28, 470, 303, 12, sell7);
        sell7.setOnAction(e -> player.sellItem(6));
        Button sell8 = new Button("Sell Item 8");
        sizeAndLocateButton(90, 28, 560, 279, 12, sell8);
        sell8.setOnAction(e -> player.sellItem(7));

        VBox buttons = new VBox();
        buttons.getChildren().addAll(back);

        VBox allbuttons = new VBox();
        allbuttons.setTranslateY(50);
        allbuttons.getChildren().addAll(back, sell1, sell2, sell3, sell4, sell5, sell6,
                sell7, sell8, cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9);

        rootPane.getChildren().addAll(iv, gridBox, box, carrotPricee, strawberryLabel,
                flowerPriceLabel, cabbageLabel, fertLabel, pestLabel, buttons, allbuttons);
        if (player.getMatureSold() == 100 || player.getBalance() == 10000) {
            Button winner = new Button("You won!");
            WinUI win = new WinUI(player);
            winner.setOnAction(e -> winner.getScene().setRoot(win.getRootPane()));
            sizeAndLocateButton(200, 200, 50, 50, 70, winner);
            rootPane.getChildren().addAll(winner);
        }
        if (player.getBalance() == 0 && player.getDeadPlants() > 99) {
            Button loser = new Button("You Lose");
            LoseUI lose = new LoseUI(player);
            loser.setOnAction(e -> loser.getScene().setRoot(lose.getRootPane()));
            sizeAndLocateButton(200, 200, 50, 50, 70, loser);
            rootPane.getChildren().addAll(loser);
        }
    }

    // method to size buttons
    private void sizeAndLocateButton(int width, int height, int x, int y,
                                     int fontSize, Button button) {
        button.prefWidth(width);
        button.prefHeight(height);
        button.setTranslateX(x);
        button.setTranslateY(y);
        button.setFont(Font.font("Stencil", fontSize));
    }

    public Pane getRootPane() {
        return rootPane;
    }

}