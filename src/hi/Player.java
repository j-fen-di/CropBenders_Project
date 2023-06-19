package hi;

import javafx.scene.Scene;

import java.util.Random;

public class Player {
    private String name;
    private String seedType;
    private String season;
    private String difficulty;
    private String[] inventory;
    private Integer balance;
    private Double threshold;
    private Market shop;
    private Plot plot;
    private int matureSold;
    private int harvestToday;
    private int harvestLimit = 6;
    private int landRowNum;
    private int deadPlants;
    private int plotNumber;
    private boolean rowJustAdded;

    private String[] types = {"Mature plant", "Seed", "Immature plant", "carrot", "flower", "watermelon", "cabbage", "strawberry"};
    public Player(String name, String difficulty, String seedType, String season) {
        this.name = name;
        this.season = season;
        this.difficulty = difficulty;
        this.seedType = seedType;
        this.balance = setStartingMoney(difficulty);
        this.inventory = makeInventory(seedType);
        this.threshold = createThreshold(difficulty);
        this.shop = new Market(difficulty);
        this.plot = new Plot();
        this.matureSold = 0;
        this.harvestToday = 0;
        this.plotNumber = plot.getPlotNumber();
        this.rowJustAdded = false;
    }


    public double createThreshold(String diff) {
        double thresh = 0.0;
        if (diff.equals("hard")) {
            thresh = 0.35;
        }
        if (diff.equals("medium")) {
            thresh = 0.50;
        }
        if (diff.equals("easy")) {
            thresh = 0.80;
        }
        return thresh;
    }

    public void setInventory(String crop, int index) {
        String[] newone = this.getInventory();
        this.getInventory()[index] = crop;
        this.inventory = newone;
    }
    /*
    DONT TOUCH THIS
     */
    public int setStartingMoney(String difficulty) {
        if (difficulty.equals("easy")) {
            return 1000;
        } else if (difficulty.equals("medium")) {
            return 700;
        } else {
            return 300;
        }
    }
    /*
       DONT TOUCH THIS
   */
    public String[] makeInventory(String seedType) {
        String[] test = new String[8];
        for (int i = 0; i < 8; i++) {
            test[i] = seedType;
        }
        return test;
    }

    /*
    Insert the index of the inventory of which they are selling
    This will automatically decrease their balance by the amount
    and change the sold index to "Empty" to indicate nothing exists in that index
    */
    public void sellItem(int index) {
        if (index > inventory.length) {
            return;
        }
        if (this.inventory[index].equals("Empty")) {
            AlertBox.displayAlert("Error", "No item here to sell.");
            return;
        }
        String name = getInventory()[index];
        this.inventory[index] = "Empty";
        AlertBox.displayAlert("Sold!", "You just sold " + name);
        this.balance += shop.getSellingPrices(name);
        matureSold++;
    }
    /*
       Insert the name of the item that they are buying
       This will automatically increase their balance by the amount
       This will navigate for any indices that have an "Empty" value to replace
   */
    public void buyItem(String name) {
        int sellingPrice = shop.getSellingPrices(name);
        if (sellingPrice > this.balance) {
            AlertBox.displayAlert("Error", "Not enough money.");
            return;
        }
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == "Empty" || name == "land") {
                inventory[i] = name;
                this.balance -= sellingPrice;
                AlertBox.displayAlert("Purchased!", "You just purchased " + name);
                if (name == "irrigation") {
                    plot.setWaterLimit(10);
                    AlertBox.displayAlert("Irrigation", "This increased the maximum water level for each day");
                }
                if (name == "tractor") {
                    this.harvestLimit = 8;
                    AlertBox.displayAlert("tractor", "This increased the number of harvests per day.");
                }
                if (name == "land") {
                    addLand();
                    AlertBox.displayAlert("land", "This added 10 plots (1 plot row) to your farm.");
                    inventory[i] = "Empty";
                }
                return;
            }
        }
        AlertBox.displayAlert("Error", "No room to buy.");
        return;
    }

    /**
     * Buy item method without AlertBox
     * @param name item name
     */
    public void buyItemTester(String name) {
        int sellingPrice = shop.getSellingPrices(name);
        if (sellingPrice > this.balance) {
            return;
        }
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == "Empty" || name == "land") {
                inventory[i] = name;
                this.balance -= sellingPrice;
                if (name == "irrigation") {
                    plot.setWaterLimit(10);
                }
                if (name == "tractor") {
                    this.harvestLimit = 8;
                }
                if (name == "land") {
                    addLand();
                    inventory[i] = "Empty";
                }
                return;
            }
        }
        return;
    }
    /*
         Insert the index of the item that they are harvesting
         This will automatically check if it is a mature plant & will add to
         inventory if they have an empty space
     */
    public void harvestCrop(int index) {
        if (harvestToday >= harvestLimit) {
            AlertBox.displayAlert("Error", "You have exceeded the amount you can harvest today. Wait until tomorrow.");
            return;
        }
        harvestToday++;
        if (plot.getPlot()[index].equals("Empty")) {
            AlertBox.displayAlert("Error", "No crop here to harvest.");
            return;
        }
        String[] newone = plot.getPlot();
        boolean checkRoom = false;
        int emptySpace = 0;
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i].equals("Empty")) {
                checkRoom = true;
                emptySpace = i;
            }
        }
        if (!checkRoom) {
            AlertBox.displayAlert("Error", "Not enough room to harvest crop.");
            return;
        }
        if (plot.getPlot()[index].equals("Dead Plant")) {
            plot.getPlot()[index] = "Empty";
        }
        if (plot.getPlot()[index].equals("Mature plant")) {
            this.setInventory("Mature Plant", emptySpace);
            if (plot.getFertilizerLevel(index) > 0) {
                int extraYield = new Random().nextBoolean() ? 1 : 2;
                int extra = 0;
                for (int q = 0; q < inventory.length && extra < extraYield; q++) {
                    if (inventory[q].equals("Empty")) {
                        this.setInventory("Mature Plant", q);
                        extra++;
                    }
                }
            }
            plot.getPlot()[index] = "Empty";
        } else if (plot.getPlot()[index].equals("Mature plant+P")) {
            this.setInventory("Mature Plant+P", emptySpace);
            plot.getPlot()[index] = "Empty";
        } else {
            AlertBox.displayAlert("Error", "Cannot harvest plot.");
        }

    }

    public void plantSpecificPlot(int inventoryIndex, int plotIndex) {
        if (plotIndex >= (plot.getPlotNumber() + getAdditionalLandRows() * 10)) {
            AlertBox.displayAlert("Error", "You have not purchased this plot yet.");
            return;
        }
        if (!this.plot.getPlot()[plotIndex].equals("Empty")) {
            AlertBox.displayAlert("Error", "This plot is not empty.");
            return;
        }
        if (this.inventory[inventoryIndex].contains("Plant")
                || this.inventory[inventoryIndex] == ("Empty")) {
            AlertBox.displayAlert("Error",
                    "There isn't a seed or this spot of your inventory may be empty.");
            return;
        }

        this.plot.setPlot(this.plot.getPlot(), plotIndex,
                this.inventory[inventoryIndex] + " Seed");
        this.inventory[inventoryIndex] = "Empty";

    }

    public void fertilizeCrop(int index) {
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i].equals("fertilizer")) {
                if (plot.getFertilizerLevel(index) == 3) {
                    AlertBox.displayAlert("Error", "This plot has maximum fertilizer levels.");
                    return;
                }
                plot.fertilize(index);
                AlertBox.displayAlert("Fertilized", "You just fertilized a plot.");
                this.inventory[i] = "Empty";
                return;
            }
        }
        AlertBox.displayAlert("Error", "No fertilizer found in inventory.");
        return;
    }
    public int getFertilizerLevels(int index) {
        return plot.getFertilizerLevel(index);
    }

    public void applyPesticide(int plotIndex) {
        boolean hasPesticide = false;
        int counter = 0;
        for (String i : this.getInventory()) {
            if (i.equals("pesticide")) {
                hasPesticide = true;
                this.inventory[counter] = "Empty";
                break;
            }
            counter++;
        }
        if (!hasPesticide) {
            AlertBox.displayAlert("No Pesticides!", "Please purchase it first.");
            return;
        }
        plot.setPlot(this.getPlot(), plotIndex, this.getPlot()[plotIndex] + "+P");
    }

    public void occurRandomEvent() {
        double[] prob = new double[3];
        for (int j = 0; j < prob.length; j++) {
            Random rand = new Random();
            prob[j] = rand.nextDouble();
        }
        double rain = prob[0];
        double drought = prob[1];
        double locust = prob[2];;
        String eventOccured = "";
        boolean findEvent = true;
        while (findEvent) {
            if (rain >= threshold) {
                Random rand = new Random();
                int randomWaterLvl = rand.nextInt(10);
                for (int k = 0; k < plot.getPlot().length; k++) {
                    this.waterCrop(k, randomWaterLvl, "water");
                }
                AlertBox.displayAlert("Rain!", "You watered all plots by " + randomWaterLvl);
                findEvent = true;
                break;
            } else if (drought >= threshold) {
                Random rand = new Random();
                int randomWaterLvl = rand.nextInt(10);
                for (int k = 0; k < plot.getPlot().length; k++) {

                    this.waterCrop(k, randomWaterLvl, "drought");
                }
                findEvent = true;
                AlertBox.displayAlert("Drought!", "This decreased water levels by " + randomWaterLvl);
                break;
            } else if (locust >= threshold) {
                int plantsDead = 0;
                int chance = 0;
                for (int plot = 0; plot < this.getPlot().length; plot++) {
                    Random rand = new Random();
                    chance = rand.nextInt(6);
                    if (this.getPlot()[plot].contains("+P")) {
                        continue;
                    } else if (difficulty.equals("hard")) {
                        if (chance <= 5) {
                            this.plot.setPlot(this.getPlot(), plot, "Dead");
                            plantsDead += 1;
                        }
                    } else if (difficulty.equals("medium")) {
                        if (chance <= 3) {
                            this.plot.setPlot(this.getPlot(), plot, "Dead");
                            plantsDead += 1;
                        }
                    } else {
                        if (chance <= 2) {
                            this.plot.setPlot(this.getPlot(), plot, "Dead");
                            plantsDead += 1;
                        }
                    }
                }
                findEvent = true;
                AlertBox.displayAlert("Locust!", "You killed " + String.valueOf(plantsDead) + " plants.");
                break;

            } else {
                findEvent = false;
            }
        }
    }

    public void addLand() {
        plot.addLand();
        landRowNum = plot.getAdditionalLandRows();
        rowJustAdded = true;

    }

    public void waterCrop(int index, int amt, String type) {
        this.plot.waterPlot(index, amt, type);
        return;
    }

    public void advanceDay() {
        this.plot.advanceADay();
        this.occurRandomEvent();
        this.harvestToday = 0;
    }

    public int[] getWaterLevels() {
        return this.plot.getWaterLevels();
    }

    public String[] getMarketItems() {
        return shop.getMarketItems();
    }

    public int getHarvestToday() {
        return this.harvestToday;
    }
    public int getMatureSold() {
        return this.matureSold;
    }

    public String[] getPlot() {
        return this.plot.getPlot();
    }

    public Plot getPlotGrid() { return this.plot; }

    public int getLandRowNum() {
        return this.landRowNum;
    }

    public String getName() {
        return this.name;
    }

    public int getDays() {
        return this.plot.getDay();
    }

    public String getSeason() {
        return this.season;
    }

    public String getDifficulty() {
        return this.difficulty;
    }
    /*
    Get user balance
     */
    public int getBalance() {
        return this.balance;
    }
    /*  Get user inventory by looping through its contents for (String i : getInventory())
     */
    public String[] getInventory() {
        return this.inventory;
    }
    public void setDeadPlants(int i) {
        deadPlants = i;
    }
    public int getDeadPlants() {
        return deadPlants;
    }
    public int getPlotNumber() {
        return plotNumber;
    }
    public int getAdditionalLandRows() {
        return plot.getAdditionalLandRows();
    }
    public boolean getRowJustAdded() {
        return rowJustAdded;
    }
    public void setRowJustAdded(boolean bool) {
        rowJustAdded = bool;
    }

}