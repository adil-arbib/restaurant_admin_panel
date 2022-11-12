package com.team.restaurant_admin_panel.models.reservation;

import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.table.Table;

import java.util.ArrayList;

public class Reservation {
    protected int id;
    protected String date;
    protected float price;
    protected  Serveur serveur;
    protected  Table table;
    protected  ArrayList<Plat> listPlat;

    public Reservation(int id, String date, float price, Serveur serveur, Table table, ArrayList<Plat> listPlat) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.serveur = serveur;
        this.table = table;
        this.listPlat = listPlat;
    }
    public Reservation(String date, float price, Serveur serveur, Table table, ArrayList<Plat> listPlat) {
        this.date = date;
        this.price = price;
        this.serveur = serveur;
        this.table = table;
        this.listPlat = listPlat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Serveur getServeur() {
        return serveur;
    }

    public void setServeur(Serveur serveur) {
        this.serveur = serveur;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ArrayList<Plat> getListPlat() {
        return listPlat;
    }

    public void setListPlat(ArrayList<Plat> listPlat) {
        this.listPlat = listPlat;
    }

    public Reservation() {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", serveur=" + serveur +
                ", table=" + table +
                ", listPlat=" + listPlat +
                '}';
    }
}
