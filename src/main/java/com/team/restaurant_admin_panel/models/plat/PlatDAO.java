package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {


    public PlatDAO(int id, String nom, double prix) {
        super(id, nom, prix);
    }

    public PlatDAO(Plat plat){
        super(plat.getId(),plat.getNom(),plat.getPrix());
    }

    public PlatDAO(){}

    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into plat values(?,?,?)");
        ps.setInt(1,id);
        ps.setString(2,nom);
        ps.setDouble(3,prix);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public boolean select() throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Object> getAll() {
        return null;
    }
}
