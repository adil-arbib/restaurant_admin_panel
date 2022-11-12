package com.team.restaurant_admin_panel.controllers.serveur;

import com.team.restaurant_admin_panel.utils.Bundle;
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

public class UpdateServerController implements Initializable {

    @FXML
    TextField edit_nom,edit_prenom ,edit_username, edit_password, edit_cin
            ,edit_salaire;
    @FXML
    Button btn_edit, btn_cancel;

    ObservableList<Serveur> data;
    TableView<Serveur> tableView;

    Serveur upServer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        Bundle bundle = Bundle.getInstance();
        upServer = (Serveur) bundle.get("updatedServer");
        data  = (ObservableList<Serveur>) bundle.get("listServeur");
        tableView = (TableView<Serveur>) bundle.get("tableViewServeur");
       System.out.println(upServer);
        if( upServer != null){
            displayInfo(upServer);
        }
    }

    public void btnEventEdit(ActionEvent e) throws SQLException, ParseException {
        String upNom = edit_nom.getText();
        String upPrenom = edit_prenom.getText();
        String upUsername = edit_username.getText();
        String upCin = edit_cin.getText();
        String upPsw = edit_password.getText();
        float upSalaire = Float.parseFloat(edit_salaire.getText());
        /*
        ServeurDAO updatedServer = new ServeurDAO(
                upServer.getId(),
                upNom,
                upPrenom,
                upUsername,
                upCin,
                upPsw,
                upSalaire
                );
         */
        if(data != null){
            ServeurDAO updatedServer = new ServeurDAO(
                    upServer.getId(),
                    upNom,
                    upPrenom,
                    upUsername,
                    upPsw,
                    upCin,
                    upSalaire
            );
            updatedServer.update();
            data.remove(upServer);
            data.add(updatedServer);
            tableView.setItems(data);
            Stage stage = (Stage) btn_edit.getScene().getWindow();
            stage.close();
        }
        //System.out.println(updatedServer);
        /*
        edit_nom.setText(upNom);
        edit_prenom.setText(upPrenom);
        edit_username.setText(upUsername);
        edit_cin.setText(upCin);
        edit_password.setText(upPsw);
        edit_salaire.setText(String.valueOf(upSalaire));
         */
    }
    public void btnEventCancel(ActionEvent e){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();

    }
    private void displayInfo(Serveur s) {
        edit_nom.setText(s.getNom());
        edit_prenom.setText(s.getPrenom());
        edit_username.setText(s.getUsername());
        edit_cin.setText(s.getCin());
        edit_password.setText(s.getPsw_ser());
        edit_salaire.setText(s.getSalaire() + "");
    }


}
