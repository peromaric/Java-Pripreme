package hr.java.vjezbe.entitet;

import java.util.*;

public class Predmet implements Unos {

    private String sifra;
    private String naziv;
    private Integer brojEctsBodova;
    private Profesor nositelj;
    private Set<Student> studenti;

    public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.brojEctsBodova = brojEctsBodova;
        this.nositelj = nositelj;
        this.studenti = new HashSet<>();
    }

    public static Predmet inputPredmet(Scanner scanner, List<Profesor> profesori) {
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
        int indexProfesora = 0;
        for (Profesor profesor : profesori) {
            odabirProfesoraPorukaBuilder.append(++indexProfesora).append(".");
            odabirProfesoraPorukaBuilder.append(profesor.getIme()).append(" ");
            odabirProfesoraPorukaBuilder.append(profesor.getPrezime()).append("\n");
        }
        String odabirProfesoraPoruka = odabirProfesoraPorukaBuilder.toString();
        int odabir = Unos.unosIntegera(scanner, odabirProfesoraPoruka);
        nositelj = profesori.get(odabir - 1);

        return new Predmet(sifra, naziv, brojEctsBodova, nositelj);
    }
    
    public void inputStudenti(Scanner scanner, List<Student> sviStudenti) {
        int brojStudenata = Unos.unosIntegera(
                scanner,
                "Unesite broj studenata na " + this.getNaziv()
        );
        System.out.printf("Odaberite studente koji su upisali %s:\n", this.getNaziv());
        for(int i = 0; i < brojStudenata; i++) {
            for(Student student : sviStudenti) {
                System.out.printf("%d. %s %s\n",
                        sviStudenti.indexOf(student) + 1,
                        student.getIme(),
                        student.getPrezime()
                );
            }
            int odabir = Unos.unosIntegera(
                    scanner,
                    ""
            );
            this.studenti.add(sviStudenti.get(odabir - 1));
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

    public Set<Student> getStudenti() {
        return studenti;
    }

    public List<Student> getStudentiList() {
        return studenti.stream().toList();
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }
}
