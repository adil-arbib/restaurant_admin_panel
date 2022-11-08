package com.team.restaurant_admin_panel.models.table;

public class Table {
    protected int id;
    protected int num;

    public Table(int id, int num) {
        this.id = id;
        this.num = num;
    }

    public Table(int num) {
        this.num = num;
    }

    public Table() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", num=" + num +
                '}';
    }
}
