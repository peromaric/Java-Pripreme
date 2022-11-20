package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladihStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Klasa koja predstavlja fakultet racunarstva
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski, Unos {
    private static final Logger logger = LoggerFactory.getLogger(FakultetRacunarstva.class);

    public FakultetRacunarstva() {
        super();
    }

    /**
     * Odreduje najuspjesnijeg studenta na toj godini
     * @param godina - integer kao godina
     * @return - najuspjesnijeg studenta
     */
    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.MIN);

        int najveciBrojIzvrsnoOcijenjenihIspita = 0;
        for(Student student : this.getStudenti()) {
            List<Ispit> ispitiStudenta = filtrirajIspitePoStudentu(
                    this.getIspiti(), student
            );

            if(ispitiStudenta.size() > 0) {
                List<Ispit> ispitiStudentaZaTuGodinu = filtrirajIspitePoGodini(ispitiStudenta, godina);
                if(ispitiStudentaZaTuGodinu.size() > 0) {
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

    /**
     * Odreduje broj izvrsno ocijenjenih ispita
     * @param ispiti - prima ispite
     * @return vraca broj izvrsno ocijenjenih
     */
    private int odrediBrojIzvrsnoOcijenjenihIspita(List<Ispit> ispiti) {
        int brojIzvrsnoOcijenjenihIspita = 0;

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena().equals(Ocjena.PET)) {
                brojIzvrsnoOcijenjenihIspita ++;
            }
        }

        return brojIzvrsnoOcijenjenihIspita;
    }

    /**
     * Odreduje studenta za rektorovu nagradu
     * @return - jednog studenta koji je dobio rektorovu nagradu
     */
    @Override
    public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladihStudenataException {
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.MIN);
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            try {
                List<Ispit> ispitiStudenta = filtrirajIspitePoStudentu(
                        this.getIspiti(), student
                );
                if(ispitiStudenta.size() > 0) {
                    BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
                    if(prosjek.compareTo(najboljiProsjek) > 0) {
                        najuspjesnijiStudent = student;
                        najboljiProsjek = prosjek;
                    } else if (prosjek.compareTo(najboljiProsjek) == 0) {
                        if(student.getDatumRodjenja().isBefore(najuspjesnijiStudent.getDatumRodjenja())) {
                            najuspjesnijiStudent = student;
                        } else if(student.getDatumRodjenja().isEqual(najuspjesnijiStudent.getDatumRodjenja())) {
                            throw new PostojiViseNajmladihStudenataException("Postoji vise najmladih studenata");
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

    /**
     * Izracunava konacnu ocjenu za studenta
     * @param ispitiStudenta - svi ispiti studenta
     * @return - prosjek svega
     * @throws NemoguceOdreditiProsjekStudentaException - baca gresku ako student ima 1
     */
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            List<Ispit> ispitiStudenta,
            Student student,
            Scanner scanner) throws NemoguceOdreditiProsjekStudentaException {
        String poruka;
        BigDecimal prosjekOcjena = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
        poruka = "Unesite ocjenu završnog rada studenta "
                + student.getIme() + " "
                + student.getPrezime();
        int ocjenaDiplomskogRada = Unos.unosIntegera(scanner, poruka);


        poruka = "Unesite ocjenu obrane rada studenta "
                + student.getIme() + " "
                + student.getPrezime();
        int ocjenaObraneRada = Unos.unosIntegera(scanner, poruka);

        BigDecimal konacnaOcjena = BigDecimal.valueOf(
                ((prosjekOcjena.doubleValue() * 3) + ocjenaObraneRada + ocjenaDiplomskogRada) / 5
        );
        return konacnaOcjena.round(new MathContext(1));
    }

    /**
     * Ispisuje sve podatke o cijelom studiju
     * @param scanner - koristi za unos podataka o zavrsnom radu i obrani
     */
    public void ispisiPodatkeOStudiju(
            Scanner scanner
    ) {
        for(Student student : getStudenti()) {
            try {
                List<Ispit> ispitiStudenta = filtrirajIspitePoStudentu(getIspiti(), student);
                if (ispitiStudenta.size() > 0) {
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
                logger.info("Student" + student.getIme() + "ima ocjenu nedovoljan");
                logger.debug("Nemoguće odrediti prosjek ocjena za studenta "
                        + student.getIme() + " "
                        + student.getPrezime()
                );
            }
        }

        try {
            Student dobitnikRektorove = odrediStudentaZaRektorovuNagradu();
            if(!dobitnikRektorove.getJmbag().isEmpty()) {
                System.out.printf(
                        "Dobitnik rektorove nagrada je %s %s!\n",
                        dobitnikRektorove.getIme(),
                        dobitnikRektorove.getPrezime()
                );
            } else {
                System.out.println("Nitko nije dobio rektorovu nagradu.");
            }
        } catch(PostojiViseNajmladihStudenataException ex) {
            logger.debug(ex.toString());
            System.out.println("Postoji vise najmladih studenata! Izlazim iz programa.");
            System.exit(1);
        }

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
}
