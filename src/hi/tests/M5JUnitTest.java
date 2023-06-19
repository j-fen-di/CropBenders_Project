package hi.tests;
import hi.Market;
import hi.Plot;
import org.junit.Test;
import static org.junit.Assert.*;

public class M5JUnitTest {
    @Test
    public void testMaxFertilizerPerPlot() {
        Plot plot = new Plot();
        for (int i = 0; i < 5; i++) {
            plot.fertilize(21);
        }
        int experimentalFertLevel = plot.getFertilizerLevel(21);
        assertEquals((long) experimentalFertLevel, 3);
    }
    @Test
    public void testFertilizerDecrease() {
        // do plots 21 (fertilizer level 3) and 42 (fertilizer level 0)
        Plot plot = new Plot();
        for (int i = 0; i < 5; i++) {
            plot.fertilize(21);
        }
        plot.advanceADay();
        int experimentalFertLevel21 = plot.getFertilizerLevel(21);
        int experimentalFertLevel42 = plot.getFertilizerLevel(42);
        assertEquals((long) experimentalFertLevel21, 2);
        assertEquals((long) experimentalFertLevel42, 0);
    }
    @Test
    public void testFertilizerPrice() {
        String[] difficulty = {"easy", "medium", "hard"};
        int[] prices = {160, 200, 230};
        for (int i = 0; i < difficulty.length; i++) {
            Market market = new Market(difficulty[i]);
            int experimentalPrice = market.getSellingPrices("fertilizer");
            assertEquals((long) experimentalPrice, (long) prices[i]);
        }
    }
    @Test
    public void testPesticidePrice() {
        String[] difficulty = {"easy", "medium", "hard"};
        int[] prices = {210, 250, 280};
        for (int i = 0; i < difficulty.length; i++) {
            Market market = new Market(difficulty[i]);
            int experimentalPrice = market.getSellingPrices("pesticide");
            assertEquals((long) experimentalPrice, (long) prices[i]);
        }
    }
    @Test
    public void testPesticideProtection() {
        String[] difficulty = {"easy", "medium", "hard"};
        int[] prices = {210, 250, 280};
        for (int i = 0; i < difficulty.length; i++) {
            Market market = new Market(difficulty[i]);
            int experimentalPrice = market.getSellingPrices("pesticide");
            assertEquals((long) experimentalPrice, (long) prices[i]);
        }
    }
}