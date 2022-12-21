package com.team.restaurant_admin_panel.controllers.cuisinier;

import com.team.restaurant_admin_panel.controllers.serveur.ServeurController;
import com.team.restaurant_admin_panel.models.cuisinier.Cuisinier;
import com.team.restaurant_admin_panel.models.cuisinier.CuisinierDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;

public class AddCuisinierController implements Initializable {

    @FXML
    TextField edit_nom, edit_prenom , edit_cin, edit_salaire;
    @FXML
    Button btn_save, btn_cancel;


    ObservableList<Cuisinier> data;
    TableView<Cuisinier> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Bundle bundle = Bundle.getInstance();

        data = (ObservableList<Cuisinier>) bundle.get("listCuisnier");
        tableView = (TableView<Cuisinier>) bundle.get("tableViewCuisinier");
    }

    public void btnEventSave(ActionEvent a) throws SQLException, ParseException {
        String editNom = edit_nom.getText();
        String editPrenom = edit_prenom.getText();
        String editCin = edit_cin.getText();
        String editSalaire = edit_salaire.getText();

        if ( !inputsREmpty()){
            if(data != null){
                CuisinierDAO cuisnier = new CuisinierDAO(editNom, editPrenom, editCin ,
                        Float.parseFloat(editSalaire));

                int id = cuisnier.add();
                cuisnier.setId(id);
                data.add(cuisnier);
                tableView.setItems(data);
                CuisinierController.addCuisinierOpen = false; // allow access
                Stage stage = (Stage) btn_save.getScene().getWindow();stage.close();
            }
        } else showAlertDialog("all fields are required");

    }
    public void btnEventCancel(ActionEvent e){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
        CuisinierController.addCuisinierOpen = false; // allow access
    }

    private boolean inputsREmpty(){
        return  edit_nom.getText().isEmpty() ||
                edit_salaire.getText().isEmpty() || edit_cin.getText().isEmpty()
                || edit_prenom.getText().isEmpty();
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
