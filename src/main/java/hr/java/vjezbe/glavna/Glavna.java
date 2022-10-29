package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.util.Scanner;

public class Glavna {

    private static final Integer BROJ_PROFESORA = 2;
    private static final Integer BROJ_ISPITA = 2;
    private static final Integer BROJ_PREDMETA = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int brojObrazovnihUstanova = 0;

        System.out.print("Unesite broj obrazovnih ustanova: ");
        brojObrazovnihUstanova = Integer.parseInt(scanner.nextLine());
        ObrazovnaUstanova[] obrazovneUstanove = new ObrazovnaUstanova[brojObrazovnihUstanova];

        for(int j = 0; j < brojObrazovnihUstanova; j++) {

            ObrazovnaUstanova.Builder obrazovnaUstanovaBuilder = new ObrazovnaUstanova.Builder();
            Profesor[] profesori = new Profesor[BROJ_PROFESORA];
            Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
            Ispit[] ispiti = new Ispit[BROJ_ISPITA];
            Student[] studenti;

            System.out.printf("Unesite naziv %d. obrazovne ustanove: ", j + 1);
            String nazivVeleucilista = scanner.nextLine();
            obrazovnaUstanovaBuilder.withNaziv(nazivVeleucilista);

            System.out.printf("Unesite podatke za %d. obrazovnu ustanovu\n", j + 1);
            for(int i = 0; i < BROJ_PROFESORA; i++) {
                System.out.printf("Unos %d. profesora\n", i + 1);
                profesori[i] = Profesor.inputProfesor(scanner);
            }
            obrazovnaUstanovaBuilder.withProfesori(profesori);

            System.out.print("Unesite ukupan broj studenata na obrazovnoj ustanovi: ");
            int ukupanBrojStudenata = Integer.parseInt(scanner.nextLine());
            studenti = new Student[ukupanBrojStudenata];
            for(int i = 0; i < studenti.length; i++) {
                System.out.printf("Unesite %d. studenta\n", i + 1);
                studenti[i] = Student.inputStudent(scanner);
            }
            obrazovnaUstanovaBuilder.withStudenti(studenti);

            for(int i = 0; i < BROJ_PREDMETA; i++) {
                System.out.printf("Unos %d. predmeta\n", i + 1);
                predmeti[i] = Predmet.inputPredmet(scanner, profesori);
                System.out.printf("Unesite broj studenata na predmetu '%s':", predmeti[i].getNaziv());
                int brojStudenata = Integer.parseInt(scanner.nextLine());
                predmeti[i].setStudenti(new Student[brojStudenata]);
                predmeti[i].inputStudenti(scanner, studenti);
            }
            obrazovnaUstanovaBuilder.withPredmeti(predmeti);

            for(int i = 0; i < BROJ_ISPITA; i++) {
                System.out.printf("Unos %d. ispitnog roka\n", i + 1);
                ispiti[i] = Ispit.inputIspit(scanner, predmeti, studenti);
            }
            obrazovnaUstanovaBuilder.withIspiti(ispiti);

            System.out.println("Unesite tip obrazovne ustanove: 1. Veleučilište jave, 2. Fakultet računarstva");
            System.out.print("Odabir >> ");
            int odabir = Integer.parseInt(scanner.nextLine());

            switch (odabir) {
                case 1 -> {
                    VeleucilisteJave.BuilderVeleuciliste veleucilisteBuilder =
                            new VeleucilisteJave.BuilderVeleuciliste(obrazovnaUstanovaBuilder);
                    obrazovneUstanove[j] = veleucilisteBuilder.build();
                }
                case 2 -> {
                    FakultetRacunarstva.BuilderFakultetRacunarstva fakultetBuilder =
                            new FakultetRacunarstva.BuilderFakultetRacunarstva(obrazovnaUstanovaBuilder);
                    obrazovneUstanove[j] = fakultetBuilder.build();
                }
                default -> {
                    System.out.println("ERROR");
                    System.exit(1);
                }
            }
        }

        for(ObrazovnaUstanova obrazovnaUstanova : obrazovneUstanove) {
            System.out.println("Podaci o " + obrazovnaUstanova.getNaziv());
            if(obrazovnaUstanova instanceof VeleucilisteJave) {
                ((VeleucilisteJave) obrazovnaUstanova).ispisiPodatkeOStudiju(scanner);
            } else if (obrazovnaUstanova instanceof FakultetRacunarstva) {
                ((FakultetRacunarstva) obrazovnaUstanova).ispisiPodatkeOStudiju(scanner);
            }
        }

    }
}
