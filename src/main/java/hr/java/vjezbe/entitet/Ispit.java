package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public final class Ispit implements Online, Unos {

    private Predmet predmet;
    private Student student;
    private Ocjena ocjena;
    private LocalDateTime datumIVrijeme;
    private Dvorana dvorana;
    private String nazivSoftvera;

    public Ispit(Predmet predmet, Student student, Ocjena ocjena, LocalDateTime datumIVrijeme) {
        this.predmet = predmet;
        this.student = student;
        this.ocjena = ocjena;
        this.datumIVrijeme = datumIVrijeme;
    }

    public static Ispit inputIspit(Scanner scanner, List<Predmet> predmeti) {
        Predmet predmet;
        Student student;
        Ocjena ocjena;
        LocalDateTime datumIVrijeme;
        String nazivDvorane;
        String zgradaDvorane;

        System.out.println("Odaberite predmet:");
        for (Predmet jedanPredmet : predmeti) {
            System.out.printf("%d. %s\n", predmeti.indexOf(jedanPredmet) + 1, jedanPredmet.getNaziv());
        }
        int odabir = Unos.unosIntegera(
                scanner,
                ""
        );
        predmet = predmeti.get(odabir - 1);

        System.out.print("Unesite naziv dvorane: ");
        nazivDvorane = scanner.nextLine();
        System.out.print("Unesite zgradu dvorane: ");
        zgradaDvorane = scanner.nextLine();

        List<Student> studentiPredmeta = predmet.getStudentiList();
        for (Student _student : studentiPredmeta) {
            System.out.printf("%d. %s %s\n",
                    studentiPredmeta.indexOf(_student) + 1,
                    _student.getIme(),
                    _student.getPrezime());
        }
        System.out.print("Odabir >>> ");
        odabir = Integer.parseInt(scanner.nextLine());
        student = predmet.getStudentiList().get(odabir - 1);

        ocjena = Unos.unosOcjene(scanner);

        System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyy. HH:mm): ");
        datumIVrijeme = LocalDateTime.parse(scanner.nextLine(),
                DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm"));

        Ispit ispit = new Ispit(predmet, student, ocjena, datumIVrijeme);
        ispit.setDvorana(new Dvorana(nazivDvorane, zgradaDvorane));
        return ispit;
    }

    public void inputNazivSoftvera(String nazivSoftvera) {
        this.nazivSoftvera = nazivSoftvera;
    }

    public Dvorana getDvorana() {
        return dvorana;
    }

    public void setDvorana(Dvorana dvorana) {
        this.dvorana = dvorana;
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

    public Ocjena getOcjena() {
        return ocjena;
    }

    public void setOcjena(Ocjena ocjena) {
        this.ocjena = ocjena;
    }

    public String getNazivSoftvera() {
        return nazivSoftvera;
    }

    public void setNazivSoftvera(String nazivSoftvera) {
        this.nazivSoftvera = nazivSoftvera;
    }

    public LocalDateTime getDatumIVrijeme() {
        return datumIVrijeme;
    }

    public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
        this.datumIVrijeme = datumIVrijeme;
    }
}
