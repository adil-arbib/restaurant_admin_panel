package com.team.restaurant_admin_panel.models.admin;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.*;
import java.util.ArrayList;
//import java.util.ArrayList;

public class AdminDAO extends Admin implements Database {


    public AdminDAO(int id, String nom, String prenom, String username, String psw_ad, String cin) {
        super(id, nom, prenom, username, psw_ad, cin);
    }

    public AdminDAO() {
    }

    public AdminDAO(String nom, String prenom, String username, String psw_ad, String cin) {
        super(nom, prenom, username, psw_ad, cin);
    }

    @Override
    public int add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into admin " +
                "(nom, prenom, username, psw_ad, cin) values (?,?,?,?,?)");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,username);
        ps.setString(4,psw_ad);
        ps.setString(5,cin);
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
        PreparedStatement ps = con.prepareStatement("update admin " +
                "set nom = ?, prenom = ?, username = ?, psw_ad = ?, cin = ? where id = ?");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,username);
        ps.setString(4,psw_ad);
        ps.setString(5,cin);
        ps.setInt(6,id);
        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM admin WHERE id = ?;");
        ps.setInt(1,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from admin where username = ? and psw_ad = ?");
        ps.setString(1,username);
        ps.setString(2,psw_ad);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            return new Admin(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            );
        }
        return null;
    }

    public static ArrayList<Admin> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from admin");
        ResultSet rs = ps.executeQuery();
        ArrayList<Admin> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Admin(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
            ));
        }
        return list;
    }
}


