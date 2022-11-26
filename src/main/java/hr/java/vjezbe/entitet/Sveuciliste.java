package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sveuciliste <T extends ObrazovnaUstanova> {
    List<T> obrazovneUstanove;

    public Sveuciliste() {
        obrazovneUstanove = new ArrayList<>();
    }

    public void dodajObrazovnuUstanovu(T obrazovnaUstanova) {
        obrazovneUstanove.add(obrazovnaUstanova);
    }

    public T dohvatiObrazovnuUstanovu(int i) {
        return obrazovneUstanove.get(i);
    }

    public List<T> getObrazovneUstanove() {
        return obrazovneUstanove;
    }

    public void ispisiPodatke(Scanner scanner) {
        for (T obrazovnaUstanova : obrazovneUstanove) {
            obrazovnaUstanova.ispisiPodatkeOStudiju(scanner);
        }
    }
}
