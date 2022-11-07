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
}
