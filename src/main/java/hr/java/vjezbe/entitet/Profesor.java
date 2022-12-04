package hr.java.vjezbe.entitet;

import java.util.Scanner;

public class Profesor extends Osoba {

    private String sifra;
    private String titula;

    public Profesor(Long id, String ime, String prezime, String sifra, String titula) {
        super(id, ime, prezime);
        this.sifra = sifra;
        this.titula = titula;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getTitula() {
        return titula;
    }

    public void setTitula(String titula) {
        this.titula = titula;
    }
}
