package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public final class Ispit implements Online{

    private Predmet predmet;
    private Student student;
    private Integer ocjena;
    private LocalDateTime datumIVrijeme;
    private Dvorana dvorana;
    private String nazivSoftvera;

    public Ispit(Predmet predmet, Student student, Integer ocjena, LocalDateTime datumIVrijeme) {
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
    }

    public static Ispit inputIspit(Scanner scanner, Predmet[] predmeti, Student[] studenti) {
        Predmet predmet;
        Student student;
        Integer ocjena;
        LocalDateTime datumIVrijeme;

        for (int i = 0; i < predmeti.length; i++) {
            System.out.printf("%d. %s\n", i + 1, predmeti[i].getNaziv());
        }
        System.out.print("Odabir >>> ");
        Integer odabir = Integer.parseInt(scanner.nextLine());
        predmet = predmeti[odabir-1];

        for (int i = 0; i < studenti.length; i++) {
            System.out.printf("%d. %s %s\n", i + 1, studenti[i].getIme(), studenti[i].getPrezime());
        }
        System.out.print("Odabir >>> ");
        odabir = Integer.parseInt(scanner.nextLine());
        student = studenti[odabir-1];

        System.out.print("Unesite ocjenu na ispitu (1-5): ");
        ocjena = Integer.parseInt(scanner.nextLine());

        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyy. HH:mm): ");
        datumIVrijeme = LocalDateTime.parse(scanner.nextLine(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));

        return new Ispit(predmet, student, ocjena, datumIVrijeme);
    }

    public void inputNazivSoftvera(String nazivSoftvera) {
        this.nazivSoftvera = nazivSoftvera;
    }

    public Predmet getPredmet() {
        return predmet;
    }

    public void setPredmet(Predmet predmet) {
        this.predmet = predmet;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public void setOcjena(Integer ocjena) {
        this.ocjena = ocjena;
    }

    public LocalDateTime getDatumIVrijeme() {
        return datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }
}
