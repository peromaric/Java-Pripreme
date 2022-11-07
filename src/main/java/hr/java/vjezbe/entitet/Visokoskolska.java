package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
        Ispit[] ispitiStudenta,
        int ocjenaZavrsnogRada,
        int ocjenaObraneRada
    ) throws NemoguceOdreditiProsjekStudentaException;

    void ispisiPodatkeOStudiju(Scanner scanner);

    default BigDecimal odrediProsjekOcjenaNaIspitima(
            Ispit[] ispiti
    ) throws NemoguceOdreditiProsjekStudentaException {
        int zbrojOcjena = 0;

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena().compareTo(1) == 0) {
                throw new NemoguceOdreditiProsjekStudentaException(
                        "Nemoguće odrediti prosjek studenta!"
                );
            }
            zbrojOcjena += ispit.getOcjena();
        }

        return BigDecimal.valueOf(zbrojOcjena / ispiti.length);
    }

    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti){
        Ispit[] polozeniIspiti = new Ispit[0];

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena() > 1) {
                polozeniIspiti = Arrays.copyOf(polozeniIspiti, polozeniIspiti.length + 1);
                polozeniIspiti[polozeniIspiti.length - 1] = ispit;
            }
        }

        return polozeniIspiti;
    }

    default Ispit[] filtrirajIspitePoGodini(Ispit[] ispiti, int godina){
        Ispit[] ispitiTeGodine = new Ispit[0];

        for(Ispit ispit : ispiti) {
            if(ispit.getDatumIVrijeme().getYear() == godina) {
                ispitiTeGodine = Arrays.copyOf(ispitiTeGodine, ispitiTeGodine.length + 1);
                ispitiTeGodine[ispitiTeGodine.length - 1] = ispit;
            }
        }

        return ispitiTeGodine;
    }

    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        Ispit[] ispitiStudenta = new Ispit[0];

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena() > 1 && ispit.getStudent().getJmbag().equals(student.getJmbag())) {
                ispitiStudenta = Arrays.copyOf(ispitiStudenta, ispitiStudenta.length + 1);
                ispitiStudenta[ispitiStudenta.length - 1] = ispit;
            }
        }

        return ispitiStudenta;
    }

}
