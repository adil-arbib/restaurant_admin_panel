package com.team.restaurant_admin_panel.models.serveur;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.admin.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServeurDAO extends Serveur implements Database {

    public ServeurDAO(String nom, String prenom, String CIN, String username, String psw , float salaire) {
        super(nom, prenom, CIN, username, psw,salaire);
    }

    @Override
    public boolean add() throws SQLException {

        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into serveur(nom,prenom,psw,cin,username, salaire) values(?,?,?,?,?,?)");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,psw);
        ps.setString(4,CIN);
        ps.setString(5,username);
        ps.setFloat(6,salaire);
        ps.executeUpdate();
        return false;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE serveur SET psw = ? , username=? , salaire=? WHERE nom = ? and prenom= ?");
        ps.setString(1,psw);
        ps.setString(2,username);
        ps.setFloat(3,salaire);
        ps.setString(4,nom);
        ps.setString(5,prenom);
        System.out.println(" object  updated");
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("DELETE from serveur where nom=? and prenom=?");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        return false;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from serveur where username=? and psw=?");
        ps.setString(1,username);
        ps.setString(2,psw);
        ResultSet s= ps.executeQuery();

        if(s.next()){
            return new Serveur(
                    s.getInt(1),
                    s.getString(2),
                    s.getString(3),
                    s.getString(4),
                    s.getString(5),
                    s.getString(6),
                    s.getInt(7)
            );
        }
        return null;
    }

    public static ArrayList<Serveur> getAll() throws SQLException {
        ArrayList<Serveur> serveurList = new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        String sql = "SELECT * from serveur";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            serveurList.add(new Serveur(
                    rs.getInt("id_ser"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("cin"),
                    rs.getString("username"),
                    rs.getString("psw"),
                    rs.getFloat("salaire")
            ));
        }
        return serveurList;
    }
}
