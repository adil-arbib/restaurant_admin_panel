package com.team.restaurant_admin_panel.models.admin;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDAO extends Admin implements Database {

    public AdminDAO(int id, String name) {
        super(id, name);
    }

    @Override
    public boolean add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into admin values(?,?)");
        ps.setInt(1,id);
        ps.setString(2,name);
        return ps.executeUpdate() > 0;
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
    public ArrayList<Object> getAll() throws SQLException {
        return null;
    }


}
