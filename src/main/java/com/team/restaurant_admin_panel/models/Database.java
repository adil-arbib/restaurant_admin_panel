package com.team.restaurant_admin_panel.models;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public interface Database {
    boolean add() throws SQLException, ParseException;
    boolean update() throws SQLException, ParseException;
    boolean delete() throws SQLException;

    Object select() throws SQLException;
    //ArrayList<Object> getAll() throws SQLException;

}
