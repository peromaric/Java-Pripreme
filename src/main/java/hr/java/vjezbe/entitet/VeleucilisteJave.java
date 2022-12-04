package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska {
    private static final Logger logger = LoggerFactory.getLogger(VeleucilisteJave.class);

    public VeleucilisteJave(Long id, String naziv, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(id, naziv, predmeti, profesori, studenti, ispiti);
    }

    /**
     * Odreduje najuspjesnijeg studenta na toj godini
     * @param godina - integer godine
     * @return - najuspjesnijeg studenta
     */
    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Student najuspjesnijiStudent = new Student(0L, "", "", "", LocalDate.MIN);
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            try {
                List<Ispit> ispitiStudenta = filtrirajIspitePoStudentu(
                        this.getIspiti(), student
                );
                if(ispitiStudenta.size() > 0) {
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
            List<Ispit> ispitiStudenta,
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
                List<Ispit> ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
                if(ispitiStudenta.size() > 0) {
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
}
