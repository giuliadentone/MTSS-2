////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.business;

import it.unipd.mtss.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GiveAway {
    Random rnd;
    List<User> utente;

    public GiveAway() {
        rnd = new Random();
        rnd.setSeed(10);
        utente = new ArrayList<>();
    }

    public boolean controlloEstrazione(User user, LocalTime timeStamp) {
        // controllo minorenne
        if (Period.between(user.getNascita(), LocalDate.now()).getYears() < 18) {
            // controllo orario
            if (timeStamp.isAfter(LocalTime.of(18, 0)) && timeStamp.isBefore(LocalTime.of(19, 0))) {
                // controllo max utenti
                if (utente.size() < 10) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean estrazione(User user, LocalTime timeStamp) {

        if (user == null) {
            throw new IllegalArgumentException("Manca l'utente");
        }
        if (timeStamp == null) {
            throw new IllegalArgumentException("Manca l'orario");
        }

        if (controlloEstrazione(user, timeStamp) && rnd.nextInt(100) <= 25) {
            utente.add(user);
            return true;
        }
        return false;
    }
}