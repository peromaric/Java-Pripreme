package hr.java.vjezbe.entitet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Citac {
    static final String PROFESORI_PATH = "dat/profesori.txt";
    static final String OBRAZOVNE_PATH = "dat/obrazovneUstanove.txt";
    static final String PREDMETI_PATH = "dat/predmeti.txt";
    static final String STUDENTI_PATH = "dat/studenti.txt";
    static final String ISPITI_PATH = "dat/ispiti.txt";

    public static List<Profesor> dodajProfesore() {
        List<Profesor> profesori = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(PROFESORI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            String sifra = "";
            String ime = "";
            String prezime = "";
            String titula = "";
            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> sifra = line;
                    case 3 -> ime = line;
                    case 4 -> prezime = line;
                    case 5 -> {
                        titula = line;
                        profesori.add(new Profesor(id, ime, prezime, sifra, titula));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return profesori;
    }

    public static List<Student> dodajStudente() {
        List<Student> studenti = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(STUDENTI_PATH))) {
            String line;
            int lineNumber = 0;

            long id = 0;
            String jmbag = "";
            String ime = "";
            String prezime = "";
            LocalDate datumRodjenja = LocalDate.EPOCH;
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
            while ((line = in.readLine()) != null) {
                lineNumber++;
                switch (lineNumber) {
                    case 1 -> id = Long.parseLong(line);
                    case 2 -> jmbag = line;
                    case 3 -> ime = line;
                    case 4 -> prezime = line;
                    case 5 -> {
                        datumRodjenja = LocalDate.parse(line, dateTimeFormatter);
                        studenti.add(new Student(id, ime, prezime, jmbag, datumRodjenja));
                        lineNumber = 0;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return studenti;
    }
}
