////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserTest {

    User user;

    @Before
    public void initialize() {
        user = new User("Andrea", "Stecca", LocalDate.of(2001, 11, 19), "andrea@gmail.com");
    }

    @Test
    public void testGetName() {
        assertEquals("Andrea", user.getNome());
    }

    @Test
    public void testGetSurname() {
        assertEquals("Stecca", user.getCognome());
    }

    @Test
    public void testGetDateOfBirth() {
        assertEquals(LocalDate.of(2001, 11, 19), user.getNascita());
    }
}