package com.team.restaurant_admin_panel.models.serveur;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServeurDAO extends Serveur implements Database {

    public ServeurDAO(String nom, String Prenom, String CIN, String username, String psw , int salaire) {
        super(nom, Prenom, CIN, username, psw,salaire);
    }

    @Override
    public boolean add() throws SQLException {

        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into serveur(username,prenom,nom,psw,cin) values(?,?,?,?,?)");
        ps.setString(1,username);
        ps.setString(2,Prenom);
        ps.setString(3,nom);
        ps.setString(4,psw);
        ps.setString(5,CIN);
        ps.executeUpdate();
        return false;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE serveur SET psw = ? , username=? , salaire=? WHERE nom = ? and prenom= ?");
        ps.setString(1,psw);
        ps.setString(2,username);
        ps.setInt(3,salaire);
        ps.setString(4,nom);
        ps.setString(5,Prenom);
        System.out.println(" object  updated");
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("DELETE from serveur where nom=? and prenom=?");
        ps.setString(1,nom);
        ps.setString(2,Prenom);
        return false;
    }

    @Override
    public boolean select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from serveur where username=? and psw=?");
        ps.setString(1,username);
        ps.setString(2,psw);
        ResultSet s= ps.executeQuery();
        System.out.println(s.next());
        return false;
    }

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        ArrayList<Object> serveurList=new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        String sql = "SELECT * from serveur";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Serveur serv = new Serveur();
            serv.setId(rs.getInt("id_Ser"));
            serv.setNom(rs.getString("nom"));
            serv.setPrenom(rs.getString("prenom"));
            serv.setCIN(rs.getString("cin"));
            serv.setUsername(rs.getString("username"));
            serv.setPsw(rs.getString("psw"));
            serveurList.add(serv);
        }
        return serveurList;
    }
}
