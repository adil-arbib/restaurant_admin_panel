package com.team.restaurant_admin_panel.models.categorie;

import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categorie {

    protected int id_cat;
    protected String libelle;

    public Categorie(int id_categorie, String libelle) {
        this.id_cat = id_categorie;
        this.libelle = libelle;
    }
    public Categorie( String libelle) {
        this.libelle = libelle;
    }


    public int getId_categorie() {
        return id_cat;
    }

    public void setId_categorie(int id_categorie) {
        this.id_cat = id_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id_categorie=" + id_cat +
                ", libelle='" + libelle + '\'' +
                '}';
    }

}
