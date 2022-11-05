package com.team.restaurant_admin_panel.models.admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {

    public static void main(String[] args) throws SQLException {
        /*AdminDAO ad= new AdminDAO("adil","adddd","S5665566","sskskskks","12345");
        System.out.println(ad.select());
        AdminDAO ad2= new AdminDAO("issam","sss","S5665566","sskskskks","12345");
        */
        AdminDAO ad= new AdminDAO("admin1","rrrrrr","R789797","user789","79789456");
        ad.add();
        ArrayList<Object> listes= new  ArrayList<>();
        listes=ad.getAll();
        System.out.println(listes);
        ad.setPsw("456789");
        ad.update();

    }
}
