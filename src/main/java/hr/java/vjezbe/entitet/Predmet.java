package hr.java.vjezbe.entitet;

import java.util.Scanner;

public class Predmet implements Unos{

    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Profesor nositelj;
    private Student[] studenti;

    public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.nositelj = nositelj;
    }

    public static Predmet inputPredmet(Scanner scanner, Profesor[] profesori) {
        String sifra;
        String naziv;
        int brojEctsBodova;
        Profesor nositelj;

        System.out.print("Unesite šifru predmeta: ");
        sifra = scanner.nextLine();

        System.out.print("Unesite naziv predmeta: ");
        naziv = scanner.nextLine();

        brojEctsBodova = Unos.unosIntegera(
                scanner,
                "Unesite broj ECTS-a za predmet " + naziv
        );

        StringBuilder odabirProfesoraPorukaBuilder = new StringBuilder("Odaberite profesora:\n");
        for (Profesor profesor : profesori) {
            odabirProfesoraPorukaBuilder.append(profesor.getIme());
            odabirProfesoraPorukaBuilder.append(" ");
            odabirProfesoraPorukaBuilder.append(profesor.getPrezime());
            odabirProfesoraPorukaBuilder.append("\n");
        }
        String odabirProfesoraPoruka = odabirProfesoraPorukaBuilder.toString();
        int odabir = Unos.unosIntegera(scanner, odabirProfesoraPoruka);
        nositelj = profesori[odabir-1];

        return new Predmet(sifra, naziv, brojEctsBodova, nositelj);
    }
    
    public void inputStudenti(Scanner scanner, Student[] sviStudenti) {
        Student[] studenti = this.getStudenti();

        System.out.printf("Odaberite studente koji su upisali %s:\n", this.getNaziv());
        for(int i = 0; i < studenti.length; i++) {
            for(int j = 0; j < sviStudenti.length; j++) {
                System.out.printf("%d. %s %s\n",
                        j + 1,
                        sviStudenti[j].getIme(),
                        sviStudenti[j].getPrezime()
                );
            }
            System.out.print("Odabir >>> ");
            int odabir = Integer.parseInt(scanner.nextLine());
            studenti[i] = sviStudenti[odabir - 1];
        }
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Integer getBrojEctsBodova() {
        return brojEctsBodova;
    }

    public void setBrojEctsBodova(Integer brojEctsBodova) {
        this.brojEctsBodova = brojEctsBodova;
    }

    public Profesor getNositelj() {
        return nositelj;
    }

    public void setNositelj(Profesor nositelj) {
        this.nositelj = nositelj;
    }

    public Student[] getStudenti() {
        return studenti;
    }

    public void setStudenti(Student[] studenti) {
        this.studenti = studenti;
    }
}
