package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Glavna implements Unos {

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int brojObrazovnihUstanova = Unos.unosIntegera(scanner, "Unesite broj obrazovnih ustanova");
        List<ObrazovnaUstanova> obrazovneUstanove = new ArrayList<>();

        for(int j = 0; j < brojObrazovnihUstanova; j++) {

            ObrazovnaUstanova obrazovnaUstanova = null;

            int odabir = Unos.unosIntegera(
                    scanner,
                    "Unesite tip obrazovne ustanove: 1. Veleučilište jave, 2. Fakultet računarstva"
            );
            switch (odabir) {
                case 1 -> {
                    obrazovnaUstanova = new VeleucilisteJave();
                }
                case 2 -> {
                    obrazovnaUstanova = new FakultetRacunarstva();
                }
                default -> {
                    System.out.println("Pogrešan unos broja, pokušajte ponovo!");
                    System.exit(1);
                }
            }

            List<Profesor> profesori = obrazovnaUstanova.getProfesori();
            List<Predmet> predmeti = obrazovnaUstanova.getPredmeti();
            List<Ispit> ispiti = obrazovnaUstanova.getIspiti();
            List<Student> studenti = obrazovnaUstanova.getStudenti();

            System.out.printf("Unesite naziv %d. obrazovne ustanove: ", j + 1);
            String nazivObrazovneUstanove = scanner.nextLine();
            obrazovnaUstanova.setNaziv(nazivObrazovneUstanove);

            System.out.printf("Unesite podatke za %d. obrazovnu ustanovu\n", j + 1);

            int brojProfesora = Unos.unosIntegera(
                    scanner,
                    "Unesite broj profesora na " + obrazovnaUstanova.getNaziv()
            );
            for(int i = 0; i < brojProfesora; i++) {
                System.out.printf("Unos %d. profesora\n", i + 1);
                profesori.add(Profesor.inputProfesor(scanner));
            }

            int brojStudenata = Unos.unosIntegera(
                    scanner,
                    "Unesite ukupan broj studenata na  " + obrazovnaUstanova.getNaziv()
            );
            for(int i = 0; i < brojStudenata; i++) {
                System.out.printf("Unesite %d. studenta\n", i + 1);
                studenti.add(Student.inputStudent(scanner));
            }

            int brojPredmeta = Unos.unosIntegera(
                    scanner,
                    "Unesite ukupan broj predmeta na " + obrazovnaUstanova.getNaziv()
            );
            for(int i = 0; i < brojPredmeta; i++) {
                System.out.printf("Unos %d. predmeta\n", i + 1);
                predmeti.add(Predmet.inputPredmet(scanner, profesori));
                predmeti.get(i).inputStudenti(scanner, studenti);
            }

            int brojIspita = Unos.unosIntegera(
                    scanner,
                    "Unesite ukupan broj ispita na " + obrazovnaUstanova.getNaziv()
            );
            for(int i = 0; i < brojIspita; i++) {
                System.out.printf("Unos %d. ispitnog roka\n", i + 1);
                ispiti.add(Ispit.inputIspit(scanner, predmeti));
            }

            obrazovneUstanove.add(obrazovnaUstanova);
        }

        for(ObrazovnaUstanova obrazovnaUstanova : obrazovneUstanove) {
            System.out.println("Podaci o " + obrazovnaUstanova.getNaziv());
            if (obrazovnaUstanova instanceof FakultetRacunarstva) {
                ((FakultetRacunarstva) obrazovnaUstanova).ispisiPodatkeOStudiju(scanner);
            } else if (obrazovnaUstanova instanceof VeleucilisteJave) {
                ((VeleucilisteJave) obrazovnaUstanova).ispisiPodatkeOStudiju(scanner);
            }
            else {
                System.out.println("Nema unesenih obrazovnih ustanova");
            }
        }

    }
}
