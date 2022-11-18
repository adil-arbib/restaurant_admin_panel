package com.team.restaurant_admin_panel.controllers.dashboard;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.utils.TimeConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    LineChart<String, Number> lineChart;

    @FXML
    BarChart<String, Number> barChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lineChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());
        barChart.getStylesheets().add(App.class.getResource("css/charts.css").toExternalForm());
        try {
            fillChart1(); // filling chart 1
            fillChart2(); // filling chart 2
        } catch (ParseException e) {}

    }

    private void fillChart2() throws ParseException {
        XYChart.Series series = new XYChart.Series();
        Random random = new Random();
        for(String month : TimeConverter.getLast12Months(TimeConverter.getCurrentMonth())){
            series.getData().add(new XYChart.Data(month,random.nextInt(1,10)));
        }

        barChart.getData().add(series);
    }

    private void fillChart1() throws ParseException {
        XYChart.Series series = new XYChart.Series();
        Random random = new Random();
        for(String month : TimeConverter.getLast12Months(TimeConverter.getCurrentMonth())){
            series.getData().add(new XYChart.Data(month,random.nextInt(1,10)));
        }

        lineChart.getData().add(series);
    }
}
