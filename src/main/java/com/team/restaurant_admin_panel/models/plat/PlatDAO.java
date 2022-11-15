package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {


    public PlatDAO(int id, String nom, float price, String description, byte[] img, Categorie categorie) {
        super(id, nom, price, description, img, categorie);
    }

    public PlatDAO(String nom, float price, String description, byte[] img, Categorie categorie) {
        super(nom, price, description, img, categorie);
    }

    public PlatDAO() {
        super();
    }

    @Override
    public int add() throws SQLException, ParseException {
        String sql = "insert into plat(nom,price,description,img,id_cat) " +
                "values (?,?,?,?,?)";
        InputStream inputStream = new ByteArrayInputStream(img); // convert byte array to inputStream
        PreparedStatement ps = ResourcesManager.getConnection().prepareStatement(sql);
        ps.setString(1,nom);
        ps.setFloat(2,price);
        ps.setString(3,description);
        ps.setBlob(4,inputStream);
        ps.setInt(5,categorie.getId());
        ps.executeUpdate();
        Statement s = ResourcesManager.getConnection().createStatement();
        ResultSet rs = s.executeQuery("SELECT LAST_INSERT_ID();");
        if (rs.next())return rs.getInt(1);
        return 0;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("UPDATE plat set nom=?, price=?, description=?, img=?,id_cat=?" +
                " WHERE id = ?;");
        InputStream inputStream = new ByteArrayInputStream(img); // convert byte array to inputStream
        ps.setString(1,nom);
        ps.setFloat(2,price);
        ps.setString(3,description);
        ps.setBlob(4,inputStream);
        ps.setInt(5,categorie.getId());
        ps.setInt(6,id);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM plat WHERE id=?;");
        ps.setInt(1,id );
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from plat where id = ? ;");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            Blob clob = rs.getBlob(5);
            byte[] byteArr = clob.getBytes(1,(int)clob.length()); // retrieving image from db
            CategorieDAO cDAO = new CategorieDAO();
            cDAO.setId(rs.getInt(6));
            Categorie categorie = (Categorie) cDAO.select(); // select categorie by id_cat
            return new Plat(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4),
                    byteArr, // image as byte array
                    categorie
            );
        }
        return null;
    }

//    public static Object selectPLatByIdCat(int id_cat) throws SQLException {
//        Connection con = ResourcesManager.getConnection();
//        PreparedStatement ps = con.prepareStatement("Select * FROM plat Where id_cat = ?");
//        ps.setInt(1,id_cat);
//        ResultSet rs = ps.executeQuery();
//        if (rs.next()){
//            Blob clob = rs.getBlob(5);
//            byte[] byteArr = clob.getBytes(1,(int)clob.length());
//            CategorieDAO cDAO = new CategorieDAO();
//            cDAO.setId(rs.getInt(6));
//            Categorie categorie = (Categorie) cDAO.select();
//            return new Plat(
//                    rs.getInt(1),
//                    rs.getString(2),
//                    rs.getFloat(3),
//                    rs.getString(4),
//                    byteArr,
//                    categorie);
//        }
//        return null;
//    }





    /**
     * get all plats
     * @return
     * @throws SQLException
     */
    public static ArrayList<Plat> getAll() throws SQLException {
        Statement st = ResourcesManager.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from plat");
        ArrayList<Plat> plats = new ArrayList<>();
        while (rs.next()){
            Blob clob = rs.getBlob(5);
            byte[] byteArr = clob.getBytes(1,(int)clob.length()); // retrieving image from db
            CategorieDAO cDAO = new CategorieDAO();
            cDAO.setId(rs.getInt(6));
            Categorie categorie = (Categorie) cDAO.select(); // select categorie by id_cat
            plats.add(new Plat(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),
                    rs.getString(4),
                    byteArr, // image as byte array
                    categorie
            ));
        }
        return plats;
    }


    //selecting list of plat of a reservation
//    public static ArrayList<Plat> getListPlatById(int reservation_id) throws SQLException {
//        Connection con= ResourcesManager.getConnection();
//        PreparedStatement ps1 = con.prepareStatement("SELECT * from plat p left join commande c on p.id=c.id_plat " +
//            "WHERE c.id_reservation=? ;");
//        ps1.setInt(1, reservation_id);
//        ResultSet rs1 = ps1.executeQuery();
//        ArrayList<Plat> listPlat= new ArrayList<>();
//        while (rs1.next()) {
//        PreparedStatement psCat = con.prepareStatement("SELECT * from categorie cat right join plat p on cat.id=p.id_cat where p.id=? ");
//        psCat.setInt(1, rs1.getInt(6));
//        ResultSet rsCat = psCat.executeQuery();
//        listPlat.add(new Plat(rs1.getInt(1), rs1.getString(2), rs1.getFloat(3),
//                rs1.getString(4), rs1.getBytes(5), new CategorieDAO(rsCat.getInt(1), rsCat.getString(2))));
//        }
//        return listPlat;
//
//    }


}







