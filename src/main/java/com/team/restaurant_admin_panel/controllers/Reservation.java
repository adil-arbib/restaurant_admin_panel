package com.team.restaurant_admin_panel.controllers;

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
import java.util.ArrayList;
import java.util.ResourceBundle;

public  class Reservation implements Initializable {


    ObservableList<Serveur> data = FXCollections.observableArrayList();

    @FXML
    TableView<Reservation> tableView;
    @FXML
    TableColumn<Reservation,String> clDateResv;

    @FXML
    TableColumn<Reservation,Float> clPrix;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clDateResv.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date_reservation"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("price"));


            //ArrayList<Reservation> resvList = ReservationDAO.getALL();




    }
}
