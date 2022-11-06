package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {

    public PlatDAO(){}

    public PlatDAO(int id, String nom, float price, String description, int id_cat) {
        super(id, nom, price,description,id_cat);
    }


    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into plat(nom_plat,description,prix_plat,id_categorie) values(?,?,?,?)");
        ps.setString(1,nom);
        ps.setString(2,description);
        ps.setDouble(3,price);
        ps.setInt(4,id_category);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("UPDATE plat SET nom = ? , id_categorie =? , prix_plat=? ,description=? WHERE nom = ? ");
        ps.setString(1,nom);
        ps.setInt(2,id_category);
        ps.setFloat(3,price);
        ps.setString(4,description);
        ps.setString(5,nom);
        System.out.println(" object  updated");
        return ps.executeUpdate() > 0;
    }


    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("DELETE from plat where nom=? ");
        ps.setString(1,nom);
        return false;

    }
    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from plat where nom=? and id_categorie=?");
        ps.setString(1,nom);
        ps.setInt(2,id_category);
        ResultSet s= ps.executeQuery();
        //int id, String nom, float price, String description, int id_cat
        if(s.next()){
            return new Plat(
                    s.getInt(1),
                    s.getString(2),
                    s.getFloat(4),
                    s.getString(3),
                    s.getInt(5)
            );
        }
        return null;
    }

//    @Override
//    public ArrayList<Object> getAll() throws SQLException {
//        ArrayList<Object> PlatList=new ArrayList<>();
//        Connection con = ResourcesManager.getConnection();
//        String sql = "SELECT * from plat";
//        PreparedStatement ps = con.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            PlatDAO dish = new PlatDAO();
//            dish.setId(rs.getInt("id_plat"));
//            dish.setNom(rs.getString("nom_plat"));
//            dish.setDescription(rs.getString("description"));
//            dish.setPrice(rs.getFloat("prix_plat"));
//            dish.setId_category(rs.getInt("id_categorie"));
//            PlatList.add(dish);
//        }
//        return PlatList;
//    }

    }


