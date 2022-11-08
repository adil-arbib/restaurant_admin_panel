package com.team.restaurant_admin_panel.models.reservations;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReservationsDAO extends Reservations implements Database {
    public ReservationsDAO(int reservation_id, int id_serv, String date, float price_reservation) {
        super(reservation_id, id_serv, date, price_reservation);
    }

    public ReservationsDAO(int id_serv, String date, float price_reservation) {
        super(id_serv, date, price_reservation);
    }

    @Override
    public boolean add() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        //yy-mm-dd hh:mm:ss xm
        //insert into reservations(id_serv,Date) VALUES(2,'22-11-08 10:34:09 PM');
        PreparedStatement ps= con.prepareStatement("insert into reservations(id_serv,Date,price_reservation) VALUES(?,?,?);");
        ps.setInt(1,id_serv);
        ps.setString(2,Date);
        ps.setFloat(3,price_reservation);
        return ps.executeUpdate()>0;
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
    public Object select() throws SQLException {
        return null;
    }
}
