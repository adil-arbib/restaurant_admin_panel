package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {


    public PlatDAO(int id, String nom, float price, String description, byte[] img, Categorie categorie) {
        super(id, nom, price, description, img, categorie);
    }

    public PlatDAO(String nom, float price, String description, byte[] img, Categorie categorie) {
        super(nom, price, description, img, categorie);
    }

    public PlatDAO() {
        super();
    }

    @Override
    public int add() throws SQLException, ParseException {
        return id;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
        return false;
    }

    @Override
    public boolean delete() throws SQLException {
        return false;
    }

    @Override
    public Object select() throws SQLException {
        return null;
    }
    //selecting list of plat of a reservation
    public static ArrayList<Plat> getListPlatById(int reservation_id) throws SQLException {
        Connection con= ResourcesManager.getConnection();
    PreparedStatement ps1 = con.prepareStatement("SELECT * from plat p left join commande c on p.id=c.id_plat " +
            "WHERE c.id_reservation=? ;");
    ps1.setInt(1, reservation_id);
    ResultSet rs1 = ps1.executeQuery();
        ArrayList<Plat> listPlat= new ArrayList<>();
        while (rs1.next()) {
        PreparedStatement psCat = con.prepareStatement("SELECT * from categorie cat right join plat p on cat.id=p.id_cat where p.id=? ");
        psCat.setInt(1, rs1.getInt(6));
        ResultSet rsCat = psCat.executeQuery();
        listPlat.add(new Plat(rs1.getInt(1), rs1.getString(2), rs1.getFloat(3),
                rs1.getString(4), rs1.getBytes(5), new CategorieDAO(rsCat.getInt(1), rsCat.getString(2))));
        }
        return listPlat;

    }
}







