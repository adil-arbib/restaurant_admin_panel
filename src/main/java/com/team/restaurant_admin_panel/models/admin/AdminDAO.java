package com.team.restaurant_admin_panel.models.admin;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.*;
import java.util.ArrayList;

public class AdminDAO extends Admin implements Database {

    public AdminDAO(){}
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
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE admin SET psw_ad = ? , username=? WHERE nom = ? and prenom= ?");
        ps.setString(1,psw);
        ps.setString(2,username);
        ps.setString(3,nom);
        ps.setString(4,prenom);
        System.out.println(" object  updated");
        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("DELETE from admin where username=? and psw_ad=?");
        ps.setString(1,username);
        ps.setString(2,psw);
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

            ArrayList<Object> adminList=new ArrayList<>();
            Connection con = ResourcesManager.getConnection();
            String sql = "SELECT * from admin";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AdminDAO adm = new AdminDAO();
                adm.setId(rs.getInt("id_ad"));
                adm.setNom(rs.getString("nom"));
                adm.setPrenom(rs.getString("prenom"));
                adm.setCIN(rs.getString("cin"));
                adm.setUsername(rs.getString("username"));
                adm.setPsw(rs.getString("psw_ad"));
                adminList.add(adm);
            }
            return adminList;
        }

    }


