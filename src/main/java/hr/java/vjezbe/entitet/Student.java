package hr.java.vjezbe.entitet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

public class Student extends Osoba{

    private String jmbag;
    private LocalDate datumRodjenja;

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
        super(ime, prezime);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return jmbag.equals(student.jmbag) && datumRodjenja.equals(student.datumRodjenja);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jmbag, datumRodjenja);
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
