package com.team.restaurant_admin_panel.controllers.serveur;

import com.team.restaurant_admin_panel.utils.Bundle;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
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

public class AddServerController implements Initializable {
    @FXML
    TextField edit_nom,edit_prenom ,edit_username, edit_cin
              ,edit_salaire;
    @FXML
    Button btn_save, btn_cancel;

    @FXML
    PasswordField edit_password;

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

        if (!inputsREmpty()){
            if(data != null){
                ServeurDAO server = new ServeurDAO(editNom,editPrenom,editUsername
                        , editPsw ,editCin,Float.parseFloat(editSalaire));
                int id = server.add();
                server.setId(id);
                data.add(server);
                tableView.setItems(data);
                ServeurController.addServeurOpen = false; // allow access
                Stage stage = (Stage) btn_save.getScene().getWindow();
                stage.close();
            }
        } else showAlertDialog("all fields are required");

    }

    public void btnEventCancel(ActionEvent e){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
        ServeurController.addServeurOpen = false; // allow access
    }

    private boolean inputsREmpty(){
        return  edit_nom.getText().isEmpty() ||
                edit_salaire.getText().isEmpty() || edit_cin.getText().isEmpty()
                || edit_prenom.getText().isEmpty() || edit_password.getText().isEmpty();
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
