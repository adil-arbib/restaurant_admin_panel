package com.team.restaurant_admin_panel.models.admin;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.*;
import java.util.ArrayList;

public class AdminDAO extends Admin implements Database {

    public AdminDAO(int id, String nom, String prenom, String CIN, String username, String psw) {
        super(id, nom,prenom,CIN,username,psw);
    }
    public AdminDAO( String nom, String prenom, String CIN, String username, String psw) {
        super( nom,prenom,CIN,username,psw);
    }
    public AdminDAO(){
        super();
    };

    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into admin(username,prenom,nom,psw_ad,cin) values(?,?,?,?,?)");
        ps.setString(1,username);
        ps.setString(2,prenom);
        ps.setString(3,nom);
        ps.setString(4,psw);
        ps.setString(5,CIN);

        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean update() throws SQLException {
        return false;
    }

    @Override
    public boolean delete() throws SQLException {
        return false;
    }

    @Override
    public boolean select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from admin where username=? and psw_ad=?");
        ps.setString(1,username);
        ps.setString(2,psw);
        ResultSet s= ps.executeQuery();
        System.out.println(s.next());
        return false;
    }

    @Override
    public ArrayList<Object> getAll() throws SQLException {
        return null;
    }


}
