package com.team.restaurant_admin_panel.models.cuisinier;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class CuisinierDAO extends Cuisinier implements Database {

    public CuisinierDAO(int id, String nom, String prenom, String cin, float salaire) {
        super(id, nom, prenom, cin, salaire);
    }

    public CuisinierDAO(String nom, String prenom, String cin, float salaire) {
        super(nom, prenom, cin, salaire);
    }

    public CuisinierDAO() {
    }

    @Override
    public int add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into cuisinier " +
                "(nom, prenom, cin, salaire) values (?,?,?,?)");
        PreparedStatement ps1 =con.prepareStatement("SELECT LAST_INSERT_ID();");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,cin);
        ps.setFloat(4,salaire);
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
        PreparedStatement ps= con.prepareStatement("UPDATE cuisinier set nom=?, prenom=?,cin=?,salaire=? WHERE id = ?;");
        ps.setString(1,nom);
        ps.setString(2,prenom);
        ps.setString(3,cin);
        ps.setFloat(4,salaire);
        ps.setInt(5,id);
        return ps.executeUpdate()>0;
    }

    @Override
    public  boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM cuisinier WHERE id=?;");
        ps.setInt(1,id );
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from cuisinier where id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Cuisinier(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getFloat(5));
        }
        return null;
    }


    public static ArrayList<Cuisinier> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from cuisinier");
        ResultSet rs = ps.executeQuery();
        ArrayList<Cuisinier> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Cuisinier(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getFloat(5)
            ));
        }
        return list;
    }
}
