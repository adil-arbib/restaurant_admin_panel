package com.team.restaurant_admin_panel.models.ingredient;

public class Ingredient {
    protected int id;
    protected String nom;
    protected String date;
    protected float qte;
    protected float unitPrice;

    public Ingredient(int id, String nom, String date, float qte, float unitPrice) {
        this.id = id;
        this.nom = nom;
        this.date = date;
        this.qte = qte;
        this.unitPrice = unitPrice;
    }

    public Ingredient(String nom, String date, float qte, float unitPrice) {
        this.nom = nom;
        this.date = date;
        this.qte = qte;
        this.unitPrice = unitPrice;
    }

    public Ingredient() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getQte() {
        return qte;
    }

    public void setQte(float qte) {
        this.qte = qte;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }
}
