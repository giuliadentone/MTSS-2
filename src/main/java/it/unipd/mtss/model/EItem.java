////////////////////////////////////////////////////////////////////
// [Giulia] [Dentone] [2001687]
// [Andrea] [Stecca] [2016104]
////////////////////////////////////////////////////////////////////
package it.unipd.mtss.model;

public class EItem {

    public enum itemType {
        PROCESSOR, MOTHERBOARD, MOUSE, KEYBOARD
    }

    itemType tipo;
    Double prezzo;
    String nome;

    public EItem(itemType tipo, double prezzo, String nome) {
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.nome = nome;
    }

    public double getPrezzo() {

        return this.prezzo;
    }

    public itemType getTipo() {
        return this.tipo;
    }

    public String getNome() {
        return this.nome;
    }
}