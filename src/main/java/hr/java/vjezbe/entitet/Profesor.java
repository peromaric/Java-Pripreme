package hr.java.vjezbe.entitet;

import java.util.Scanner;

public class Profesor extends Osoba{

    private String sifra;
    private String titula;

    public Profesor(String sifra, String ime, String prezime, String titula) {
        super(ime, prezime);
        this.sifra = sifra;
        this.titula = titula;
    }

    public static Profesor inputProfesor(Scanner scanner) {
        String sifra;
        String ime;
        String prezime;
        String titula;

        System.out.print("Unesite šifru profesora: ");
        sifra = scanner.nextLine();

        System.out.print("Unesite ime profesora: ");
        ime = scanner.nextLine();

        System.out.print("Unesite prezime profesora: ");
        prezime = scanner.nextLine();

        System.out.print("Unesite titulu profesora: ");
        titula = scanner.nextLine();

        return new Profesor(sifra, ime, prezime, titula);
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
