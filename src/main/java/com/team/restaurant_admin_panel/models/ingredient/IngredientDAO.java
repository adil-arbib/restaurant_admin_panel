package com.team.restaurant_admin_panel.models.ingredient;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.categorie.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class IngredientDAO extends Ingredient implements Database {

    public IngredientDAO(int id, String nom, String date, float qte, float unitPrice) {
        super(id, nom, date, qte, unitPrice);
    }

    public IngredientDAO(String nom, String date, float qte, float unitPrice) {
        super(nom, date, qte, unitPrice);
    }

    public IngredientDAO() {
    }

    @Override
    public int add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into ingredient " +
                "(nom,date_ing,qte,unitPrice) values (?,?,?,?)");

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        ps.setString(1,nom);
        ps.setDate(2,sqlDate);
        ps.setFloat(3,qte);
        ps.setFloat(4,unitPrice);
        ps.executeUpdate();
        PreparedStatement ps1 =con.prepareStatement("SELECT LAST_INSERT_ID();");
        ResultSet rs1= ps1.executeQuery();
        while (rs1.next()){
            id=rs1.getInt(1);
        }
        return id ;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("update ingredient " +
                "set nom = ?, date_ing = ?, qte = ?, unitPrice = ? where id = ?");

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        ps.setString(1,nom);
        ps.setDate(2,sqlDate);
        ps.setFloat(3,qte);
        ps.setFloat(4,unitPrice);
        ps.setInt(5,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM ingredient WHERE id = ?;");
        ps.setInt(1,id);
        return ps.executeUpdate() > 0;
    }

    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from ingredients where id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Ingredient(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3).toString(),
                    rs.getFloat(4),
                    rs.getFloat(5));
        }
        return null;
    }


    public static ArrayList<Ingredient> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from ingredient");
        ResultSet rs = ps.executeQuery();
        ArrayList<Ingredient> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Ingredient(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDate(3).toString(),
                    rs.getFloat(4),
                    rs.getFloat(5)
            ));
        }
        return list;
    }
}
