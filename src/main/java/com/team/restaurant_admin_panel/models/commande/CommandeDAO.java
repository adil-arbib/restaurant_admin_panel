package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.plat.Plat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class CommandeDAO extends Commande implements Database {


    public CommandeDAO(int id_reservation, int id_plat) {
        super(id_reservation, id_plat);
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
