package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.Arrays;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            Ispit[] ispiti, Integer ocjenaPismeniZavrsni,
            Integer ocjenaUsmeniZavrsni
    );

    default BigDecimal odrediProsjekOcjenaNaIspitima(
            Ispit[] ispiti
    ) {
        Integer zbrojOcjena = 0;

        for(Ispit ispit : ispiti) {
            zbrojOcjena += ispit.getOcjena();
        }

        return BigDecimal.valueOf(zbrojOcjena / ispiti.length);
    }

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){
        Ispit[] polozeniIspiti = new Ispit[0];

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena() > 1) {
                polozeniIspiti = Arrays.copyOf(polozeniIspiti, polozeniIspiti.length + 1);
            }
        }

        return polozeniIspiti;
    };

    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        Ispit[] ispitiStudenta = new Ispit[0];

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena() > 1 && ispit.getStudent().getJmbag().equals(student.getJmbag())) {
                ispitiStudenta = Arrays.copyOf(ispitiStudenta, ispitiStudenta.length + 1);
            }
        }

        return ispitiStudenta;
    }

}
