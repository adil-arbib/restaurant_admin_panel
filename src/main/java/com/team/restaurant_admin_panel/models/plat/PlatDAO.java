package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PlatDAO extends Plat implements Database {

    public PlatDAO(int id, String nom, float price, String description, byte[] img, int id_category) {
        super(id, nom, price, description, img, id_category);
    }

    public PlatDAO(String nom, float price, String description, byte[] img, int id_category) {
        super(nom, price, description, img, id_category);
    }

    public PlatDAO() {
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







