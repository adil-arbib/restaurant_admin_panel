package com.team.restaurant_admin_panel.models.cuisinier;

public class Cuisinier {

    protected int id;
    protected String nom;
    protected String prenom;
    protected String cin;
    protected float salaire;

    public Cuisinier(int id, String nom, String prenom,  String cin, float salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.salaire = salaire;
    }

    public Cuisinier(String nom, String prenom, String cin, float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.salaire = salaire;
    }

    public Cuisinier() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Cuisinier{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", cin='" + cin + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}
