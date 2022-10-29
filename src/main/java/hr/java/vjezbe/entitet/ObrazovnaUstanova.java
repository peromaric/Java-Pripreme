package hr.java.vjezbe.entitet;

public abstract class ObrazovnaUstanova {

    private String naziv;
    private Predmet[] predmeti;
    private Profesor[] profesori;
    private Student[] studenti;
    private Ispit[] ispiti;

    public ObrazovnaUstanova(
            String naziv, Predmet[] predmeti,
            Profesor[] profesori, Student[] studenti,
            Ispit[] ispiti
    ) {
        this.naziv = naziv;
        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina);

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Predmet[] getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(Predmet[] predmeti) {
        this.predmeti = predmeti;
    }

    public Profesor[] getProfesori() {
        return profesori;
    }

    public void setProfesori(Profesor[] profesori) {
        this.profesori = profesori;
    }

    public Student[] getStudenti() {
        return studenti;
    }

    public void setStudenti(Student[] studenti) {
        this.studenti = studenti;
    }

    public Ispit[] getIspiti() {
        return ispiti;
    }

    public void setIspiti(Ispit[] ispiti) {
        this.ispiti = ispiti;
    }

    public static class Builder {
        private String naziv;
        private Predmet[] predmeti;
        private Profesor[] profesori;
        private Student[] studenti;
        private Ispit[] ispiti;

        public Builder(String naziv, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
            this.naziv = naziv;
            this.predmeti = predmeti;
            this.profesori = profesori;
            this.studenti = studenti;
            this.ispiti = ispiti;
        }

        public Builder() {}

        public Builder withNaziv(String naziv) {
            this.naziv = naziv;
            return this;
        }

        public Builder withPredmeti(Predmet[] predmeti) {
            this.predmeti = predmeti;
            return this;
        }

        public Builder withProfesori(Profesor[] profesori) {
            this.profesori = profesori;
            return this;
        }

        public Builder withStudenti(Student[] studenti) {
            this.studenti = studenti;
            return this;
        }

        public Builder withIspiti(Ispit[] ispiti) {
            this.ispiti = ispiti;
            return this;
        }

        public String getNaziv() {
            return naziv;
        }

        public Predmet[] getPredmeti() {
            return predmeti;
        }

        public Profesor[] getProfesori() {
            return profesori;
        }

        public Student[] getStudenti() {
            return studenti;
        }

        public Ispit[] getIspiti() {
            return ispiti;
        }
    }
}
