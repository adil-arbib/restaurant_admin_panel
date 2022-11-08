package com.team.restaurant_admin_panel.models.reservations;

public class Reservations {
    protected int reservation_id;
    protected  int id_serv;
    protected String Date;
    protected float price_reservation;

    public Reservations(int reservation_id, int id_serv, String date, float price_reservation) {
        this.reservation_id = reservation_id;
        this.id_serv = id_serv;
        Date = date;
        this.price_reservation = price_reservation;
    }
    public Reservations( int id_serv, String date, float price_reservation) {
        this.id_serv = id_serv;
        Date = date;
        this.price_reservation = price_reservation;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public int getId_serv() {
        return id_serv;
    }

    public void setId_serv(int id_serv) {
        this.id_serv = id_serv;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public float getPrice_reservation() {
        return price_reservation;
    }

    public void setPrice_reservation(float price_reservation) {
        this.price_reservation = price_reservation;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "reservation_id=" + reservation_id +
                ", id_serv=" + id_serv +
                ", Date=" + Date +
                ", price_reservation=" + price_reservation +
                '}';
    }
}
