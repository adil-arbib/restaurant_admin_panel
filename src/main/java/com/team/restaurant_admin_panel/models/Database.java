package com.team.restaurant_admin_panel.models;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Database {
    boolean add() throws SQLException;
    boolean update() throws SQLException;
    boolean delete() throws SQLException;
    ArrayList<Object> getAll() throws SQLException;
}
