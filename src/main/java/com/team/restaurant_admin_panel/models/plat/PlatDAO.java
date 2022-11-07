package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {

    public PlatDAO() {
    }

    public PlatDAO(int id, String nom, float price, String description, int id_cat) {
        super(id, nom, price, description, id_cat);
    }


    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into plat(nom,description,price ,id_cat) values(?,?,?,?)");
        ps.setString(1, nom);
        ps.setString(2, description);
        ps.setDouble(3, price);
        ps.setInt(4, id_category);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE plat SET nom = ? , id_cat =? , price=? ,description=? WHERE nom = ? ");
        ps.setString(1, nom);
        ps.setInt(2, id_category);
        ps.setFloat(3, price);
        ps.setString(4, description);
        ps.setString(5, nom);
        System.out.println(" object  updated");
        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE from plat where nom=? ");
        ps.setString(1, nom);
        return false;

    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from plat where nom=? and id_categorie=?");
        ps.setString(1, nom);
        ps.setInt(2, id_category);
        ResultSet s = ps.executeQuery();
        //int id, String nom, float price, String description, int id_cat
        if (s.next()) {
            return new Plat(
                    s.getInt(1),
                    s.getString(2),
                    s.getFloat(4),
                    s.getString(3),
                    s.getInt(5)
            );
        }
        return null;
    }


    public static ArrayList<Plat> getAll() throws SQLException {
        ArrayList<Plat> platList = new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        String sql = "SELECT * from plats";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            platList.add(new PlatDAO(
                    rs.getInt("id_plat"),
                    rs.getString("nom"),
                    rs.getFloat("price"),
                    rs.getString("description"),
                    rs.getInt("id_cat")
            ));

        }
        return platList;
    }
}


