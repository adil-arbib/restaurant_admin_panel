package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.plat.Plat;

public class Commande {

    protected int reservation_id;
    protected int id_plat;


    public Commande(int reservation_id, int id_plat) {
        this.reservation_id = Commande.this.reservation_id;
        this.id_plat = id_plat;
    }

    public int getreservation_id() {
        return reservation_id;
    }

    public int getId_plat() {
        return id_plat;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "reservation_id=" + reservation_id +
                ", id_plat=" + id_plat +
                '}';
    }
}
