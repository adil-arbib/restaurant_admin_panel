package com.team.restaurant_admin_panel.models.ingredient;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredientDAO extends Ingredient implements Database {

    IngredientDAO(int id , String nom , Float qte){
        super(id, nom, qte);
    }
    IngredientDAO(){};


    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into ingredients (nom,qte) values(?,?)");
        ps.setString(1,nom);
        ps.setFloat(2,qte);
        return  ps.executeUpdate() > 0;

    }

    @Override
    public boolean update() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("Update ingredients SET qte = ? WHERE nom = ?");
        ps.setFloat(1,qte);
        ps.setString(2,nom);
        return  ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("Delete from ingredients WHERE nom = ?");
        ps.setString(1,nom);
        return false;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("Select * from ingredients WHERE nom = ?");
        ps.setString(1,nom);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            return  new Ingredient(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3)

            );
        }
        return null;
    }

    public static ArrayList<Ingredient> getAll() throws SQLException {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * from Ingredients");
        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            ingredientList.add(new IngredientDAO(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3)
            ));
        }
        return ingredientList;
    }
}
