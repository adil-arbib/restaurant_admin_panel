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
import com.team.restaurant_admin_panel.models.table.TableDAO;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        ps.setTimestamp(1,  new java.sql.Timestamp(sqlDate.getTime()));
        ps.setFloat(2, price);
        ps.setInt(3, serveur.getId());
        ps.setInt(4, table.getId());
        ps.executeUpdate();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
        int lastID = 0;
        if (rs.next()) {
            lastID = rs.getInt(1);
        }
        for (Plat p : listPlat) {
            CommandeDAO commands = new CommandeDAO(lastID, p.getId());
            commands.add();
        }
        return lastID;

    }

    /**
     * update reservation
     * @return boolean indicate if the operation successes
     */
    @Override
    public boolean update() throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // convert java.util.Date to java.sql.Date
        PreparedStatement ps0 = con.prepareStatement("DELETE FROM commande WHERE id_reservation = ?");
        ps0.setInt(1,id);
        boolean success = ps0.executeUpdate() > 0;

        PreparedStatement ps = con.prepareStatement("UPDATE reservation SET date_reservation=?, price=?, " +
                "id_ser=?, id_table=? WHERE id=? ; ");

        ps.setTimestamp(1,  new java.sql.Timestamp(sqlDate.getTime()));
        ps.setFloat(2, price);
        ps.setInt(3, serveur.getId());
        ps.setInt(4, table.getId());
        ps.setInt(5, id);
        success =  success && ps.executeUpdate()>0;
        for (Plat plat : listPlat){
            PreparedStatement ps2 = con.prepareStatement("INSERT INTO commande values(?,?)");
            ps2.setInt(1,id);
            ps2.setInt(2,plat.getId());
            success = success && ps2.executeUpdate()>0;
        }

        return  success;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps1 = con.prepareStatement("DELETE FROM commande WHERE id_reservation=?");
        PreparedStatement ps2 = con.prepareStatement("DELETE FROM reservation WHERE id=?");
        ps1.setInt(1, id);
        boolean succ1 = ps1.executeUpdate() > 0;
        ps2.setInt(1, id);
        boolean succ2 = ps2.executeUpdate() > 0;
        return succ1 && succ2 ;
    }

    /**
     * return one reservation by id
     * @return Reservation object
     * @throws SQLException
     */
    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM reservation where id=? ;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            // selecting list plats
            CommandeDAO commandeDAO = new CommandeDAO();
            commandeDAO.setId_reservation(id);
            ArrayList<Plat> plats = (ArrayList<Plat>) commandeDAO.select();

            // selecting serveur
            ServeurDAO serveurDAO = new ServeurDAO();
            serveurDAO.setId(rs.getInt(4));
            Serveur serveur = (Serveur) serveurDAO.select();

            //selecting table
            TableDAO tableDAO = new TableDAO();
            tableDAO.setId(rs.getInt(5));
            Table table = (Table) tableDAO.select();


            return new Reservation(id, rs.getTimestamp(2).toString(), rs.getFloat(3), serveur, table, plats);
        }
        return null;
    }


    /**
     * get all reservations
     * @return arrayList of reservations
     * @throws SQLException
     */
    public static ArrayList<Reservation> getAll() throws SQLException {
        ArrayList<Reservation> reservations = new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        //selecting all from reservation
        PreparedStatement ps =con.prepareStatement("SELECT * FROM reservation;");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            ReservationDAO dao = new ReservationDAO();
            dao.setId(rs.getInt(1));
            reservations.add((Reservation) dao.select());
        }
        return reservations;
    }

    public static void main(String[] args) throws SQLException, ParseException {

    }

}