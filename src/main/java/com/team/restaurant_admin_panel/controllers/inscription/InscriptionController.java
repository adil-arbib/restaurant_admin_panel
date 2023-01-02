package com.team.restaurant_admin_panel.controllers.inscription;

import com.team.restaurant_admin_panel.models.admin.Admin;
import com.team.restaurant_admin_panel.models.admin.AdminDAO;
import com.team.restaurant_admin_panel.utils.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InscriptionController implements Initializable {

    @FXML
    Button inscriptionbutt;
    @FXML
    Button annulationbutt;
    @FXML
    TextField NomIns;
    @FXML
    TextField PrenomIns;
    @FXML
    TextField CININS;
    @FXML
    TextField UsernameINS;
    @FXML
    TextField PswIns;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public void btnInscription(ActionEvent actionEvent) throws IOException, SQLException {
        if(checkInputs()) return;

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setNom(NomIns.getText());
        adminDAO.setPrenom(PrenomIns.getText());
        adminDAO.setCin(CININS.getText());
        adminDAO.setUsername(UsernameINS.getText());
        adminDAO.setPsw_ad(PswIns.getText());

        if( adminDAO.add() != 0){
            showAlertDialog("admin ajouté avec succés");
            StageManager.replace("fxml/login/login.fxml", false, false,"login");
        }

    }
    public void btnAnnulation(ActionEvent actionEvent) throws IOException {
        StageManager.replace("fxml/login/login.fxml", false, false,"login");
    }
    private boolean checkInputs(){
        String nom = NomIns.getText();
        String prenom  = PrenomIns.getText();
        String username = UsernameINS.getText();
        String CIN= CININS.getText();
        String psw= PswIns.getText();
        NomIns.setStyle(nom.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        PrenomIns.setStyle(prenom.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        UsernameINS.setStyle(username.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        CININS.setStyle(CIN.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        PswIns.setStyle(psw.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);

        return nom.isEmpty() || prenom.isEmpty() || username.isEmpty() || CIN.isEmpty() || psw.isEmpty();
    }

}
