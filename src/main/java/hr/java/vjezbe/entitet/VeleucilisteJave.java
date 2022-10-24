package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

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
        Student najuspjesnijiStudent = new Student("", "", "", LocalDate.now());
        BigDecimal najboljiProsjek = BigDecimal.valueOf(0);

        for(Student student : this.getStudenti()) {
            Ispit[] ispitiStudenta = filtrirajIspitePoStudentu(
                    this.getIspiti(), student
            );

            BigDecimal prosjek = odrediProsjekOcjenaNaIspitima(ispitiStudenta);
            if(prosjek.compareTo(najboljiProsjek) >= 0) {
                najuspjesnijiStudent = student;
            }
        }

        return najuspjesnijiStudent;
    }


    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispiti,
            Integer ocjenaPismeniZavrsni,
            Integer ocjenaUsmeniZavrsni
    ) {
        return null;
    }
}
