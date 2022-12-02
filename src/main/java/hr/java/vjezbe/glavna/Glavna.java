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
        Sveuciliste<ObrazovnaUstanova> sveuciliste = new Sveuciliste<>();

        for(int j = 0; j < brojObrazovnihUstanova; j++) {
            System.out.printf("Unesite podatke za %d. obrazovnu ustanovu\n", j + 1);
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

            obrazovnaUstanova.inputObrazovnaUstanova(scanner, j);
            sveuciliste.dodajObrazovnuUstanovu(obrazovnaUstanova);
            System.out.println("Studenti pojedinih predmeta sortirani po imenima:");
            for(Predmet predmet : obrazovnaUstanova.getPredmeti()) {
                System.out.println("Studenti predmeta " + predmet.getNaziv());
                for(Student student : predmet.getSortedStudents()) {
                    System.out.printf("%s %s\n", student.getPrezime(), student.getIme());
                }
            }
        }

        sveuciliste.ispisiPodatke(scanner);

    }
}
