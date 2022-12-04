package hr.java.vjezbe.entitet;

import hr.java.vjezbe.sortiranje.StudentSorter;

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

    public List<Student> getSortedStudents() {
        return getStudentiList().stream().sorted(new StudentSorter()).toList();
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
