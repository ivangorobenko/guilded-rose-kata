class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateItemQuality(items[i]);
            updateItemSellIn(items[i]);
        }

    }

    private void updateItemQuality(Item item) {

        if (isBackstagePasseItem(item)) {
            updateQualityForBackstageItem(item);
        } else if (isAgedBrieItem(item)) {
            increaseQualityForAgedItemBrie(item);
        } else if (isConjured(item)) {
            decreaseQualityForConjuredItem(item);
        } else if (!isSulfurasItem(item)) {
            decreaseQualityForGenericItem(item);
        }
    }

    private void decreaseQualityForConjuredItem(Item item) {
        decreaseQualityTwiceAsFastAsForGenericItem(item);
    }

    private void decreaseQualityTwiceAsFastAsForGenericItem(Item item) {
        for (int i = 0; i < 2; i++) {
            decreaseQualityForGenericItem(item);
        }
    }

    private void decreaseQualityForGenericItem(Item item) {
        if (itemQualityHasNotDroppedToZero(item)) {
            decreaseItemQuality(item);
            minusExtraOneFromQualityIfSaleHasFinished(item);
        }
    }

    private void updateQualityForBackstageItem(Item item) {
        if (maximumQualityItemNotAchieved(item)) {
            increaseItemQuality(item);
            addExtraOneToQualityIf10DaysBeforeTheConcert(item);
            addExtraOneToQualityIf5daysBeforeConcert(item);
        }
        dropQualityToZeroIfConcertHasPassed(item);
    }

    private void minusExtraOneFromQualityIfSaleHasFinished(Item item) {
        if (hasItemSaleFinished(item)) {
            decreaseItemQuality(item);
        }
    }

    private void dropQualityToZeroIfConcertHasPassed(Item item) {
        if (hasItemSaleFinished(item)) {
            decreaseItemQualityToZero(item);
        }
    }

    private void addExtraOneToQualityIf5daysBeforeConcert(Item item) {
        if (itemHasFiveDaysOrLessToGoForTheSale(item)) {
            increaseItemQuality(item);
        }
    }

    private void addExtraOneToQualityIf10DaysBeforeTheConcert(Item item) {
        if (itemHasTenDaysOrLessToGoForTheSale(item)) {
            increaseItemQuality(item);
        }
    }

    private void increaseQualityForAgedItemBrie(Item item) {
        if (maximumQualityItemNotAchieved(item)) {
            increaseItemQuality(item);
        }
    }

    private boolean itemQualityHasNotDroppedToZero(Item item) {
        return item.quality > 0;
    }

    private boolean maximumQualityItemNotAchieved(Item item) {
        return item.quality < 50;
    }

    private boolean hasItemSaleFinished(Item item) {
        return item.sellIn < 1;
    }

    private boolean itemHasTenDaysOrLessToGoForTheSale(Item item) {
        return item.sellIn < 11;
    }

    private boolean itemHasFiveDaysOrLessToGoForTheSale(Item item) {
        return item.sellIn < 6;
    }



    private boolean isBackstagePasseItem(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrieItem(Item item) {
        return item.name.equals("Aged Brie");
    }

    private boolean isConjured(Item item) {
        return item.name.equals("Conjured");
    }

    private boolean isSulfurasItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private void decreaseItemQuality(Item item) {
        changeItemQuality(item, -1);
    }

    private void increaseItemQuality(Item item) {
        changeItemQuality(item, 1);
    }

    private void decreaseItemQualityToZero(Item item) {
        changeItemQuality(item, -item.quality);
    }

    private void changeItemQuality(Item item, int value) {
        item.quality = item.quality + value;
    }

    private void updateItemSellIn(Item item) {
        if (!isSulfurasItem(item)) {
            decreaseItemSellIn(item);
        }
    }

    private void decreaseItemSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

}