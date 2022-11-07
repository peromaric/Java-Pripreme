package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Scanner;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski, Unos {

    private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);
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

            if(ispitiStudenta.length > 0) {
                Ispit[] ispitiStudentaZaTuGodinu = filtrirajIspitePoGodini(ispitiStudenta, godina);
                if(ispitiStudentaZaTuGodinu.length > 0) {
                    int brojIzvrsnoOcijenjenihIspitaStudenta =
                            odrediBrojIzvrsnoOcijenjenihIspita(ispitiStudentaZaTuGodinu);
                    if (
                            brojIzvrsnoOcijenjenihIspitaStudenta > najveciBrojIzvrsnoOcijenjenihIspita
                    ) {
                        najuspjesnijiStudent = student;
                    }
                }
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
            try {
                Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                        this.getIspiti(), student
                );
                if(ispitiStudenta.length > 0) {
                    BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
                    if(prosjek.compareTo(najboljiProsjek) > 0) {
                        najuspjesnijiStudent = student;
                    } else if (prosjek.compareTo(najboljiProsjek) == 0) {
                        if(student.getDatumRodjenja().isBefore(najuspjesnijiStudent.getDatumRodjenja())) {
                            najuspjesnijiStudent = student;
                        }
                    }
                }
            } catch (NemoguceOdreditiProsjekStudentaException ex) {
                logger.debug("Nemoguće odrediti prosjek ocjena za studenta "
                        + student.getIme() + " "
                        + student.getPrezime()
                );
            }

        }

        return najuspjesnijiStudent;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispitiStudenta,
            int ocjenaDiplomskogRada,
            int ocjenaObraneRada) throws NemoguceOdreditiProsjekStudentaException {
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
            try {
                String poruka;

                poruka = "Unesite ocjenu završnog rada studenta "
                        + student.getIme() + " "
                        + student.getPrezime();

                int ocjenaDiplomskogRada = Unos.unosIntegera(scanner, poruka);


                poruka = "Unesite ocjenu obrane rada studenta "
                        + student.getIme() + " "
                        + student.getPrezime();
                int ocjenaObraneRada = Unos.unosIntegera(scanner, poruka);

                Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
                if (ispitiStudenta.length > 0) {
                    BigDecimal konacnaOcjena = izracunajKonacnuOcjenuStudijaZaStudenta(
                            ispitiStudenta,
                            ocjenaDiplomskogRada,
                            ocjenaObraneRada
                    );
                    System.out.println("Konačna ocjena: " + konacnaOcjena);
                } else {
                    System.out.println("Student nije pisao niti jedan ispit.");
                }
            } catch (NemoguceOdreditiProsjekStudentaException ex) {
                logger.debug("Nemoguće odrediti prosjek ocjena za studenta "
                        + student.getIme() + " "
                        + student.getPrezime()
                );
            }
        }

        Student dobitnikRektorove = odrediStudentaZaRektorovuNagradu();
        System.out.printf(
                "Dobitnik rektorove nagrada je %s %s!\n",
                dobitnikRektorove.getIme(),
                dobitnikRektorove.getPrezime()
        );

        int godina = Unos.unosIntegera(
                scanner,
                "Unesi godinu za koju Vas zanima najuspješniji student: "
        );

        Student najuspjesnijiStudentGodine = odrediNajuspjesnijegStudentaNaGodini(godina);
        if(najuspjesnijiStudentGodine.getDatumRodjenja().isEqual(LocalDate.MIN)) {
            System.out.println("Za tu godinu nema ispisa - nema dovoljno podataka.");
        } else {
            System.out.printf("Najuspješniji student %d. godine je %s %s.",
                    godina,
                    najuspjesnijiStudentGodine.getIme(),
                    najuspjesnijiStudentGodine.getPrezime()
            );
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
