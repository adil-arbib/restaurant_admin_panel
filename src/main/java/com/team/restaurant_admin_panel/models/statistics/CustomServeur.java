package com.team.restaurant_admin_panel.models.statistics;

public class CustomServeur {
     String Nom;
     String Prenom;
     Integer Total;

    @Override
    public String toString() {
        return "CustomServeur{" +
                "Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Total=" + Total +
                '}';
    }

    public CustomServeur(String nom, String prenom, Integer total) {
        Nom = nom;
        Prenom = prenom;
        Total = total;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public Integer getTotal() {
        return Total;
    }

    public void setTotal(Integer total) {
        Total = total;
    }
}
