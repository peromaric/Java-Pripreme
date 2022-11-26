package hr.java.vjezbe.sortiranje;

import hr.java.vjezbe.entitet.Student;

import java.util.Comparator;

public class StudentSorter implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        int prezimenaIndex = s1.getPrezime().compareTo(s2.getPrezime());
        int imenaIndex = s1.getIme().compareTo(s2.getIme());

        if(prezimenaIndex < 0) {
            return -1;
        } else if(prezimenaIndex > 0) {
            return 1;
        } else {
            return Integer.compare(imenaIndex, 0);
        }
    }
}
