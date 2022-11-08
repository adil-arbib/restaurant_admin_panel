package com.team.restaurant_admin_panel.models.commande;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.plat.Plat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommandeDAO extends Commande implements Database {

    public CommandeDAO(int reservation_id, int id_plat) {
        super(reservation_id, id_plat);
    }

    @Override
    public boolean add() throws SQLException {
        //reservation_id	id_plat
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("INSERT INTO commande values(?,?)");
        ps.setInt(1,reservation_id);
        ps.setInt(2,id_plat);
        return ps.executeUpdate()>0;
    }

    @Override
    public boolean update() throws SQLException {

        return false;
}

    @Override
    public boolean delete() throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("DELETE FROM commande where reservation_id=? and id_plat=?");
        ps.setInt(1,reservation_id);
        ps.setInt(2,id_plat);
        return ps.executeUpdate()>0;
    }

    @Override
    public Object select() throws SQLException {

        return null;
    }


      public ArrayList<Plat> getAllPLats() throws SQLException {

        ArrayList<Plat> PlatList=new ArrayList<>();
        Connection con = ResourcesManager.getConnection();
        String sql = "SELECT * from plats p left join commande c on p.id_plat=c.id_plat WHERE c.reservation_id=?;";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1,reservation_id);
        ResultSet rs = ps.executeQuery();
          while (rs.next()) {
              PlatList.add(new Plat(
                      rs.getInt("id"),
                      rs.getString("nom"),
                      rs.getFloat("price"),
                      rs.getString("description"),
                      rs.getInt("id_categrory")
              ));
          }
          return PlatList;
      }
    }

