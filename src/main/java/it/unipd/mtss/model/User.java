////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {

    String nome;
    String cognome;
    String email; // id
    LocalDate nascita;

    public User(String nome, String cognome, LocalDate nascita, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.nascita = nascita;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public LocalDate getNascita() {
        return nascita;
    }
}