package com.team.restaurant_admin_panel.controllers.cuisinier;

import com.team.restaurant_admin_panel.controllers.serveur.ServeurController;
import com.team.restaurant_admin_panel.models.cuisinier.Cuisinier;
import com.team.restaurant_admin_panel.models.cuisinier.CuisinierDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateCuisinierController implements Initializable {

    @FXML
    TextField edit_nom, edit_prenom , edit_cin, edit_salaire;
    @FXML
    Button btn_edit, btn_cancel;


    ObservableList<Cuisinier> data;
    TableView<Cuisinier> tableView;

    Cuisinier upCuisinier;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Bundle bundle = Bundle.getInstance();

        data = (ObservableList<Cuisinier>) bundle.get("listCuisinier");
        tableView = (TableView<Cuisinier>) bundle.get("tableViewCuisinier");
        upCuisinier  = (Cuisinier) bundle.get("updatedCuisinier");

        if (upCuisinier != null){
            displayInfo(upCuisinier);
        }
    }

    public void btnEventEdit(ActionEvent a) throws SQLException, ParseException {
        String editNom = edit_nom.getText();
        String editPrenom = edit_prenom.getText();
        String editCin = edit_cin.getText();
        String editSalaire = edit_salaire.getText();

        if(data != null){
            CuisinierDAO cuisnier = new CuisinierDAO(
                    upCuisinier.getId(),
                    editNom,
                    editPrenom,
                    editCin,
                    Float.parseFloat(editSalaire));

            cuisnier.update();
            data.remove(upCuisinier);
            data.add(cuisnier);
            tableView.setItems(data);
            Stage stage = (Stage) btn_edit.getScene().getWindow();
            stage.close();
            CuisinierController.updateCuisinierOpen = false; // allow access
        }
    }

    public void btnEventCancel(ActionEvent e){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
        CuisinierController.updateCuisinierOpen = false;
    }

    public void displayInfo(Cuisinier c){
        edit_nom.setText(c.getNom());
        edit_prenom.setText(c.getPrenom());
        edit_cin.setText(c.getCin());
        edit_salaire.setText(String.valueOf(c.getSalaire()));
    }
}
