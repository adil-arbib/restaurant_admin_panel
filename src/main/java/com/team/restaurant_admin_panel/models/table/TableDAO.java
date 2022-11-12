package com.team.restaurant_admin_panel.models.table;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.categorie.Categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableDAO extends Table implements Database {

    public TableDAO(int id, int num) {
        super(id, num);
    }

    public TableDAO(int num) {
        super(num);
    }

    public TableDAO() {
    }

    @Override
    public int add() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into table_ (num) values (?)");
        ps.setInt(1,num);
        PreparedStatement ps1 =con.prepareStatement("SELECT LAST_INSERT_ID();");
        ps.executeUpdate() ;
        ResultSet rs1= ps1.executeQuery();
        while (rs1.next()){
            id=rs1.getInt(1);
        }
        return id;
    }

    @Override
    public boolean update() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("update table_ " +
                "set num = ? where id = ?");
        ps.setInt(1,num);
        ps.setInt(2,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean delete() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("DELETE FROM table_ WHERE id = ?;");
        ps.setInt(1,id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public Object select() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from table_ where id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return new Table(rs.getInt(1),rs.getInt(2));
        }
        return false;
    }

    public static ArrayList<Table> getAll() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from table_");
        ResultSet rs = ps.executeQuery();
        ArrayList<Table> list = new ArrayList<>();
        while (rs.next()){
            list.add(new Table(
                    rs.getInt(1),
                    rs.getInt(2)
            ));
        }
        return list;
    }
}
