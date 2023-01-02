package com.team.restaurant_admin_panel.controllers.dashboard;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.models.statistics.Statistics;
import com.team.restaurant_admin_panel.utils.TimeConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

public class DashboardController implements Initializable {

    @FXML
    LineChart<String, Number> lineChart;

    @FXML
    BarChart<String, Number> barChart;

    @FXML
    Label mostDish;
    @FXML
    Label NumberOrders_Serveur;
    @FXML
    Label NumberOrders_Dish;
    @FXML
    Label serveurOfMonth;
    @FXML
    Label Month_Profit;
    @FXML
    Label addedPourcentage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());
        barChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());
        try {
            fillChart1(); // filling chart 1
            fillChart2(); // filling chart 2
            fillEntity1();
            fillEntity2();
            fillEntity3();
        } catch (ParseException e) {} catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void fillChart2() throws ParseException, SQLException {
        XYChart.Series series = new XYChart.Series();
        ArrayList<String> last12Months=TimeConverter.getLast12Months(TimeConverter.getCurrentMonth());
        for(int i=last12Months.size(); i>0;i--) {
            series.getData().add(new XYChart.Data(last12Months.get(i-1), Statistics.totalIngredPermonth(last12Months.get(i-1))));
        }
        series.setName("Montant en DH");

        barChart.getData().add(series);
    }

    private void fillChart1() throws ParseException, SQLException {
        XYChart.Series series = new XYChart.Series();
        ArrayList<String> last12Months=TimeConverter.getLast12Months(TimeConverter.getCurrentMonth());
        for(int i=last12Months.size(); i>0;i--) {
            series.getData().add(new XYChart.Data(last12Months.get(i-1),Statistics.monthlyProfit(last12Months.get(i-1))));
        }
        series.setName("Montant en DH");
        lineChart.getData().add(series);
    }

    private  void fillEntity1() throws SQLException {
            mostDish.setText(Statistics.mostOrdered().getNom());
            NumberOrders_Dish.setText(""+Statistics.PlatOccMonth(TimeConverter.getCurrentMonth()));
        }

    private  void fillEntity2() throws SQLException {
        Serveur s= Statistics.serverOfMonth();
        serveurOfMonth.setText(s.getNom()+" "+s.getPrenom());
        NumberOrders_Serveur.setText( ""+Statistics.numberTablesServerd(s.getId(),TimeConverter.getCurrentMonth()) );

    }
    private  void fillEntity3() throws SQLException {

        Month_Profit.setText(" "+Statistics.monthlyProfit(TimeConverter.getCurrentMonth())+" DH");

        float currentMonthP= Statistics.monthlyProfit(TimeConverter.getCurrentMonth());
        float lastMonthP= Statistics.monthlyProfit(TimeConverter.getLastMonth());

            // % increase = Increase ÷ Original Number × 100
        float pourcentage= ( (lastMonthP - currentMonthP) / lastMonthP) * 100;
        String p = String.format("%.02f", pourcentage);
       String txt= pourcentage>0 ? "+"+ p +"%" : ""+ p +"%";
        addedPourcentage.setText(txt);
        String c= pourcentage>0 ?  "-fx-text-fill: green" : "-fx-text-fill: red";
        addedPourcentage.setStyle(c);


    }
}
