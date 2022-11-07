package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
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

public class Serveurs implements Initializable {

    ObservableList<Serveur> data = FXCollections.observableArrayList();

    @FXML
    TableView<Serveur> tableView;

    @FXML
    TableColumn<Serveur,String> clCin;

    @FXML
    TableColumn<Serveur,String> clUsername;

    @FXML
    TableColumn<Serveur,String> clNom;

    @FXML
    TableColumn<Serveur,String> clPrenom;

    @FXML
    TableColumn<Serveur,Float> clSalaire;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clCin.setCellValueFactory(new PropertyValueFactory<Serveur,String>("CIN"));
        clUsername.setCellValueFactory(new PropertyValueFactory<Serveur,String>("username"));
        clNom.setCellValueFactory(new PropertyValueFactory<Serveur,String>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<Serveur,String>("prenom"));
        clSalaire.setCellValueFactory(new PropertyValueFactory<Serveur,Float>("salaire"));
        System.out.println("teeest");
        try {
            ArrayList<Serveur> serveurList =  ServeurDAO.getAll();
            data.addAll(serveurList);
            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}