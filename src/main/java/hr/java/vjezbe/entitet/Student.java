package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {

    private String ime;
    private String prezime;
    private String jmbag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbag = jmbag;
        this.datumRodjenja = datumRodjenja;
    }

    public static Student inputStudent(Scanner scanner) {
        String ime;
        String prezime;
        String jmbag;
        LocalDate datumRodjenja;

        System.out.print("Unesite ime studenta: ");
        ime = scanner.nextLine();

        System.out.print("Unesite prezime studenta: ");
        prezime = scanner.nextLine();

        System.out.print("Unesite jmbag studenta: ");
        jmbag = scanner.nextLine();

        System.out.print("Unesite datum rodenja studenta u formatu (dd.MM.yyyy.): ");
        datumRodjenja = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy."));

        return new Student(ime, prezime, jmbag, datumRodjenja);
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }
}
