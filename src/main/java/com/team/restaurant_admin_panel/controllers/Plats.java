package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
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



public class Plats implements Initializable {


    ObservableList<Plat> data = FXCollections.observableArrayList();

    @FXML
    TableView<Plat> tableView;

    @FXML
    TableColumn<Plat,String> clNom;

    @FXML
    TableColumn<Plat,Float> clPrix;

    @FXML
    TableColumn<Plat,String> clDescription;

    @FXML
    TableColumn<Plat,Integer> clCategorie;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clNom.setCellValueFactory(new PropertyValueFactory<Plat,String>("nom"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Plat,Float>("price"));
        clDescription.setCellValueFactory(new PropertyValueFactory<Plat,String>("description"));
        clCategorie.setCellValueFactory(new PropertyValueFactory<Plat,Integer>("id_cat"));

        try{
            ArrayList<Plat> platList = PlatDAO.getAll();
            data.addAll(platList);
            tableView.setItems(data);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
