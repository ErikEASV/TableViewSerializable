package com.example.tableviewserializable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L; // Versionsnummer
    private int id;
    private String navn;
    private List<Adresse> adresser;

    public Person(int id, String navn) {
        this.id = id;
        this.navn = navn;
        this.adresser = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public List<Adresse> getAdresser() {
        return adresser;
    }

    public void addAdresse(Adresse adresse) {
        adresser.add(adresse);
    }

    @Override
    public String toString() {
        return navn;
    }
}
