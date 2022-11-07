package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.Scanner;


public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
    private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

    /**
     * Konstruktor klase veleuciliste jave
     * @param naziv - string naziv, naziv veleucilista
     * @param predmeti - svi predmeti
     * @param profesori - svi profesori
     * @param studenti - svi studenti
     * @param ispiti - svi ispiti
     */
    public VeleucilisteJave(
            String naziv,
            Predmet[] predmeti,
            Profesor[] profesori,
            Student[] studenti,
            Ispit[] ispiti
    ) {
        super(naziv, predmeti, profesori, studenti, ispiti);
    }

    /**
     * Odreduje najuspjesnijeg studenta na toj godini
     * @param godina - integer godine
     * @return - najuspjesnijeg studenta
     */
    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.MIN);
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            try {
                Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                        this.getIspiti(), student
                );
                if(ispitiStudenta.length > 0) {
                    BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
                    if(prosjek.compareTo(najboljiProsjek) >= 0) {
                        najuspjesnijiStudent = student;
                    }
                }
            } catch (NemoguceOdreditiProsjekStudentaException ex) {
                logger.debug("Nemoguce odrediti prosjek za"
                        + student.getIme() + " "
                        + student.getPrezime()
                );
            }

        }

        return najuspjesnijiStudent;
    }

    /**
     * Izracunava konacnu ocjenu studija
     * @param ispitiStudenta - svi ispiti tog studenta
     * @return - vraca konacnu ocjenu
     * @throws NemoguceOdreditiProsjekStudentaException - ako student ima 1 baca gresku
     */
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispitiStudenta,
            Student student,
            Scanner scanner
    ) throws NemoguceOdreditiProsjekStudentaException {
        BigDecimal prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
        String poruka;

        poruka = "Unesite ocjenu završnog rada studenta "
                + student.getIme() + " "
                + student.getPrezime();
        int ocjenaZavrsnogRada = Unos.unosIntegera(scanner, poruka);


        poruka = "Unesite ocjenu obrane rada studenta "
                + student.getIme() + " "
                + student.getPrezime();
        int ocjenaObraneRada = Unos.unosIntegera(scanner, poruka);
        BigDecimal konacnaOcjena = BigDecimal.valueOf(
                ((prosjekOcjena.doubleValue() * 2) + ocjenaObraneRada + ocjenaZavrsnogRada) / 4
        );
        return konacnaOcjena.round(new MathContext(1));
    }

    public void ispisiPodatkeOStudiju(
            Scanner scanner
    ) {
        for(Student student : getStudenti()) {
            try {


                Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
                if(ispitiStudenta.length > 0) {
                    BigDecimal konacnaOcjena = izracunajKonacnuOcjenuStudijaZaStudenta(
                            ispitiStudenta,
                            student,
                            scanner
                    );
                    System.out.println("Konačna ocjena: " + konacnaOcjena);
                } else {
                    System.out.println("Student nije pisao niti jedan ispit.");
                }
            } catch (NemoguceOdreditiProsjekStudentaException ex) {
                logger.debug("Student je pao ispit, nemoguce odrediti prosjek"
                        + student.getIme() + " "
                        + student.getPrezime()
                );
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
