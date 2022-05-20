////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class EItemTest {
    EItem item;

    @Before
    public void initialize() {
        item = new EItem(EItem.itemType.KEYBOARD, 80.0, "Corsair k50");
    }

    @Test
    public void testGetItemType() {
        assertEquals(EItem.itemType.KEYBOARD, item.getTipo());
    }

    @Test
    public void testGetName() {
        assertEquals("Corsair k50", item.getNome());
    }

    @Test
    public void testGetPrice() {
        assertEquals(80.0, item.getPrezzo(), 1e-8);
    }
}