package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteDialogController implements Initializable {

    Serveur delServer;

    ObservableList<Serveur> data;
    TableView<Serveur> tableView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        delServer = (Serveur) bundle.get("deletedServer");
        data = (ObservableList<Serveur>) bundle.get("listServeur");
        tableView = (TableView<Serveur>) bundle.get("tableViewServeur");
        if (delServer != null ){
            confirmeDialog();
        }

        // didnt use this class at all :/
    }
    public void confirmeDialog(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " +
                " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {

        }


    }
}
