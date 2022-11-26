package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public interface Visokoskolska {

    BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(
            List<Ispit> ispitiStudenta,
            Student student,
            Scanner scanner
    ) throws NemoguceOdreditiProsjekStudentaException;

    default BigDecimal odrediProsjekOcjenaNaIspitima(
            List<Ispit> ispiti
    ) throws NemoguceOdreditiProsjekStudentaException {
        int zbrojOcjena = 0;

        for(Ispit ispit : ispiti) {
            if(ispit.getOcjena().compareTo(Ocjena.JEDAN) == 0) {
                throw new NemoguceOdreditiProsjekStudentaException(
                        "Nemoguće odrediti prosjek studenta!"
                );
            }
            zbrojOcjena += ispit.getOcjena().getOcjena();
        }

        return BigDecimal.valueOf(zbrojOcjena / ispiti.size());
    }

    default List<Ispit> filtrirajIspitePoGodini(List<Ispit> ispiti, int godina) {
        List<Ispit> ispitiTeGodine = new ArrayList<>();

        for(Ispit ispit : ispiti) {
            if(ispit.getDatumIVrijeme().getYear() == godina) {
                ispitiTeGodine.add(ispit);
            }
        }

        return ispitiTeGodine;
    }

    default List<Ispit> filtrirajIspitePoStudentu(
            List<Ispit> ispiti,
            Student student
    ) {
        List<Ispit> ispitiStudenta = new ArrayList<>();

        ispiti.stream()
                .filter(ispit -> ispit.getStudent().equals(student))
                .forEach(ispitiStudenta::add);

        for(Ispit ispit : ispiti) {
            if (ispit.getStudent().equals(student)) {
                ispitiStudenta.add(ispit);
            }
        }

        return ispitiStudenta;
    }

}
