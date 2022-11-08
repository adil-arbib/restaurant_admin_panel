package com.team.restaurant_admin_panel.models.reservation;

import com.team.restaurant_admin_panel.models.Database;

import java.sql.SQLException;
import java.text.ParseException;

public class ReservationDAO extends Reservation implements Database {

    public ReservationDAO(int id, String date, float price, int id_ser, int id_table) {
        super(id, date, price, id_ser, id_table);
    }

    public ReservationDAO(String date, float price, int id_ser, int id_table) {
        super(date, price, id_ser, id_table);
    }

    public ReservationDAO() {
    }

    @Override
    public boolean add() throws SQLException, ParseException {
        return false;
    }

    @Override
    public boolean update() throws SQLException, ParseException {
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
