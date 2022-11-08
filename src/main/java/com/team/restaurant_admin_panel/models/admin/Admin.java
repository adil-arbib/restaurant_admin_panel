package com.team.restaurant_admin_panel.models.admin;

public class Admin {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String username;
    protected String psw_ad;
    protected String cin;

    public Admin(int id, String nom, String prenom, String username, String psw_ad, String cin) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.psw_ad = psw_ad;
        this.cin = cin;
    }

    public Admin() {
    }

    public Admin(String nom, String prenom, String username, String psw_ad, String cin) {
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.psw_ad = psw_ad;
        this.cin = cin;
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

    public String getPsw_ad() {
        return psw_ad;
    }

    public void setPsw_ad(String psw_ad) {
        this.psw_ad = psw_ad;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", username='" + username + '\'' +
                ", psw_ad='" + psw_ad + '\'' +
                ", cin='" + cin + '\'' +
                '}';
    }
}

