package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Scanner;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski {
    public FakultetRacunarstva(
            String naziv,
            Predmet[] predmeti,
            Profesor[] profesori,
            Student[] studenti,
            Ispit[] ispiti
    ) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.MIN);

        int najveciBrojIzvrsnoOcijenjenihIspita = 0;
        for(Student student : this.getStudenti()) {
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                    this.getIspiti(), student
            );

            int brojIzvrsnoOcijenjenihIspitaStudenta = odrediBrojIzvrsnoOcijenjenihIspita(ispitiStudenta);
            if(brojIzvrsnoOcijenjenihIspitaStudenta > najveciBrojIzvrsnoOcijenjenihIspita) {
                najuspjesnijiStudent = student;
            }
        }

        return najuspjesnijiStudent;
    }

    private int odrediBrojIzvrsnoOcijenjenihIspita(Ispit[] ispiti) {
        int brojIzvrsnoOcijenjenihIspita = 0;

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena() == 5) {
                brojIzvrsnoOcijenjenihIspita ++;
            }
        }

        return brojIzvrsnoOcijenjenihIspita;
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.MIN);
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                    this.getIspiti(), student
            );

            BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
            if(prosjek.compareTo(najboljiProsjek) > 0) {
                najuspjesnijiStudent = student;
            } else if (prosjek.compareTo(najboljiProsjek) == 0) {
                if(student.getDatumRodjenja().isBefore(najuspjesnijiStudent.getDatumRodjenja())) {
                    najuspjesnijiStudent = student;
                }
            }
        }

        return najuspjesnijiStudent;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispitiStudenta,
            int ocjenaDiplomskogRada,
            int ocjenaObraneRada) {
        BigDecimal prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
        BigDecimal konacnaOcjena = BigDecimal.valueOf(
                ((prosjekOcjena.doubleValue() * 3) + ocjenaObraneRada + ocjenaDiplomskogRada) / 5
        );
        return konacnaOcjena.round(new MathContext(1));
    }

    public void ispisiPodatkeOStudiju(
            Scanner scanner
    ) {
        for(Student student : getStudenti()) {
            System.out.printf("Unesite ocjenu završnog rada studenta %s %s",
                    student.getIme(), student.getPrezime()
            );
            int ocjenaZavrsnogRada = Integer.parseInt(scanner.nextLine());


            System.out.printf("Unesite ocjenu obrane rada studenta %s %s",
                    student.getIme(), student.getPrezime()
            );
            int ocjenaObraneRada = Integer.parseInt(scanner.nextLine());

            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
            BigDecimal konacnaOcjena = izracunajKonacnuOcjenuStudijaZaStudenta(
                    ispitiStudenta,
                    ocjenaZavrsnogRada,
                    ocjenaObraneRada
            );

            System.out.println("Konačna ocjena: " + konacnaOcjena);
        }
    }

    public static class BuilderFakultetRacunarstva extends Builder {

        public BuilderFakultetRacunarstva(Builder builder) {
            super(
                    builder.getNaziv(),
                    builder.getPredmeti(),
                    builder.getProfesori(),
                    builder.getStudenti(),
                    builder.getIspiti()
            );
        }
        public FakultetRacunarstva build() {
            return new FakultetRacunarstva(
                    this.getNaziv(),
                    this.getPredmeti(),
                    this.getProfesori(),
                    this.getStudenti(),
                    this.getIspiti()
            );
        }
    }
}
