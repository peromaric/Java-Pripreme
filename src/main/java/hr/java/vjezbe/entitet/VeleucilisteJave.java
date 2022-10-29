package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Scanner;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
    public VeleucilisteJave(
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
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                    this.getIspiti(), student
            );
            if(ispitiStudenta.length > 0) {
                BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
                if(prosjek.compareTo(najboljiProsjek) >= 0) {
                    najuspjesnijiStudent = student;
                }
            }
        }

        return najuspjesnijiStudent;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispitiStudenta,
            int ocjenaZavrsnogRada,
            int ocjenaObraneRada
    ) {
        BigDecimal prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
        BigDecimal konacnaOcjena = BigDecimal.valueOf(
                ((prosjekOcjena.doubleValue() * 2) + ocjenaObraneRada + ocjenaZavrsnogRada) / 4
        );
        return konacnaOcjena.round(new MathContext(1));
    }

    public void ispisiPodatkeOStudiju(
            Scanner scanner
    ) {
        for(Student student : getStudenti()) {
            System.out.printf("Unesite ocjenu završnog rada studenta %s %s: ",
                    student.getIme(), student.getPrezime()
            );
            int ocjenaZavrsnogRada = Integer.parseInt(scanner.nextLine());


            System.out.printf("Unesite ocjenu obrane rada studenta %s %s: ",
                    student.getIme(), student.getPrezime()
            );
            int ocjenaObraneRada = Integer.parseInt(scanner.nextLine());

            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
            if(ispitiStudenta.length > 0) {
                BigDecimal konacnaOcjena = izracunajKonacnuOcjenuStudijaZaStudenta(
                        ispitiStudenta,
                        ocjenaZavrsnogRada,
                        ocjenaObraneRada
                );
                System.out.println("Konačna ocjena: " + konacnaOcjena);
            } else {
                System.out.println("Student nije pisao niti jedan ispit.");
            }
        }

        Student najuspjesnijiStudent = odrediNajuspjesnijegStudentaNaGodini(2022);
        System.out.println("Najuspjesniji student na ovom studiju je" + " " +
                    najuspjesnijiStudent.getIme() + " " +
                    najuspjesnijiStudent.getPrezime()
                );
    }

    public static class BuilderVeleuciliste extends Builder {

        public BuilderVeleuciliste(Builder builder) {
            super(
                    builder.getNaziv(),
                    builder.getPredmeti(),
                    builder.getProfesori(),
                    builder.getStudenti(),
                    builder.getIspiti()
            );
        }

        public VeleucilisteJave build() {
            return new VeleucilisteJave(
                    this.getNaziv(),
                    this.getPredmeti(),
                    this.getProfesori(),
                    this.getStudenti(),
                    this.getIspiti()
            );
        }
    }
}
