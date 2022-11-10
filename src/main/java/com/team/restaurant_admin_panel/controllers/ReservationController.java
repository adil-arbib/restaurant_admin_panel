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
    TableColumn<Reservation,Integer> clId_Serv;

    @FXML
    TableColumn<Reservation,Integer> clId_Table;
    @FXML
    TableColumn<Reservation,String> clDateResv;

    @FXML
    TableColumn<Reservation,Float> clPrix;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clDateResv.setCellValueFactory(new PropertyValueFactory<Reservation,String>("date_reservation"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("price"));
        clId_Table.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("price"));
        clId_Serv.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("price"));


        try{
            ArrayList<Reservation> resList;
            ReservationDAO resTmp = new ReservationDAO();
            resList = resTmp.getAll();
            data.addAll(resList);
            tableView.setItems(data);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
