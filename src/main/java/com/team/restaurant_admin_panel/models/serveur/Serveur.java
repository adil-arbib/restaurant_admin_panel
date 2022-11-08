package com.team.restaurant_admin_panel.models.serveur;

public class Serveur {

    protected int id;
    protected String nom;
    protected String prenom;
    protected String username;
    protected String psw_ser;
    protected String cin;
    protected float salaire;

    public Serveur(int id, String nom, String prenom, String username, String psw_ser, String cin, float salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.psw_ser = psw_ser;
        this.cin = cin;
        this.salaire = salaire;
    }

    public Serveur(String nom, String prenom, String username, String psw_ser, String cin, float salaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.psw_ser = psw_ser;
        this.cin = cin;
        this.salaire = salaire;
    }

    public Serveur() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw_ser() {
        return psw_ser;
    }

    public void setPsw_ser(String psw_ser) {
        this.psw_ser = psw_ser;
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
        return "Serveur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", username='" + username + '\'' +
                ", psw_ser='" + psw_ser + '\'' +
                ", cin='" + cin + '\'' +
                ", salaire=" + salaire +
                '}';
    }
}
