package com.team.restaurant_admin_panel.models.serveur;

public class Serveur {

    protected int id;
    protected float salaire;
    protected String nom;
    protected String prenom;
    protected String CIN;
    protected String username;
    protected String psw;

    public Serveur(int id, String nom, String prenom,String psw, String CIN, String username, float salaire) {
        this.id = id;
        this.salaire=salaire;
        this.nom = nom;
        this.prenom= prenom;
        this.CIN = CIN;
        this.username = username;
        this.psw = psw;
    }

    public Serveur(){}

    public Serveur(String nom, String prenom,String psw, String CIN, String username, float salaire) {
        this.salaire = salaire;
        this.nom = nom;
        prenom = prenom;
        this.CIN = CIN;
        this.username = username;
        this.psw = psw;
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
        return prenom ;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
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
                ", salaire=" + salaire +
                ", nom='" + nom + '\'' +
                ", Prenom='" + prenom + '\'' +
                ", CIN='" + CIN + '\'' +
                ", username='" + username + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
