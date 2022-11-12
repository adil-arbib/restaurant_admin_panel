package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddIngredientController implements Initializable {

    @FXML
    TextField edit_nom, edit_date, edit_qte, edit_price;
    @FXML
    Button btn_save, btn_cancel;

    ObservableList<Serveur> data;
    TableView<Serveur> tableView;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Serveur>) bundle.get("ingredientList");
        tableView = (TableView<Serveur>) bundle.get("tableViewIngr");
    }

    public void btnEventSave(ActionEvent action){
    }

    public void btnEventCancel(ActionEvent action){

    }
}
