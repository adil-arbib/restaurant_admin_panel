package com.team.restaurant_admin_panel.models.plat;

public class Plat {
    protected int id;
    protected String nom;
    protected float price;
    protected String description;
    protected byte[] img;
    protected int id_category;

    public Plat(int id, String nom, float price, String description, byte[] img, int id_category) {
        this.id = id;
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.id_category = id_category;
    }

    public Plat(String nom, float price, String description, byte[] img, int id_category) {
        this.nom = nom;
        this.price = price;
        this.description = description;
        this.img = img;
        this.id_category = id_category;
    }

    public Plat() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

}

