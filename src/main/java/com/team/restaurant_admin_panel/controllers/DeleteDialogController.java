package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteDialogController implements Initializable {
    @FXML
    Label txtConfirmation;
    @FXML
    TextField edtConfirmation;
    @FXML Button btnDelete, btnCancel;



    Bundle bundle;
    String purpose;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        purpose = (String) bundle.get("dialogPurpose");

        switch (purpose){
            case "deleteServeur":
                deleteServeur();
                break;
            case "deletePlat":
                break;
        }



    }

    private void deleteServeur() {
        Serveur delServeur =(Serveur) bundle.get("deletedServeur");
        TableView<Serveur> tableView = (TableView<Serveur>) bundle.get("tableViewServeur");
        ObservableList<Serveur> data = (ObservableList<Serveur>) bundle.get("listServeur");
        if(delServeur != null){
            System.out.println(delServeur.getId());
            txtConfirmation.setText("type "+delServeur.getUsername()+" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                String input = edtConfirmation.getText();
                if(input.equals(delServeur.getUsername())){
                    ServeurDAO serveurDAO = new ServeurDAO();
                    serveurDAO.setId(delServeur.getId());
                    try {
                        serveurDAO.delete();
                        ResourcesManager.close();
                        data.remove(delServeur);
                        tableView.setItems(data);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");

                    } catch (SQLException ex) {}
                }else showAlertDialog("error try again");
            });

            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            });
        }
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
