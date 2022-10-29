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

    private Profesor() {
        super("", "");
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

        return new Builder(sifra)
                .withIme(ime)
                .withPrezime(prezime)
                .withTitula(titula)
                .build();
    }

    public static class Builder {

        private String sifra;
        private String ime;
        private String prezime;
        private String titula;

        public Builder(String sifra) {
            this.sifra = sifra;
        }

        public Builder withIme(String ime) {
            this.ime = ime;
            return this;
        }

        public Builder withPrezime(String prezime) {
            this.prezime = prezime;

            return this;
        }

        public Builder withTitula(String titula) {
            this.titula = titula;

            return this;
        }

        public Profesor build() {
            Profesor profesor = new Profesor();
            profesor.sifra = this.sifra;
            profesor.setIme(this.ime);
            profesor.setPrezime(this.prezime);
            profesor.titula = this.titula;

            return profesor;
        }


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
