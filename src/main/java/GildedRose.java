class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateItemQuality(items[i]);
        }
    }


    private boolean itemQualityHasNotDroppedToZero(Item item){
        return item.quality > 0;
    }
    private boolean maximumQualityItemNotAchieved(Item item){
        return item.quality < 50;
    }

    private boolean hasItemSaleFinished(Item item){
        return item.sellIn<0;
    }

    private boolean itemHasTenDaysOrLessToGoForTheSale(Item item){
        return item.sellIn<11;
    }

    private boolean itemHasFiveDaysOrLessToGoForTheSale(Item item){
        return item.sellIn<6;
    }

    private boolean isSulfurasItem(Item item) {
        return item.name.equals("Sulfuras, Hand of Ragnaros");
    }

    private boolean isBackstagePasseItem(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }

    private boolean isAgedBrieItem(Item item) {
        return item.name.equals("Aged Brie");
    }

    private void decreaseItemSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void decreaseItemQuality(Item item){
        changeItemQuality(item,-1);
    }

    private void increaseItemQuality(Item item){
        changeItemQuality(item,1);
    }

    private void decreaseItemQualityToZero(Item item){
        changeItemQuality(item,-item.quality);
    }
    private void changeItemQuality(Item item, int value){
        item.quality = item.quality + value;
    }

    private void updateItemQuality(Item item){

        if (isBackstagePasseItem(item)) {
            updateQualityForBackstageItem(item);
        }
        updateItemSellIn(item);

        if (!isAgedBrieItem(item)
                && !isBackstagePasseItem(item)) {
            if (itemQualityHasNotDroppedToZero(item)) {
                if (!isSulfurasItem(item)) {
                    decreaseItemQuality(item);
                }
            }
        } else {
            if (maximumQualityItemNotAchieved(item)) {
                increaseItemQuality(item);
            }
        }

        if (hasItemSaleFinished(item)) {
            updateItemQualityIfSaleHasFinished(item);
        }
    }

    private void updateItemSellIn(Item item) {
        if (!isSulfurasItem(item)) {
            decreaseItemSellIn(item);
        }
    }

    private void updateItemQualityIfSaleHasFinished(Item item) {
        if (!isAgedBrieItem(item)) {
            if (!isBackstagePasseItem(item)) {
                if (itemQualityHasNotDroppedToZero(item)) {
                    if (!isSulfurasItem(item)) {
                        decreaseItemQuality(item);
                    }
                }
            } else {
                decreaseItemQualityToZero(item);
            }
        } else {
            if (maximumQualityItemNotAchieved(item)) {
                increaseItemQuality(item);
            }
        }
    }

    private void updateQualityForBackstageItem(Item item) {
        if (maximumQualityItemNotAchieved(item)) {
            if (itemHasTenDaysOrLessToGoForTheSale(item)) {
                increaseItemQuality(item);
            }
            if (itemHasFiveDaysOrLessToGoForTheSale(item)) {
                increaseItemQuality(item);
            }
        }
    }
}