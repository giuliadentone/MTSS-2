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
        assertEquals(0.0, scontrino.getOrderPrice(itemsOrdered, utente), 1e-8);
    }
}