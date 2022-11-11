package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddServerController implements Initializable {

    @FXML
    TextField edit_nom,edit_prenom ,edit_username, edit_password, edit_cin
              ,edit_salaire;
    @FXML
    Button btn_save, btn_cancel;

    ObservableList<Serveur> data;
    TableView<Serveur> tableView;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Serveur>) bundle.get("listServeur");
        tableView = (TableView<Serveur>) bundle.get("tableViewServeur");

    }

    public void btnEventSave(ActionEvent e) throws SQLException, ParseException {
        String editNom = edit_nom.getText();
        String editPrenom = edit_prenom.getText();
        String editUsername = edit_username.getText();
        String editCin = edit_cin.getText();
        String editPsw = edit_password.getText();
        String editSalaire = edit_salaire.getText();

        if(data != null){
            ServeurDAO server = new ServeurDAO(editNom,editPrenom,editUsername
            , editPsw ,editCin,Float.parseFloat(editSalaire));
            data.add(server);
            tableView.setItems(data);
            server.add();
            Stage stage = (Stage) btn_save.getScene().getWindow();
            stage.close();
        }


    }
    public void btnEventCancel(ActionEvent e){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();

    }


}
