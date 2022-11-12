package com.team.restaurant_admin_panel.models.reservation;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.commande.Commande;
import com.team.restaurant_admin_panel.models.commande.CommandeDAO;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.models.table.Table;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

public class ReservationDAO extends Reservation implements Database {


    public ReservationDAO(int id, String date, float price, Serveur serveur, Table table, ArrayList<Plat> listPlat) {
        super(id, date, price, serveur, table, listPlat);
    }

    public ReservationDAO(String date, float price, Serveur serveur, Table table, ArrayList<Plat> listPlat) {
        super(date, price, serveur, table, listPlat);
    }

    public ReservationDAO() {
    }

    @Override
    public int add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();

        PreparedStatement ps = con.prepareStatement("INSERT INTO reservation(date_reservation,price,id_ser,id_table) " +
                "values(?,?,?,?); ");
        PreparedStatement ps1 = con.prepareStatement("SELECT LAST_INSERT_ID();");
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        ps.setDate(1, sqlDate);
        ps.setFloat(2, price);
        ps.setInt(3, serveur.getId());
        ps.setInt(4, table.getId());
        ps.executeUpdate();
        ResultSet rs = ps1.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        for (Plat p : listPlat) {
            CommandeDAO commands = new CommandeDAO(id, p.getId());
            commands.add();
        }

        return id;

    }

    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        //java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        PreparedStatement ps = con.prepareStatement("UPDATE reservation SET date_reservation=? price=?" +
                "id_ser=? id_table=? WHERE id=? ;");

        ps.setDate(1, sqlDate);
        ps.setFloat(2, price);
        ps.setInt(3, serveur.getId());
        ps.setInt(4, table.getId());
        ps.setInt(5, id);

        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM commande WHERE id_reservation=?;" +
                "DELETE FROM reservation WHERE id=? ;");
        ps.setInt(1, id);
        ps.setInt(2, id);

        return ps.executeUpdate() > 0;
    }

    @Override
    //return one reservation by id
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM reservation where id=? ;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            //selecting list of plat of this reservation
            PlatDAO p = new PlatDAO();
            listPlat = p.getListPlatById(id);
            //selecting serveur of this reservation
            ServeurDAO s = new ServeurDAO();
            serveur = s.getServeurById(id);
            //selecting table
            PreparedStatement ps3 = con.prepareStatement("SELECT * from table_ t right join reservation r on t.id=r.id_table ;");
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) table = new Table(rs3.getInt(1), rs3.getInt(2));

            return new Reservation(id, rs.getDate(2).toString(), rs.getFloat(3), serveur, table, listPlat);
        }
        return null;
    }


    //return list of reservation
       public  ArrayList<Reservation> getAll() throws SQLException {

        Connection con = ResourcesManager.getConnection();
        //selecting all from reservation
        PreparedStatement ps =con.prepareStatement("SELECT * FROM reservation;");
        ResultSet rs = ps.executeQuery();
        ArrayList<Reservation> list = new ArrayList<>();
        while (rs.next()){
        //selecting list of plats
       PlatDAO p = new PlatDAO();
            listPlat = p.getListPlatById(id);
        //selecting serveur in reservation
        ServeurDAO s = new ServeurDAO();
            serveur = s.getServeurById(id);
         //selecting table
        PreparedStatement ps3=con.prepareStatement("SELECT * from table_ t left join reservation r on t.id=r.id_table ;");
        ResultSet rs3= ps3.executeQuery();
        table= new Table(rs3.getInt(1),rs3.getInt(2));
          //adding everything into a list of reservations
            list.add(new Reservation(rs.getInt(1), rs.getDate(2).toString(), rs.getFloat(3), serveur, table, listPlat));
        }
        return list;
    }

}
