package hr.java.vjezbe.entitet;

import java.util.Scanner;

public interface Unos {

    static int unosIntegera(Scanner scanner, String message) {
        while(true) {
            try {
                System.out.println(message);
                System.out.print("Unos >> ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ex) {
                System.out.println("Pogrešan unos, pokušajte ponovo.");
            }
        }
    }

    static Ocjena unosOcjene(Scanner scanner) {
        try {
            System.out.print("Unesite ocjenu na ispitu (1-5): ");
            int ocjenaUnos = Integer.parseInt(scanner.nextLine());

            for(Ocjena ocjena : Ocjena.values()) {
                if(ocjena.getOcjena().compareTo(ocjenaUnos) == 0) {
                    return ocjena;
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Pogrešan unos, pokušajte ponovo.");
            return unosOcjene(scanner);
        }

        System.out.println("Pogrešan unos, pokušajte ponovo.");
        return unosOcjene(scanner);
    }
}
