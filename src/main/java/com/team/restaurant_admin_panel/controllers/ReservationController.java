package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.models.reservation.Reservation;
import com.team.restaurant_admin_panel.models.reservation.ReservationDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public  class ReservationController implements Initializable {


    ObservableList<Reservation> data = FXCollections.observableArrayList();

    @FXML
    TableView<Reservation> tableView;

    @FXML
    TableColumn<Reservation,Integer> clServeur;

    @FXML
    TableColumn<Reservation,Integer> clTable;
    @FXML
    TableColumn<Reservation,String> clDateResv;

    @FXML
    TableColumn<Reservation,Float> clPrix;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clDateResv.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date_reservation"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("price"));
        clServeur.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("serveur "));
        clTable.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("table"));


        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clDateResv.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );
        clPrix.setMaxWidth( 1f * Integer.MAX_VALUE * 25);
        clTable.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );
        clServeur.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );




    }
}
