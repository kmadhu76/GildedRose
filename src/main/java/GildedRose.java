import java.util.ArrayList;
import java.util.List;


public class GildedRose {



    /**
     * @param args
     */
    public static void main(String[] args) {

        GildedRose gildedRose = new GildedRose();

        System.out.println("OMGHAI!");
        List<Item> items = new ArrayList<Item>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));

            gildedRose.updateQuality(items);

    }


    /**
     * end of the day system  update lowers both values for every item
     * @param items
     */
    public void updateQuality(List<Item> items)
    {
        for (Item item : items)
        {
            if(item != null) {

                if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                    item.setSellIn(item.getSellIn() - 1);

                }
                if (requiredSpecialRules(item)) {
                    increaseTheQualityBasedOnItem(item);
                 }else{
                    decreaseTheQualityBasedOnItem(item);
                }


                if (item.getSellIn() < 0 && "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    item.setQuality(item.getQuality() - item.getQuality());
                }
            }

        }

    }

    /**
     * Aged Brie ,Backstage passes and Sulfuras has additional rules defined
     * @param item
     * @return boolean
     */
    public boolean requiredSpecialRules(Item item){
        if (("Aged Brie".equals(item.getName()))
                || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())
                || "Sulfuras, Hand of Ragnaros".equals(item.getName())) {
            return true;

        }
        return false;

    }

    /**
     *  decrease the quality for normal items
     * and  decrease twice for Conjured items when compaare normal items
     * @param item
     */
    public void decreaseTheQualityBasedOnItem(Item item){

        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);

            if ( "Conjured Mana Cake".equals(item.getName())) {
                item.setQuality(item.getQuality() - 1);
            }
        }

    }

    /**
     * increase the quality for Backstage passes and Aged Brie
     * @param item
     */
    public void increaseTheQualityBasedOnItem(Item item){

        boolean  increasQuality = false;
        if (item.getQuality() < 50)
        {
            if ("Backstage passes to a TAFKAL80ETC concert".equals(item.getName()))
            {
                if (item.getSellIn() < 6)
                {
                    item.setQuality(item.getQuality() + 3);
                } else if (item.getSellIn() < 11)
                {
                    item.setQuality(item.getQuality() + 2);
                }else{
                    increasQuality = true;

                }
            }else if ( "Aged Brie".equals(item.getName())){

                increasQuality = true;

            }

            if(increasQuality){
                item.setQuality(item.getQuality() + 1);
            }
        }

    }

}