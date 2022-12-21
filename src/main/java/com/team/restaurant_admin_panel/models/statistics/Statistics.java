package com.team.restaurant_admin_panel.models.statistics;

import com.team.restaurant_admin_panel.models.Database;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.utils.TimeConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public static int getTotalReservations(String year) throws SQLException {
        int totaLReservations=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT COUNT(reservation.id) from reservation " +
                "WHERE YEAR(reservation.date_reservation) = ?;");
        ps.setString(1,year);
        ResultSet rs= ps.executeQuery();
        if(rs.next()){
            return rs.getInt("COUNT(reservation.id)");
        }
            return 0;
    }
    public static int getTotalMonthReservations(String year_month) throws SQLException {

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
    public static float getTotalYear(String year) throws SQLException {
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT SUM(reservation.price) from reservation " +
                "WHERE YEAR(reservation.date_reservation) = ?;");
        ps.setString(1,year);
        ResultSet rs= ps.executeQuery();
        if (rs.next()){
            return rs.getFloat("SUM(reservation.price)");
        }
        return 0;
    }
    public static float ProfitYear(String year) throws SQLException, ParseException {
        Connection con = ResourcesManager.getConnection();
        float somme= getTotalYear(year);
        float profit=0;
        float TotalSalary= getTotalSalary();
        float TotalIngredientPrice = getTotalIngredietPrice(year);

        return  profit= somme - (TotalSalary +TotalIngredientPrice);
    }
    public static float getTotalIngredietPrice(String year) throws SQLException {
        float S=0;
        float pIngredient1=0;
        Connection con =ResourcesManager.getConnection();
        PreparedStatement ps=con.prepareStatement("SELECT unitPrice, qte from ingredient  where YEAR(ingredient.date_ing)=?");
        ps.setString(1,year);
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

    //to know List of the most popular items and the ones that are least ordered by customers according to category.
    public static Map<Plat,Integer> AllPlatOccurence() throws SQLException {
        HashMap<Plat,Integer> platOcc=new HashMap<Plat, Integer>();
        int id;
        Connection con= ResourcesManager.getConnection();
        ArrayList<Categorie> listCat=  CategorieDAO.getAll();
        for (Categorie cat:listCat) {
            PreparedStatement ps0= con.prepareStatement("SELECT id from categorie where libelle = ?; ");
            ps0.setString(1,cat.getLibelle());
            ResultSet rs0=ps0.executeQuery();
            if (rs0.next()) {id=rs0.getInt(1);
                PreparedStatement ps= con.prepareStatement("SELECT commande.id_plat , COUNT(commande.id_plat) AS value_occurrence" +
                        " FROM commande left join plat on commande.id_plat=plat.id WHERE " +
                        "plat.id_cat=? GROUP BY commande.id_plat ORDER BY value_occurrence DESC limit 1;");
                ps.setInt(1,id);
                ResultSet rs=ps.executeQuery();
                int id_plat;
                int numberOcc;
                while(rs.next()){
                    id_plat=rs.getInt("id_plat");
                    numberOcc=rs.getInt("value_occurrence");
                    PlatDAO p = new PlatDAO();
                    p.setId(id_plat);
                    Plat plat =(Plat) p.select();
                    platOcc.put(plat,numberOcc);}

            }
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

    // How much was a dish ordered in one month
    public static int PlatOccMonth(String year_month) throws SQLException {
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT commande.id_plat, COUNT(commande.id_plat) AS value_occurrence" +
                "    FROM commande LEFT JOIN reservation on reservation.id = commande.id_reservation" +
                "    WHERE CONCAT(YEAR(reservation.date_reservation),'-',MONTH(reservation.date_reservation))=?" +
                "    GROUP BY commande.id_plat ORDER BY `value_occurrence` DESC limit 1;");
        ps.setString(1,year_month);
        ResultSet rs=ps.executeQuery();
        int id_plat;
        int numberOcc;
        if(rs.next()){
            id_plat=rs.getInt("id_plat");
            numberOcc=rs.getInt("value_occurrence");
            PlatDAO p = new PlatDAO();
            p.setId(id_plat);
            Plat plat =(Plat) p.select();
            return  numberOcc;

        }
        return 0;
    }

    public static float monthlyProfit(String year_month) throws SQLException {
        float profit=0;
        float TotalSalary= getTotalSalary();
        float TotalIngredientPrice = totalIngredPermonth(year_month);
        float TotalReservationPrice= getTotalPriceRes(year_month);
        profit= TotalReservationPrice  - (TotalSalary +TotalIngredientPrice);

        return profit;
    }

//how many reservations did a server serve in a month
   public static int numberTablesServerd(int idServeur, String year_month) throws SQLException{
        int NumTables=0;
       Connection con = ResourcesManager.getConnection();
       PreparedStatement ps = con.prepareStatement("SELECT COUNT(id_ser) AS NumberServed from reservation " +
               "where id_ser = ? and CONCAT(YEAR(date_reservation),'-',MONTH(date_reservation))= ?;");
      ps.setInt(1,idServeur);
      ps.setString(2,year_month);
      ResultSet rs=ps.executeQuery();
      if(rs.next()) NumTables= rs.getInt("NumberServed");

       return NumTables;

   }
   public static Serveur serverOfMonth() throws SQLException {
        HashMap<Serveur,Integer> waiter= new HashMap<>();
       ServeurDAO s= new ServeurDAO();
       Serveur sOfMonth=new Serveur();
       ArrayList<Serveur> list=s.getAll();
       for( Serveur serveur : list){
           int total = numberTablesServerd(serveur.getId(), TimeConverter.getCurrentMonth());
           waiter.put(serveur,total);
       }
       Map.Entry<Serveur, Integer> maxEntry = null;
        for (Map.Entry<Serveur, Integer> entry : waiter.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                    maxEntry = entry;
            }
        }
        sOfMonth=maxEntry.getKey();
    return  sOfMonth;
   }

   public static  int numberofServeurs() throws SQLException {
       Connection con = ResourcesManager.getConnection();
       PreparedStatement ps= con.prepareStatement("SELECT COUNT(id) from serveur");
       ResultSet rs=ps.executeQuery();
       if(rs.next()) return rs.getInt(1);
       return 0;
   }
    public static  int numberofmanager() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT COUNT(id) from admin");
        ResultSet rs=ps.executeQuery();
        if(rs.next()) return rs.getInt(1);
        return 0;
    }
    public static  int numberofcuisinier() throws SQLException {
        Connection con = ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("SELECT COUNT(id) from cuisinier");
        ResultSet rs=ps.executeQuery();
        if(rs.next()) return rs.getInt(1);
        return 0;
    }

    public static HashMap<String,Integer> CatPopularity() throws SQLException {
        HashMap<String,Integer> liste=new HashMap<>();
        Connection con= ResourcesManager.getConnection();
        PreparedStatement ps= con.prepareStatement("select ca.libelle ,count(ca.id) as demande from plat p" +
                " inner join commande c on p.id=c.id_plat " +
            "inner join categorie ca on ca.id=p.id_cat group by (ca.id);");
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            liste.put(rs.getString(1),rs.getInt(2));
        }
    return liste;
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

}
