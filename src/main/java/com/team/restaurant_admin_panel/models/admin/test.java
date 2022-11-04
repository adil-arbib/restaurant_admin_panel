package com.team.restaurant_admin_panel.models.admin;

import java.sql.SQLException;

public class test {

    public static void main(String[] args) throws SQLException {
        AdminDAO ad= new AdminDAO("adil","adddd","S5665566","sskskskks","12345");
        System.out.println(ad.select());

    }
}
