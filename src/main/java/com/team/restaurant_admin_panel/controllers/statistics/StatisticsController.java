package com.team.restaurant_admin_panel.controllers.statistics;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.statistics.Statistics;
import com.team.restaurant_admin_panel.utils.TimeConverter;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;

import java.net.URL;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class StatisticsController implements Initializable{

    @FXML
    Label CalendarLabel;
    @FXML
    Label lastMonthOrder;

    @FXML
    BarChart barChart;
    @FXML
    CategoryAxis xlabel;
    @FXML
    NumberAxis ylabel;
    @FXML
    PieChart popularDrink;
    @FXML
    Label ProfitYear;
    @FXML
    Label LastYearProfit;
    @FXML
    Label Orders;
    @FXML
    Label BestMonth;
    @FXML
    Label ProfitMonth;
    @FXML
    Label LessDish;
    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCalendar();
        barChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());

        try {
            fillbarChart();
            fillPieChart2();
            fillEntitie1();
            fillEntitie2();
            fillEntitie3();
            fillEntitie4();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    public void fillCalendar(){
        CalendarLabel.setText(LocalDate.now().toString());
    }

     public void fillbarChart() throws SQLException {
         xlabel.setLabel("item name");
         ylabel.setLabel("Occurence");
         XYChart.Series series = new XYChart.Series();
         series.setName("based on category");
         HashMap<Plat,Integer> listPlat= (HashMap<Plat, Integer>) Statistics.AllPlatOccurence();
         for (Plat i : listPlat.keySet()){
             series.getData().add(new XYChart.Data(i.getNom(),listPlat.get(i) ));
         }
         barChart.getData().add(series);
     }
    public void fillPieChart2(){
        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(
                        new PieChart.Data("hot Chocolate",25),
                        new PieChart.Data("banana smoothie",30),
                        new PieChart.Data("CocaCola",50),
                        new PieChart.Data("lemonade",15)
                );

        popularDrink.setData(pieChartData);
    }
    public void fillEntitie1() throws SQLException {
        Orders.setText(Statistics.getTotalMonthReservations(TimeConverter.getCurrentMonth())+" orders");
        lastMonthOrder.setText(Statistics.getTotalMonthReservations(TimeConverter.getLastMonth())+" orders");
    }
    public void fillEntitie2() throws SQLException, ParseException {
        float currentYear=Statistics.ProfitYear(TimeConverter.getCurrentYear());
       float lastYear=Statistics.ProfitYear(TimeConverter.getLastYear());

        ProfitYear.setText(Statistics.ProfitYear(TimeConverter.getCurrentYear())+" DH ");

        float pourcentage= ( (currentYear -lastYear) / lastYear) * 100;
        String p = String.format("%.02f", pourcentage);
        String txt= pourcentage>0 ? "+"+ p +"%" : ""+ p +"%";
        LastYearProfit.setText(txt);
        String c= pourcentage>0 ?  "-fx-text-fill: green" : "-fx-text-fill: red";
        LastYearProfit.setStyle(c);

    }
    public void fillEntitie3() throws SQLException {
        String bestmonth="";
        //ArrayList of profit that year and get the one with the highest value
        String year=TimeConverter.getCurrentYear().toString();
        ArrayList<String> months= new ArrayList<>(Arrays.asList(year+"-01",year+"-02",year+"-03",year+"-04",year+"-05",year+"-06",
                year+"-07",year+"-08",year+"-09",year+"-10",year+"-11",year+"-12"));
        ArrayList<Float> annualProfit= new ArrayList<>();
        for (String m: months) {
            annualProfit.add(Statistics.monthlyProfit(m));
        }
        float maxprofit=Collections.max(annualProfit);

        ProfitMonth.setText(maxprofit+"DH");
        // getting the month when profit = max profit
        for (String m:months) {
            if(maxprofit==Statistics.monthlyProfit(m))
                bestmonth=m;
        }
        BestMonth.setText(bestmonth);
    }

    public void fillEntitie4() throws SQLException {
        LessDish.setText(Statistics.lessOrdered().getNom());
    }



    public static void main(String[] args) throws SQLException {
       HashMap<Plat,Integer> h= (HashMap<Plat, Integer>) Statistics.AllPlatOccurence();
        for (Plat i : h.keySet()) {
            System.out.println("plat: " + i + " occurence: " + h.get(i));
    }}
}
