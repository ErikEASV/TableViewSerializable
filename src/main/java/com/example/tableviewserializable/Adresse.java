package com.example.tableviewserializable;
import java.io.Serializable;

public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L; // Versionsnummer
    private String vej;
    private int postnr;
    private String by;

    public Adresse(String vej, int postnr, String by) {
        this.vej = vej;
        this.postnr = postnr;
        this.by = by;
    }

    public String getVej() {
        return vej;
    }

    public int getPostnr() {
        return postnr;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return vej + ", " + postnr + " " + by;
    }
}
