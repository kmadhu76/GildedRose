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
	public void testBackstagePassesShouldIncrease3WhenSellINLessThan6() {

		Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10);

		gildedRose.increaseTheQualityBasedOnItem(item);

		assertEquals(13, item.getQuality());
	}

	@Test
	public void testBackstagePassesShouldIncrease2WhenSellINLessThan11() {

		Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10);

		gildedRose.increaseTheQualityBasedOnItem(item);

		assertEquals(12, item.getQuality());
	}

	@Test
	public void testBackstagePassesShouldIncrease1WhenSellInMoreThan10() {

		Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10);

		gildedRose.increaseTheQualityBasedOnItem(item);

		assertEquals(11, item.getQuality());
	}

	@Test
	public void testAgedBrieShouldIncreaseOnly1() {

		Item item = new Item("Aged Brie", 11, 10);

		gildedRose.increaseTheQualityBasedOnItem(item);

		assertEquals(11, item.getQuality());
	}

	@Test
	public void testCheckforRequiredSpecialRulesIsTrue() {

		assertTrue(gildedRose.requiredSpecialRules((new Item("Sulfuras, Hand of Ragnaros", 0, 80))));
		assertTrue(gildedRose.requiredSpecialRules((new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20))));
		assertTrue(gildedRose.requiredSpecialRules(new Item("Aged Brie", 2, 0)));

	}

	@Test
	public void testCheckforRequiredSpecialRulesIsFalse() {

		assertFalse(gildedRose.requiredSpecialRules(new Item("adasdsadas", 5, 7)));
		assertFalse(gildedRose.requiredSpecialRules(new Item("+5 Dexterity Vest", 10, 20)));
		assertFalse(gildedRose.requiredSpecialRules((new Item("Conjured Mana Cake", 3, 6))));
		assertFalse(gildedRose.requiredSpecialRules(new Item("Elixir of the Mongoose", 5, 7)));

	}

	@Test
	public void testDateIsPassedLowerTheBothSellInAndQualityValuesForEveryItem() {

		List<Item> items = new ArrayList<Item>();


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
	public void testAllDifferentItemsWithDifferntDataAtOnce() {

		gildedRose.updateQuality(items);
		for (Item item : items){

			if("+5 Dexterity Vest".equalsIgnoreCase(item.getName())){
				assertEquals(9, item.getSellIn());
				assertEquals(19, item.getQuality());
			}else if("Aged Brie".equalsIgnoreCase(item.getName())){
				assertEquals(1, item.getSellIn());
				assertEquals(1, item.getQuality());
			}else if("Elixir of the Mongoose".equalsIgnoreCase(item.getName())){
				assertEquals(4, item.getSellIn());
				assertEquals(6, item.getQuality());
			}else if("ESulfuras, Hand of Ragnaros".equalsIgnoreCase(item.getName())){
				assertEquals(0, item.getSellIn());
				assertEquals(80, item.getQuality());
			}else if("Backstage passes to a TAFKAL80ETC concert".equalsIgnoreCase(item.getName())){
				assertEquals(14, item.getSellIn());
				assertEquals(21, item.getQuality());
			}else if("Backstage passes to a TAFKAL80ETC concert".equalsIgnoreCase(item.getName())){
				assertEquals(2, item.getSellIn());
				assertEquals(5, item.getQuality());
			}

		}


	}

	@AfterClass
	public static void tearDown(){
		items = null;
		gildedRose= null;

	}



}
