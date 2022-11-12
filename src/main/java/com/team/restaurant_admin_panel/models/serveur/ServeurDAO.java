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
    public int add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into serveur " +
                "(nom, prenom, username, psw_ser, cin, salaire) values (?,?,?,?,?,?)");
        PreparedStatement ps1 =con.prepareStatement("SELECT LAST_INSERT_ID();");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,username);
        ps.setString(4,psw_ser);
        ps.setString(5,cin);
        ps.setFloat(6,salaire);
        ps.executeUpdate();
        ResultSet rs1= ps1.executeQuery();
        while (rs1.next()){
            id=rs1.getInt(1);
        }
        return id;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("UPDATE serveur set nom=?, prenom=?, username=?, psw_ser=?,cin=?," +
                "salaire=? WHERE id = ?;");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,username);
        ps.setString(4,psw_ser);
        ps.setString(5,cin);
        ps.setFloat(6,salaire);
        ps.setInt(7,id);
        return ps.executeUpdate()>0;
    }

    @Override
    public  boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM serveur WHERE id=?;");
        ps.setInt(1,id );
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from serveur where id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Serveur(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getFloat(7));
        }
        return null;
    }

     //selecting a serveur by id of reservation

    public static Serveur getServeurById(int id_Reservation) throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT * from serveur s left join reservation r on s.id=r.id_ser where r.id=? ;");
        ps.setInt(1,id_Reservation);
        ResultSet rs= ps.executeQuery();
        Serveur serveur;
        if(rs.next()){
             serveur=new Serveur(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),
                    rs.getString(5),rs.getString(6),rs.getFloat(7));
            return serveur;
        }
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
