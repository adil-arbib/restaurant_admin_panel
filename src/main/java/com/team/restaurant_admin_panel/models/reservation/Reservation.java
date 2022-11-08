package com.team.restaurant_admin_panel.models.reservation;

public class Reservation {
    protected int id;
    protected String date;
    protected float price;
    protected int id_ser;
    protected int id_table;


    public Reservation(int id, String date, float price, int id_ser, int id_table) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.id_ser = id_ser;
        this.id_table = id_table;
    }

    public Reservation(String date, float price, int id_ser, int id_table) {
        this.date = date;
        this.price = price;
        this.id_ser = id_ser;
        this.id_table = id_table;
    }

    public Reservation() {
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

    public int getId_ser() {
        return id_ser;
    }

    public void setId_ser(int id_ser) {
        this.id_ser = id_ser;
    }

    public int getId_table() {
        return id_table;
    }

    public void setId_table(int id_table) {
        this.id_table = id_table;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", price=" + price +
                ", id_ser=" + id_ser +
                ", id_table=" + id_table +
                '}';
    }
}
