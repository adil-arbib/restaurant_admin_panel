package com.team.restaurant_admin_panel.models.reservation;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.commande.Commande;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.table.Table;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public boolean add() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("INSERT INTO reservation(date_reservation,price,id_ser,id_table) " +
                "values(?,?,?,?); ");
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        ps.setDate(1,sqlDate);
        ps.setFloat(2,price);
        ps.setInt(3,serveur.getId());
        ps.setInt(4,table.getId());
        for(Plat p : listPlat){
            PreparedStatement p1 =con.prepareStatement("INSERT INTO commande(id_reservation,id_plat) " +
                    "values(?,?); ");
            p1.setInt(1,id);
            p1.setInt(2,p.getId());
        }
        return ps.executeUpdate()>0;
    }


    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        PreparedStatement ps =con.prepareStatement("UPDATE reservation SET date_reservation=? price=?" +
                "id_ser=? id_table=? WHERE id=? ;");

        ps.setDate(1,sqlDate);
        ps.setFloat(2,price);
        ps.setInt(3,serveur.getId());
        ps.setInt(4,table.getId());
        ps.setInt(5,id);

        return ps.executeUpdate()>0;
    }

    @Override
    public boolean delete() throws SQLException{
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps =con.prepareStatement("DELETE FROM commande WHERE id_reservation=?;" +
                "DELETE FROM reservation WHERE id=? ;");
        ps.setInt(1,id );
        ps.setInt(2,id);

        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
       // PreparedStatement ps =con.prepareStatement("SELECT * FROM reservation;");

        return null;
    }
    public ArrayList<Reservation> getAll() throws SQLException {

        Connection con = ResourcesManager.getConnection();
        //selecting all from reservation
        PreparedStatement ps =con.prepareStatement("SELECT * FROM reservation;");
        ResultSet rs = ps.executeQuery();


        //selecting list of plats
        PreparedStatement ps1=con.prepareStatement("SELECT * from plat p left join commande c on p.id_plat=c.id_plat " +
                "WHERE c.id_reservation=? ;");
        ps1.setInt(1,id);
        ResultSet rs1= ps1.executeQuery();
        ArrayList<Plat>listPlat = new ArrayList<>();
        while (rs1.next()){
            listPlat.add(new Plat(rs1.getInt(1),rs1.getString(2),rs1.getFloat(3),
                    rs.getString(4),rs.getBytes(5),rs.getInt(6)));
        }


        //selecting serveur in reservation

        PreparedStatement ps2=con.prepareStatement("SELECT * from serveur s left join reservation r on s.id=r.id_ser ;");
        ResultSet rs2= ps2.executeQuery();
        serveur= new Serveur(rs2.getInt(1),rs2.getString(2),rs2.getString(3),
                rs2.getString(4),rs2.getString(5),
                rs2.getString(6), rs2.getFloat(7));


         //selecting table
        PreparedStatement ps3=con.prepareStatement("SELECT * from table_ t left join reservation r on t.id=r.id_table ;");
        ResultSet rs3= ps3.executeQuery();
        table= new Table(rs3.getInt(1),rs.getInt(2));

        //adding everything into a list of reservations
        ArrayList<Reservation> list = new ArrayList<>();
        while (rs.next()){

            list.add(new Reservation(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getFloat(3),serveur,table,
                    listPlat));
        }
        return list;
    }
}
