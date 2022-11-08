package com.team.restaurant_admin_panel.models.serveur;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import com.team.restaurant_admin_panel.models.categorie.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class ServeurDAO extends Serveur implements Database {

    public ServeurDAO(int id, String nom, String prenom, String username, String psw_ser, String cin, float salaire) {
        super(id, nom, prenom, username, psw_ser, cin, salaire);
    }

    public ServeurDAO(String nom, String prenom, String username, String psw_ser, String cin, float salaire) {
        super(nom, prenom, username, psw_ser, cin, salaire);
    }

    public ServeurDAO() {
    }

    @Override
    public boolean add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into serveur " +
                "(nom, prenom, username, psw_ser, cin, salaire) values (?,?,?,?,?,?)");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,username);
        ps.setString(4,psw_ser);
        ps.setString(5,cin);
        ps.setFloat(6,salaire);
        return ps.executeUpdate() > 0;
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

    public static ArrayList<Serveur> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from serveur");
        ResultSet rs = ps.executeQuery();
        ArrayList<Serveur> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Serveur(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getFloat(7)
            ));
        }
        return list;
    }
}
