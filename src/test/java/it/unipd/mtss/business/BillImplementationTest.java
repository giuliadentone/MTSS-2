package it.unipd.mtss.business;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import it.unipd.mtss.business.exception.BillException;
import it.unipd.mtss.model.EItem;
import it.unipd.mtss.model.User;
import it.unipd.mtss.model.EItem.itemType;

@RunWith(JUnit4.class)
public class BillImplementationTest {
    public List<EItem> itemsOrdered;
    public BillImplementation scontrino;
    public User utente;

    @Before
    public void setUp() throws Exception {
        scontrino = new BillImplementation();
        utente = new User("Andrea", "Stecca", LocalDate.of(2001, 11, 19), "andrea@gmail.com");
        itemsOrdered = new ArrayList<EItem>();
    }

    @Test
    public void testGetOrderPrice() throws BillException {

        EItem keyboard = new EItem(itemType.KEYBOARD, 20, "Logitechi");
        EItem cpu = new EItem(itemType.PROCESSOR, 30, "Intel 6700x");
        EItem motheboard = new EItem(itemType.MOTHERBOARD, 40, "Madre");

        itemsOrdered.add(keyboard);
        itemsOrdered.add(cpu);
        itemsOrdered.add(motheboard);
        assertEquals(90.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }

    @Test
    public void testScontoProcessori() throws BillException {
        for (int i = 0; i < 6; i++) {
            itemsOrdered.add(new EItem(itemType.PROCESSOR, 90, "Ryzen 1600"));
        }
        assertEquals(495.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }

    @Test
    public void testScontoMouse() throws BillException {

        for (int i = 0; i < 11; i++) {
            itemsOrdered.add(new EItem(itemType.MOUSE, 10, "mouse"));
        }

        assertEquals(100.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }

    @Test
    public void testMouseAndKeyboard() throws BillException {
        itemsOrdered.add(new EItem(itemType.MOUSE, 100, "mouse"));
        itemsOrdered.add(new EItem(itemType.MOUSE, 100, "mouse"));
        itemsOrdered.add(new EItem(itemType.KEYBOARD, 100, "keyboard"));
        itemsOrdered.add(new EItem(itemType.KEYBOARD, 5, "keyboard"));
        for (int i = 0; i < 11; i++) {
            itemsOrdered.add(new EItem(itemType.MOUSE, 10, "mouse"));
            itemsOrdered.add(new EItem(itemType.KEYBOARD, 30, "keyboard"));
        }

        assertEquals(740.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }
}