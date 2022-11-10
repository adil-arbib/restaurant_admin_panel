package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.table.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CommandeDAO extends Commande implements Database {


    public CommandeDAO(int id_reservation, int id_plat) {
        super(id_reservation, id_plat);
    }

    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("INSERT INTO commande(id_reservation,id_plat) values(?,?); ");
        ps.setInt(1,id_reservation);
        ps.setInt(2,id_plat);
        return ps.executeUpdate()>0;
    }


    @Override
    public boolean update() throws SQLException{
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("UPDATE commande SET id_plat=? WHERE id_reservation=?; ");
        ps.setInt(1,id_plat);
        ps.setInt(2,id_reservation);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM commande WHERE id_plat=? and id_reservation=?; ");
        ps.setInt(1,id_plat);
        ps.setInt(2,id_reservation);
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from commande where id_reservation=?");
        ps.setInt(1,id_reservation);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Commande(rs.getInt(1), rs.getInt(2));
        }
        return null;
    }

    public ArrayList<Commande> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from commande");
        ResultSet rs = ps.executeQuery();
        ArrayList<Commande> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Commande( rs.getInt(1), rs.getInt(2) ) );
        }
        return list;
    }
}
