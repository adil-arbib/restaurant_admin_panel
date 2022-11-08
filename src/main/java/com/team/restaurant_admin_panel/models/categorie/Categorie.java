package com.team.restaurant_admin_panel.models.categorie;

import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Categorie {

    protected int id;
    protected String libelle;

    public Categorie(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }

    public Categorie() {
    }

    @Override
    public String toString() {
        return "Categorie{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
