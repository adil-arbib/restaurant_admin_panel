package com.team.restaurant_admin_panel.models.serveur;

public class Serveur {

    protected int id;
    protected int salaire;
    protected String nom;
    protected String Prenom;
    protected String CIN;
    protected String username;
    protected String psw;

    public Serveur(int id, String nom, String prenom, String CIN, String username, String psw,int salaire) {
        this.id = id;
        this.salaire=salaire;
        this.nom = nom;
        this.Prenom= prenom;
        this.CIN = CIN;
        this.username = username;
        this.psw = psw;
    }
    public Serveur(){}
    public Serveur( String nom, String prenom, String CIN, String username, String psw, int salaire) {
        this.nom = nom;
        this.salaire=salaire;
        this.Prenom= prenom;
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
        return Prenom ;
    }

    public void setPrenom(String prenom) {
        this.Prenom = prenom;
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

    public int getSalaire() {
        return salaire;
    }

    public void setSalaire(int salaire) {
        this.salaire = salaire;
    }

    @Override
    public String toString() {
        return "Serveur{" +
                "id=" + id +
                ", salaire=" + salaire +
                ", nom='" + nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", CIN='" + CIN + '\'' +
                ", username='" + username + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }
}
