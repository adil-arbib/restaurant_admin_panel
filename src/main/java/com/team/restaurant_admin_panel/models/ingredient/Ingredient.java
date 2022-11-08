package com.team.restaurant_admin_panel.models.ingredient;

public class Ingredient {

    protected int id_ingred;
    protected String nom;
    protected Float qte;

    public Ingredient(int id_ing , String nom , Float quantite){
        this.id_ingred = id_ing;
        this.nom = nom;
        this.qte = quantite;
    }

    public Ingredient(String nom , Float quantite){
        this.nom = nom;
        this.qte = quantite;
    }

    public Ingredient(){};

    public int getId_ingred() {
        return id_ingred;
    }

    public void setId_ingred(int id_ingred) {
        this.id_ingred = id_ingred;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getQte() {
        return qte;
    }

    public void setQte(Float qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id_ingred=" + id_ingred +
                ", nom='" + nom + '\'' +
                ", qte=" + qte +
                '}';
    }
}
