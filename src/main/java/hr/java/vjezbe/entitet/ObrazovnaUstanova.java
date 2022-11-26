package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class ObrazovnaUstanova {

    private String naziv;
    private List<Predmet> predmeti;
    private List<Profesor> profesori;
    private List<Student> studenti;
    private List<Ispit> ispiti;

    public ObrazovnaUstanova() {
        profesori = new ArrayList<>();
        predmeti = new ArrayList<>();
        ispiti = new ArrayList<>();
        studenti = new ArrayList<>();
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina);

    public void inputObrazovnaUstanova(Scanner scanner, int j) {

        System.out.printf("Unesite naziv %d. obrazovne ustanove: ", j + 1);
        String nazivObrazovneUstanove = scanner.nextLine();
        this.setNaziv(nazivObrazovneUstanove);

        int brojProfesora = Unos.unosIntegera(
                scanner,
                "Unesite broj profesora na " + this.getNaziv()
        );
        for(int i = 0; i < brojProfesora; i++) {
            System.out.printf("Unos %d. profesora\n", i + 1);
            profesori.add(Profesor.inputProfesor(scanner));
        }

        int brojStudenata = Unos.unosIntegera(
                scanner,
                "Unesite ukupan broj studenata na  " + this.getNaziv()
        );
        for(int i = 0; i < brojStudenata; i++) {
            System.out.printf("Unesite %d. studenta\n", i + 1);
            studenti.add(Student.inputStudent(scanner));
        }

        int brojPredmeta = Unos.unosIntegera(
                scanner,
                "Unesite ukupan broj predmeta na " + this.getNaziv()
        );
        for(int i = 0; i < brojPredmeta; i++) {
            System.out.printf("Unos %d. predmeta\n", i + 1);
            predmeti.add(Predmet.inputPredmet(scanner, profesori));
            predmeti.get(i).inputStudenti(scanner, studenti);
        }

        int brojIspita = Unos.unosIntegera(
                scanner,
                "Unesite ukupan broj ispita na " + this.getNaziv()
        );
        for(int i = 0; i < brojIspita; i++) {
            System.out.printf("Unos %d. ispitnog roka\n", i + 1);
            ispiti.add(Ispit.inputIspit(scanner, predmeti));
        }
    }

    public abstract void ispisiPodatkeOStudiju(Scanner scanner);

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Ispit> getIspiti() {
        return ispiti;
    }

    public void setIspiti(List<Ispit> ispiti) {
        this.ispiti = ispiti;
    }
}
