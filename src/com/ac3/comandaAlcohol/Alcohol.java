package com.ac3.comandaAlcohol;

public class Alcohol {
    private String nom;
    private double graduation;
    private String procedence;
    private int year;
    private int type;
    private Founder[] founders;
    private int[] combinations;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getGraduation() {
        return graduation;
    }

    public void setGraduation(double graduation) {
        this.graduation = graduation;
    }

    public String getProcedence() {
        return procedence;
    }

    public void setProcedence(String procedence) {
        this.procedence = procedence;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Founder[] getFounders() {
        return founders;
    }

    public void setFounders(Founder[] founders) {
        this.founders = founders;
    }

    public int[] getCombinations() {
        return combinations;
    }

    public void setCombinations(int[] conmbinations) {
        this.combinations = conmbinations;
    }
}
