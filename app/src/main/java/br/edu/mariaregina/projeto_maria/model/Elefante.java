package br.edu.mariaregina.projeto_maria.model;

import java.io.Serializable;

public class Elefante implements Serializable {
    private String name;
    private String affiliation;
    private String species;
    private String sex;
    private String note;

    public Elefante(String name, String affiliation, String species, String sex, String note) {
        this.name = name;
        this.affiliation = affiliation;
        this.species = species;
        this.sex = sex;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

