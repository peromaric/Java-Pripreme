package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.util.Scanner;

public class Glavna {

    private static final Integer BROJ_PROFESORA = 2;
    private static final Integer BROJ_ISPITA = 2;
    private static final Integer BROJ_PREDMETA = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Profesor[] profesori = new Profesor[BROJ_PROFESORA];
        Predmet[] predmeti = new Predmet[BROJ_PREDMETA];
        Ispit[] ispiti = new Ispit[BROJ_ISPITA];
        Student[] studenti;
        int brojObrazovnihUstanova = 0;

        System.out.print("Unesite broj obrazovnih ustanova: ");
        brojObrazovnihUstanova = Integer.parseInt(scanner.nextLine());

        for(int j = 0; j < brojObrazovnihUstanova; j++) {
            System.out.printf("Unesite podatke za %d. obrazovnu ustanovu\n", j + 1);
            for(int i = 0; i < BROJ_PROFESORA; i++) {
                System.out.printf("Unos %d. profesora\n", i + 1);
                profesori[i] = Profesor.inputProfesor(scanner);
            }

            System.out.print("Unesite ukupan broj studenata na obrazovnoj ustanovi: ");
            int ukupanBrojStudenata = Integer.parseInt(scanner.nextLine());
            studenti = new Student[ukupanBrojStudenata];
            for(int i = 0; i < studenti.length; i++) {
                System.out.printf("Unesite %d. studenta\n", i + 1);
                studenti[i] = Student.inputStudent(scanner);
            }

            for(int i = 0; i < BROJ_PREDMETA; i++) {
                System.out.printf("Unos %d. predmeta\n", i + 1);
                predmeti[i] = Predmet.inputPredmet(scanner, profesori);
                System.out.printf("Unesite broj studenata na predmetu '%s':", predmeti[i].getNaziv());
                int brojStudenata = Integer.parseInt(scanner.nextLine());
                predmeti[i].setStudenti(new Student[brojStudenata]);
                predmeti[i].inputStudenti(scanner, studenti);
            }

            for(int i = 0; i < BROJ_ISPITA; i++) {
                System.out.printf("Unos %d. ispitnog roka\n", i + 1);
                ispiti[i] = Ispit.inputIspit(scanner, predmeti, studenti);
            }

            for (Ispit ispit : ispiti) {
                if (ispit.getOcjena() == 5) {
                    System.out.printf(
                            "Student %s %s je ostvario ocjenu 'izvrstan' na predmetu %s\n",
                            ispit.getStudent().getIme(),
                            ispit.getStudent().getPrezime(),
                            ispit.getPredmet().getNaziv()
                    );
                }
            }
        }

    }
}
