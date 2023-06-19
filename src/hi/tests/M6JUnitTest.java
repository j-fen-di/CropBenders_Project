package hi.tests;
import hi.Market;
import hi.Plot;
import hi.Player;
import org.junit.Test;
import static org.junit.Assert.*;

public class M6JUnitTest {
    @Test
    public void testIrrigation() {
        Player player = new Player("John Doe", "easy", "carrot", "spring");
        player.buyItemTester("irrigation");
        Plot plot = player.getPlotGrid();
        int expLimit = plot.getWaterLimit();
        assertEquals((long) 6, (long) expLimit);
    }

    @Test
    public void testTractorPrice() {
        String[] difficulty = {"easy", "medium", "hard"};
        int[] prices = {410, 450, 480};
        for (int i = 0; i < difficulty.length; i++) {
            Market market = new Market(difficulty[i]);
            int experimentalPrice = market.getSellingPrices("tractor");
            assertEquals(experimentalPrice, prices[i]);
        }
    }
    @Test
    public void testIrrigationPrice() {
        String[] difficulty = {"easy", "medium", "hard"};
        int[] prices = {610, 650, 680};
        for (int i = 0; i < difficulty.length; i++) {
            Market market = new Market(difficulty[i]);
            int experimentalPrice = market.getSellingPrices("irrigation");
            assertEquals(experimentalPrice, prices[i]);
        }
    }
    @Test
    public void testPlotIncrease() {
        Plot plot = new Plot();
        plot.addLand();
        int experimentalPlotNum = plot.getAdditionalLandRows();
        assertEquals((long) 1, (long) experimentalPlotNum);
    }
    @Test
    public void testLandIncreaseLimit() {
        Plot plot = new Plot();
        plot.setAdditionalLandRows(5);
        plot.addLandTester();
        int experimentalPlotNum = plot.getAdditionalLandRows();
        assertEquals((long) 5, (long) experimentalPlotNum);
    }
}
