package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Citac;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.util.List;

public class GlavnaDatoteke {

    public static void main(String[] args){
        List<Profesor> profesori = Citac.dodajProfesore();
        List<Student> studenti = Citac.dodajStudente();
        int x = 2;
    }
}
