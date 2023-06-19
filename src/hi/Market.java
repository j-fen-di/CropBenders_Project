package hi;

import java.util.Random;

public class Market {
    private String[] sellingItems;
    private String difficulty;
    public Market(String difficulty) {
        this.sellingItems = makeSellItems();
        this.difficulty = difficulty;
    }


    public String[] makeSellItems() {
        String[] test = {"carrot", "flower", "watermelon", "cabbage", "strawberry",
                "fertilizer", "pesticide", "irrigation", "tractor", "land"};
        return test;
    }
    /*
    Get contents of the market's selling items
     */
    public String[] getMarketItems() {
        return this.sellingItems;
    }
    /*
        Insert the name of the selling item and it will return a price
        You will need this to display on UI only.
     */
    public int getSellingPrices(String item) {
        int variance = variance();
        if (item.equals("carrot")) {
            return 300 + variance;
        }
        if (item.equals("tractor")) {
            return 400 + variance;
        }
        if (item.equals("irrigation")) {
            return 600 + variance;
        }
        if (item.equals("land")) {
            return 300;
        }
        if (item.equals("flower")) {
            return 400 + variance;
        }
        if (item.equals("cabbage")) {
            return 500 + variance;
        }
        if (item.equals("strawberry")) {
            return 1000 + variance;
        }
        if (item.equals("fertilizer")) {
            return 150 + variance;
        }
        if (item.contains("pesticide")) {
            return 200 + variance;
        }
        if (item.equals("Mature Plant+P") || item.equals("Mature plant+P") || item.contains("+P")) {
            return 150 + variance;
        } else {
            return 400 + variance;
        }
    }

    // gives variance of +/- 10 coins
    private int variance() {
        long seed = 582495029;
        int variance = 10;
        Random rand = new Random(seed);
        /*
        if (difficulty.equals("easy")) {
            //variance = rand.nextInt(15) - 10;
        */
        if (difficulty.equals("medium")) {
            //variance = rand.nextInt(30) - 10;
            variance += 40;
        } else if (difficulty.equals("hard")) {
            //variance = rand.nextInt(50) - 10;
            variance += 70;
        }
        return variance;
    }
}