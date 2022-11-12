package com.team.restaurant_admin_panel.models.categorie;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategorieDAO extends Categorie implements Database {

    public CategorieDAO(int id, String libelle) {
        super(id, libelle);
    }

    public CategorieDAO(String libelle) {
        super(libelle);
    }

    public CategorieDAO() {
    }

    @Override
    public int add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into categorie " +
                "(libelle) values (?)");
        ps.setString(1,libelle);
        ps.executeUpdate();
        PreparedStatement ps1 =con.prepareStatement("SELECT LAST_INSERT_ID();");
        ResultSet rs1= ps1.executeQuery();
        while (rs1.next()){
            id=rs1.getInt(1);
        }
        return id ;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("update categorie " +
                "set libelle = ? where id = ?");
        ps.setString(1,libelle);
        ps.setInt(2,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM categorie WHERE id = ?;");
        ps.setInt(1,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from categorie where id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Categorie(rs.getInt(1),rs.getString(2));
        }
        return null;
    }

    public static ArrayList<Categorie> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from categorie");
        ResultSet rs = ps.executeQuery();
        ArrayList<Categorie> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Categorie(
                    rs.getInt(1),
                    rs.getString(2)
            ));
        }
        return list;
    }
}
