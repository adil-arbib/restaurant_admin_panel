package com.team.restaurant_admin_panel.models.admin;

public class Admin {
    protected int id;
    protected String nom;
    protected String prenom;
    protected String CIN;
    protected String username;
    protected String psw;

    public Admin(){}
    public Admin(int id, String nom, String prenom, String CIN, String username, String psw) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.CIN = CIN;
        this.username = username;
        this.psw = psw;
    }
    public Admin( String nom, String prenom, String CIN, String username, String psw) {

        this.nom = nom;
        this.prenom = prenom;
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
        return prenom;
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

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", CIN='" + CIN + '\'' +
                ", username='" + username + '\'' +
                ", psw='" + psw + '\'' +
                '}';
    }

    ;}

