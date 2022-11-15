package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.plat.Plat;

public class Commande {

    protected int id_reservation;
    protected int id_plat;


    public Commande(int id_reservation, int id_plat) {
        this.id_reservation = id_reservation;
        this.id_plat = id_plat;
    }

    public Commande() {
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_plat() {
        return id_plat;
    }

    public void setId_plat(int id_plat) {
        this.id_plat = id_plat;
    }
}