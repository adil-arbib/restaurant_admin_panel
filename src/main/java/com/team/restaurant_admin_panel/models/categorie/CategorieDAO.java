package com.team.restaurant_admin_panel.models.categorie;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDAO extends Categorie implements Database {

    public CategorieDAO(int id_categorie, String libelle) {
        super(id_categorie, libelle);
    }

    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("INSERT INTO categorie values(?)");
        ps.setString(1,libelle);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE categorie SET libelle= ? WHERE id_categorie=?");
        ps.setString(1,libelle);
        ps.setInt(2,id_cat);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM categorie  WHERE id_categorie=? and libelle =?");
        ps.setInt(1,id_cat);
        ps.setString(2,libelle);
        return ps.execute();

    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from categorie where id_categorie=? ");
        ps.setInt(1,id_cat);
        ResultSet s= ps.executeQuery();
        if(s.next()){
            return new Categorie(
                    s.getInt(1),
                    s.getString(2));
        }

        return null;
    }

    public static ArrayList<Categorie> getAll() throws SQLException {

        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT * from categorie");
        ResultSet rs = ps.executeQuery();
        ArrayList<Categorie> CategorieList = new ArrayList<>();

        while (rs.next()) {
            CategorieList.add(new Categorie(
                    rs.getInt("id_cat"),
                    rs.getString("libelle")
            ));
        }
        return CategorieList;
    }


    public static ArrayList<Categorie> getCatLibelle() throws SQLException{

        Connection connection = ResourcesManager.getConnection();
        PreparedStatement ps = connection.prepareStatement(
                "SELECT categorie.libelle FROM categorie " +
                        "LEFT JOIN plats ON plats.id_cat = categorie.id_cat");
        ResultSet rs = ps.executeQuery();
        ArrayList<Categorie> libList = new ArrayList<>();
        if(rs.next()){
            libList.add(new CategorieDAO(
                    rs.getInt("id_cat"),
                    rs.getString("libelle")
            ));
        }
        return  libList;

    }


}

