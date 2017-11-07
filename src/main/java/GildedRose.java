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
        try {
            gildedRose.updateQuality(items);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }



    public void updateQuality(List<Item> items)
    {
        for (Item item : items)
        {
            if(item != null) {
                if (!requiredSpecialRules(item)) {
                    if (item.getQuality() > 0) {
                        item.setQuality(item.getQuality() - 1);
                    }

                }
                if (!"Sulfuras, Hand of Ragnaros".equals(item.getName())) {
                    item.setSellIn(item.getSellIn() - 1);

                }
                increaseTheQualityBasedOnItem(item);

                if (item.getSellIn() < 0 && "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())) {
                    item.setQuality(item.getQuality() - item.getQuality());
                }
            }

        }

    }

    public boolean requiredSpecialRules(Item item){
        if (("Aged Brie".equals(item.getName()))
                || "Backstage passes to a TAFKAL80ETC concert".equals(item.getName())
                || "Sulfuras, Hand of Ragnaros".equals(item.getName())) {
            return true;

        }
        return false;

    }

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