package hr.java.vjezbe.entitet;

public enum Ocjena {

    JEDAN(1, "nedovoljan"),
    DVA(2, "dovoljan"),
    TRI(3, "dobar"),
    CETIRI(4, "vrlo dobar"),
    PET(5, "odličan");

    private final Integer ocjena;
    private final String naziv;

    Ocjena(Integer ocjena, String naziv) {
        this.ocjena = ocjena;
        this.naziv = naziv;
    }

    public Integer getOcjena() {
        return ocjena;
    }

    public String getNaziv() {
        return naziv;
    }
}
