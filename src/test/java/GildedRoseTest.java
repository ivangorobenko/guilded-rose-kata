import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void shouldLowerQualityAndSellInWithOneUnitAfterOneDay() {

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("+5 Dexterity Vest", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(19, app.items[0].quality);

    }


    @Test
    void shouldLowerQualityTwiceAsFastOnceTheSellDatePassed() {

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        int days = 12;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals("+5 Dexterity Vest", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void qualityOfAnItemShouldNeverBeNegatif() {

        Item[] items = new Item[]{new Item("+5 Dexterity Vest", 10, 20)};
        GildedRose app = new GildedRose(items);
        int days = 22;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void shouldIncreaseQualityForAgedBrie() {

        Item[] items = new Item[]{new Item("Aged Brie", 10, 10)};
        GildedRose app = new GildedRose(items);
        int days = 2;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(8, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);
    }

    @Test
    void qualityOfAnItemShouldNeverBeMoreThan50() {

        Item[] items = new Item[]{new Item("Aged Brie", 10, 40)};
        GildedRose app = new GildedRose(items);
        int days = 30;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void shouldNotDecreaseSellInForSulfurasItem() {

        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", 10, 40)};
        GildedRose app = new GildedRose(items);
        int days = 20;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(10, app.items[0].sellIn);
        assertEquals(40, app.items[0].quality);
    }

    @Test
    void shouldSellInShouldRemainAsIsForSulfurasItemWhenNotForSale() {

        Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", -1, 40)};
        GildedRose app = new GildedRose(items);
        int days = 20;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(-1, app.items[0].sellIn);
    }

    @Test
    void shouldIncreaseQualityByOneForBackstagePassesIfMoreThan10daysBeforeConcert() {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20)};
        GildedRose app = new GildedRose(items);
        int days = 4;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(24, app.items[0].quality);
    }

    @Test
    void shouldIncreaseQualityByTwoForBackstagePassesBetween5and10daysBeforeConcert() {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20)};
        GildedRose app = new GildedRose(items);
        int days = 12;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(34, app.items[0].quality);
    }


    @Test
    void shouldIncreaseQualityByThreeForBackstagePassesFor5daysAndLessBeforeConcert() {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 20, 20)};
        GildedRose app = new GildedRose(items);
        int days = 17;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(46, app.items[0].quality);
    }

    @Test
    void shouldIncreaseQualityByThreeForBackstagePassesFor5daysAndLessBeforeConcertCaseDayOfTheConcert() {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);
        int days = 5;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(35, app.items[0].quality);
    }

    @Test
    void qualityBeZeroForBackstagePassesAfterConcert() {

        Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);
        int days = 6;
        for (int i = 1; i<=days; i++){
            app.updateQuality();
        }
        assertEquals(0, app.items[0].quality);
    }


}
