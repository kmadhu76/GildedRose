import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GildedRoseTest {

	private static List<Item> items = null;
	private static GildedRose gildedRose = null;

	@BeforeClass
	public static void setup(){
		gildedRose = new GildedRose();
		items = new ArrayList<Item>();

		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("Aged Brie", 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

	}


	@Test
	public void testDateIsPassedLowerTheBothSellInAndQualityValuesForEveryItem() {


		List<Item> items = new ArrayList<Item>();

		//items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item("+5 Dexterity Vest", 10, 20));


		gildedRose.updateQuality(items);

		assertEquals(9, items.get(0).getSellIn());
		assertEquals(19, items.get(0).getQuality());

	}

	@Test
	public void testQualityOfItemIsNeverNegative() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("+5 Dexterity Vest", 10, 0));


		gildedRose.updateQuality(items);

		assertEquals(9, items.get(0).getSellIn());
		assertEquals(0, items.get(0).getQuality());

	}

	@Test
	public void testAgedBrieQualityIncreaseAgedItGets() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Aged Brie", 2, 0));

		gildedRose.updateQuality(items);

		assertEquals(1, items.get(0).getSellIn());
		assertEquals(1, items.get(0).getQuality());

	}

	@Test
	public void testTheQualityOfItemNeverMoreThan50() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Aged Brie", 10, 50));

		gildedRose.updateQuality(items);

		assertEquals(9, items.get(0).getSellIn());
		assertEquals(50, items.get(0).getQuality());

	}

	@Test
	public void testSulfurasNeverSoldOrDecreaseTheQuantity() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

		gildedRose.updateQuality(items);

		assertEquals(0, items.get(0).getSellIn());
		assertEquals(80, items.get(0).getQuality());

	}

	@Test
	public void testBackstagePassesIncreasQualityWhenSellin10orMore() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

		gildedRose.updateQuality(items);

		assertEquals(14, items.get(0).getSellIn());
		assertEquals(21, items.get(0).getQuality());

	}

	@Test
	public void testBackstagePassesIncreaseQualityWhenSellin10orLess() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 8, 10));


		gildedRose.updateQuality(items);

		assertEquals(9, items.get(0).getSellIn());
		assertEquals(22, items.get(0).getQuality());

		assertEquals(7, items.get(1).getSellIn());
		assertEquals(12, items.get(1).getQuality());

	}

	@Test
	public void testBackstagePassesIncreaseQualityWhenSellin5orLess() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));
		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 3, 10));


		gildedRose.updateQuality(items);

		assertEquals(4, items.get(0).getSellIn());
		assertEquals(23, items.get(0).getQuality());

		assertEquals(2, items.get(1).getSellIn());
		assertEquals(13, items.get(1).getQuality());

	}


	@Test
	public void testBackstagePassesIncreaseQualityis0AfterConcert() {


		List<Item> items = new ArrayList<Item>();

		items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));

		gildedRose.updateQuality(items);

		//assertEquals(-1, items.get(0).getSellIn());
		assertEquals(0, items.get(0).getQuality());

	}

	@Test
	public void testAllDifferentItemsAtOnce() {

		gildedRose.updateQuality(items);
		for (Item item : items){
			System.out.println(item.getName() + ":" + item.getSellIn() + ":" + item.getQuality());
		}


		assertEquals(9, items.get(0).getSellIn());
		assertEquals(19, items.get(0).getQuality());

	}

	@AfterClass
	public static void tearDown(){
		items = null;
		gildedRose= null;

	}



}
