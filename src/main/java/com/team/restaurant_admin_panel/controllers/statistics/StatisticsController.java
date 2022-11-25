package com.team.restaurant_admin_panel.controllers.statistics;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;

import java.time.LocalDate;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable{

    @FXML
    Label CalendarLabel;

    @FXML


    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillCalendar();
    }

    public void fillCalendar(){
        CalendarLabel.setText(LocalDate.now().toString());
    }
    public void fillPieChart1(){
    }}
