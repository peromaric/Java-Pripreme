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
        System.out.print("Unesite ocjenu na ispitu (1-5): ");
        int ocjenaUnos = Integer.parseInt(scanner.nextLine());

        Ocjena ocjena = null;
        switch (ocjenaUnos) {
            case 1:
                ocjena = Ocjena.JEDAN;
                break;
            case 2:
                ocjena = Ocjena.DVA;
                break;
            case 3:
                ocjena = Ocjena.TRI;
                break;
            case 4:
                ocjena = Ocjena.CETIRI;
                break;
            case 5:
                ocjena = Ocjena.PET;
                break;
            default:
                System.out.println("Ova ocjena ne postoji! Pokušajte ponovo!");
                unosOcjene(scanner);
        }

        return ocjena;
    }
}
