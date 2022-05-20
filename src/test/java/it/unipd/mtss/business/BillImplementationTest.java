////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

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
    public void testGetOrderPrice_Scontrinovuoto() throws BillException {
        itemsOrdered = null;
        try {
            scontrino.getOrderPrice(itemsOrdered, utente);
        } catch (BillException e) {

            assertEquals("La lista di prodotti non può essere vuota", e.getMessage());
        }
    }

    @Test
    public void testGetOrderPrice_Prodottonull() {

        EItem keyboard = new EItem(itemType.KEYBOARD, 20, "Logitechi");
        EItem cpu = new EItem(itemType.PROCESSOR, 30, "Intel 6700x");
        itemsOrdered.add(keyboard);
        itemsOrdered.add(cpu);
        itemsOrdered.add(null);
        try {
            scontrino.getOrderPrice(itemsOrdered, utente);
        } catch (BillException e) {

            assertEquals("Il prodotto non può essere vuoto", e.getMessage());
        }
    }

    @Test
    public void testGetOrderPrice_senzaUtente() {

        itemsOrdered.add(new EItem(itemType.KEYBOARD, 20, "Logitechi"));
        try {
            scontrino.getOrderPrice(itemsOrdered, null);
        } catch (BillException e) {

            assertEquals("Utente non può essere null", e.getMessage());
        }
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
        itemsOrdered.add(new EItem(itemType.MOUSE, 200, "mouse"));
        for (int i = 0; i < 11; i++) {
            itemsOrdered.add(new EItem(itemType.MOUSE, 10, "mouse"));
        }

        assertEquals(300.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
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

    @Test
    public void testSconto1000euro() throws BillException {
        EItem tastiera = new EItem(itemType.KEYBOARD, 1001, "Tastiera");
        itemsOrdered.add(tastiera);
        assertEquals(900.9, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }

    @Test
    public void test30prodotti() throws BillException {
        for (int i = 0; i < 32; i++) {
            itemsOrdered.add(new EItem(itemType.MOUSE, 10, "mouse"));
        }
        try {
            scontrino.getOrderPrice(itemsOrdered, utente);
        } catch (BillException e) {
            assertEquals("La lista di prodotti ordinati è troppo grande (maggiore di 30)", e.getMessage());
        }
    }

    @Test
    public void testminore10euro() throws BillException {
        EItem tastiera = new EItem(itemType.KEYBOARD, 9, "Tastiera");
        itemsOrdered.add(tastiera);
        assertEquals(11.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }
}