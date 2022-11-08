package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.plat.Plat;

public class Commande {

    protected int id_reservation;
    protected int id_plat;


    public Commande(int id_reservation, int id_plat) {
        this.id_reservation = id_reservation;
        this.id_plat = id_plat;
    }
}
