package hi;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InventoryUI implements Resizing {
    private final Pane rootPane;
    private Player player;
    private Image backgroundImage;
    private ImageView iv;
    private int length;
    private int plotText = 0;
    private int inventoryText = 0;


    public InventoryUI(Player play) {
        this.player = play;
        rootPane = new StackPane();
        length = 100;

        Label i1;
        Label i2;
        Label i3;
        Label i4;
        Label i5;
        Label i6;
        Label i7;
        Label i8;

        Button back = new Button("Back");
        back.prefWidth(90);
        back.prefHeight(28);
        back.setTranslateX(40);
        back.setTranslateY(19);
        back.setStyle("-fx-background-color: transparent;");
        back.setFont(Font.font("Stencil", 16));
        back.setOnAction(e -> {
            InitFarmUI init = new InitFarmUI(player);
            back.getScene().setRoot(init.getRootPane());
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(250, 0, 22, 136));
        grid.setVgap(2);
        grid.setHgap(2);
        int i = 0;
        for (int col = 0; col < 8; col++) {
            Text text = new Text(player.getInventory()[i]);
            text.setStyle("-fx-background-color: transparent;");
            text.setFont(Font.font("Stencil", 12));
            Rectangle rec = new Rectangle();
            rec.setStroke(Color.BLACK);
            rec.setWidth(89);
            rec.setHeight(89);
            rec.setFill(Color.TRANSPARENT);
            StackPane stack = new StackPane();
            stack.getChildren().addAll(rec, text);
            GridPane.setColumnIndex(stack, col);
            grid.getChildren().add(stack);
            i++;
        }

        Button plantSeed = new Button("Plant Seed");
        plantSeed.setOnAction(e -> player.plantSpecificPlot(2, 0));

        HBox plantSeedBox = new HBox();
        plantSeedBox.getChildren().addAll(plantSeed);

        HBox gridBox = new HBox();
        gridBox.getChildren().add(grid);

        MenuButton menuButton = new MenuButton("Choose a Plot");
        menuButton.setTranslateX(750);
        menuButton.setFont(Font.font("Stencil", 12));
        MenuItem[] allPlots = new MenuItem[player.getPlotNumber() + (player.getAdditionalLandRows() * 10)];
        for (int g = 0; g < allPlots.length; g++) {
            if (player.getPlot()[g] == "Empty") {
                allPlots[g] = new MenuItem("Empty: Plot #" + String.valueOf(g));
            } else {
                allPlots[g] = new MenuItem("Occupied: Plot #" + String.valueOf(g));
            }
        }
        menuButton.getItems().addAll(allPlots);

        int plotItems = 0;
        for (final MenuItem item : menuButton.getItems()) {
            int finalPlotItems = plotItems;
            item.setOnAction((event) -> {
                this.plotText = finalPlotItems;
            });
            plotItems++;
        }

        MenuButton inventoryButton = new MenuButton("Choose Inventory Space");
        inventoryButton.setTranslateX(750);
        inventoryButton.setFont(Font.font("Stencil", 12));
        MenuItem[] allInventoryItems = new MenuItem[player.getInventory().length];
        for (int g = 0; g < player.getInventory().length; g++) {
            allInventoryItems[g] = new MenuItem("Item " + String.valueOf(player.getInventory()[g]));
        }

        inventoryButton.getItems().addAll(allInventoryItems);

        int ok = 0;
        for (final MenuItem item : inventoryButton.getItems()) {
            int finalOk = ok;
            System.out.println(item);
            item.setOnAction((event) -> {
                System.out.println(finalOk);
                this.inventoryText = finalOk;
            });
            ok++;
        }

        Button plantSeedBtn = new Button("Plant Seed");
        plantSeedBtn.setTranslateX(750);
        plantSeedBtn.setFont(Font.font("Stencil", 12));
        plantSeedBtn.setOnAction(e -> {
            System.out.println(this.plotText);
            System.out.println(this.inventoryText);
            player.plantSpecificPlot(Integer.valueOf(this.inventoryText),
                    Integer.valueOf(this.plotText));
        });

        VBox chooseSpecificPlot = new VBox();
        chooseSpecificPlot.setTranslateY(100);
        chooseSpecificPlot.getChildren().addAll(inventoryButton, menuButton, plantSeedBtn);

        // sets up initial configuration background image
        backgroundImage = new Image("file:src/hi/img/Inventory.gif", 1000, 600, false, false);
        iv = new ImageView();
        iv.setImage(backgroundImage);

        HBox buttons = new HBox();
        buttons.getChildren().addAll(back);
        rootPane.getChildren().addAll(iv, gridBox, buttons, chooseSpecificPlot);

    }
    public Pane getRootPane() {
        return rootPane;
    }

}