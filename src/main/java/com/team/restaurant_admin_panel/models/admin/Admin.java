package com.team.restaurant_admin_panel.models.admin;

public class Admin {
    protected int id;
    protected String name;
    String psw;
    int j;

    public Admin(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
