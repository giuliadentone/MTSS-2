////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.User;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GiveAwayTest {
    GiveAway gift = null;
    User utente = null;

    @Before
    public void beforeTests() {
        
        utente = new User( "Giulia", "Dentone", LocalDate.of(2014, 8, 12),"giulia@gmail.com");
        gift = new GiveAway();
    }

    @Test
    public void testControllo() {
       
        assertTrue(gift.controlloEstrazione(utente, LocalTime.of(18, 15)));
    }

    @Test
    public void testControllo_senzaUtente() {
        
        utente = new User("Giulia", "Dentone", LocalDate.of(2001, 8, 12), "giulia@gmail.com");
        
        assertFalse(gift.controlloEstrazione(utente, LocalTime.of(18, 15)));
    }

    @Test
    public void testControllo_OrarioSbagliato() {
        
        utente = new User("Giulia", "Dentone", LocalDate.of(2014, 8, 12), "giulia@gmail.com");
        
        assertFalse(gift.controlloEstrazione(utente, LocalTime.of(13, 15)));
    }

    @Test
    public void testControllo_troppiUtenti() throws IllegalArgumentException {
        
        for (int i = 0; i < 10; i++) {
            gift.utente.add(utente);
        }
       
        assertFalse(gift.estrazione(utente, LocalTime.of(18, 15)));
    }

    @Test
    public void testEstrazione() throws IllegalArgumentException {
        
        assertTrue(gift.estrazione(utente, LocalTime.of(18, 01)));
    }

    @Test
    public void testEstrazione_senzaUtente() {
        
        utente = null;
        try {
            gift.estrazione(utente, LocalTime.of(18, 01));
        } catch (IllegalArgumentException e) {
            
            assertEquals("Manca l'utente", e.getMessage());
        }
    }

    @Test
    public void testEstrazione_OrarioSbagliato() {
        
        try {
            gift.estrazione(utente, null);
        } catch (IllegalArgumentException e) {
            
            assertEquals("Manca l'orario", e.getMessage());
        }
    }
}