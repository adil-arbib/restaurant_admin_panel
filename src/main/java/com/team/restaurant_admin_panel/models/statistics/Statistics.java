package com.team.restaurant_admin_panel.models.statistics;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Statistics implements Database {


    //getting total salary of servers
    public static float getTotalSalary() throws SQLException {
        float totalSalary;
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT SUM(serveur.salaire) from serveur;");
        ResultSet rs=ps.executeQuery();
        if (rs.next()) {
            totalSalary=rs.getFloat("SUM(serveur.salaire)");
        return totalSalary;}
        return 0;
    }
    //getting totalReservations
    public static int getTotalReservations(String year_month) throws SQLException {
        int totaLReservations=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT COUNT(reservation.id) from reservation " +
                "WHERE CONCAT(YEAR(reservation.date_reservation),'-',MONTH(reservation.date_reservation)) = ?;");
        ps.setString(1,year_month);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            return rs.getInt("COUNT(reservation.id)");
        }
            return 0;
    }
    public static float getTotalPriceRes(String year_month) throws SQLException {
        int totaLReservations=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT SUM(reservation.price) from reservation " +
                "WHERE CONCAT(YEAR(reservation.date_reservation),'-',MONTH(reservation.date_reservation)) = ?;");
        ps.setString(1,year_month);
        ResultSet rs= ps.executeQuery();
        if (rs.next()){
            return rs.getFloat("SUM(reservation.price)");
        }
        return 0;
    }
    public static float getTotalIngredietPrice() throws SQLException {
        float S=0;
        float pIngredient1=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT unitPrice, qte from ingredient ");
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
            //price of one ingredient;
             pIngredient1=rs.getFloat(1)*rs.getFloat(2);
             S=S+pIngredient1;
        }
        return S;
    }

    public static Plat  mostOrdered() throws SQLException {
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT commande.id_plat, COUNT(commande.id_plat) AS value_occurrence" +
                " FROM commande GROUP BY commande.id_plat ORDER BY value_occurrence DESC LIMIT 1; ");
        ResultSet rs= ps.executeQuery();
        //gets id plat and number of occurences
        int id_plat;
        int numberOcc;
        while (rs.next()){
            id_plat=rs.getInt("id_plat");
            numberOcc=rs.getInt("value_occurrence");
            PlatDAO p = new PlatDAO();
            p.setId(id_plat);
            Plat plat =(Plat) p.select();

            return  plat;
        }
        return null;
    }

    public static Plat  lessOrdered() throws SQLException {
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT commande.id_plat, COUNT(commande.id_plat) AS value_occurrence" +
                " FROM commande GROUP BY commande.id_plat ORDER BY value_occurrence ASC LIMIT 1; ");
        ResultSet rs= ps.executeQuery();
        //gets id plat and number of occurences
        int id_plat;
        int numberOcc;
        while (rs.next()){
            id_plat=rs.getInt("id_plat");
            numberOcc=rs.getInt("value_occurrence");
            PlatDAO p = new PlatDAO();
            p.setId(id_plat);
            Plat plat =(Plat) p.select();
            return  plat;
        }
        return null;
    }

    //to know List of the most popular items and the ones that are least ordered by customers.
    public static Map<Plat,Integer> AllPlatOccurence() throws SQLException {
        HashMap<Plat,Integer> platOcc=new HashMap<Plat, Integer>();;
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT commande.id_plat, COUNT(commande.id_plat) AS value_occurrence " +
                "FROM commande GROUP BY commande.id_plat ORDER BY value_occurrence DESC;");
        ResultSet rs=ps.executeQuery();
        int id_plat;
        int numberOcc;
        while(rs.next()){
            id_plat=rs.getInt("id_plat");
            numberOcc=rs.getInt("value_occurrence");
            PlatDAO p = new PlatDAO();
            p.setId(id_plat);
            Plat plat =(Plat) p.select();
            platOcc.put(plat,numberOcc);
           /* for (Plat i : platOcc.keySet()) {
                System.out.println("plat: " + i + " occurence: " + platOcc.get(i));
*/
        }

        return  platOcc;
    }

    public static float totalIngredPermonth(String year_month) throws SQLException {
        int totalPrice=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT SUM(unitPrice*qte) AS total_price FROM ingredient " +
                "WHERE CONCAT(YEAR(ingredient.date_ing),'-',MONTH(ingredient.date_ing)) =?;");
        ps.setString(1,year_month);
        ResultSet rs= ps.executeQuery();
        if (rs.next()){
            return rs.getFloat("total_price");
        }
        return 0;
    }
    // How much was a dish ordered in one year

    public static Map<Plat,Integer> PlatOccYear(String year) throws SQLException {
        HashMap<Plat,Integer> platOcc=new HashMap<Plat, Integer>();;
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT commande.id_plat, COUNT(commande.id_plat) AS value_occurrence" +
                "    FROM commande LEFT JOIN reservation on reservation.id = commande.id_reservation" +
                "    WHERE YEAR(reservation.date_reservation)=?" +
                "    GROUP BY commande.id_plat ORDER BY `value_occurrence` DESC;");
        ps.setString(1,year);
        ResultSet rs=ps.executeQuery();
        int id_plat;
        int numberOcc;
        while(rs.next()){
            id_plat=rs.getInt("id_plat");
            numberOcc=rs.getInt("value_occurrence");
            PlatDAO p = new PlatDAO();
            p.setId(id_plat);
            Plat plat =(Plat) p.select();
            platOcc.put(plat,numberOcc);
        }
        return  platOcc;
    }

    public static float monthlyProfit(String year_month) throws SQLException {
        float profit=0;

        float TotalSalary= getTotalSalary();
        float TotalIngredientPrice = totalIngredPermonth(year_month);
        float TotalReservationPrice= getTotalPriceRes(year_month);
        profit= TotalReservationPrice  - (TotalSalary +TotalIngredientPrice);

        return profit;
    }


    @Override
    public int add() throws SQLException, ParseException {
        return 0;
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

    public static void main(String[] args) throws SQLException {
        float s=totalIngredPermonth("2020-11");
       // System.out.println(PlatOccYear("2022"));
        //System.out.println(AllPlatOccurence());


    }
}
