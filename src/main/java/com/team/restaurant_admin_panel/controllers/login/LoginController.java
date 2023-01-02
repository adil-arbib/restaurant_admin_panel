package com.team.restaurant_admin_panel.controllers.login;

import com.team.restaurant_admin_panel.models.admin.AdminDAO;
import com.team.restaurant_admin_panel.utils.StageManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static Admin currentAdmin;


    public  static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public Button btnConnect;
    public Button btnSign1;

    @FXML
    TextField username, psw_ad;

    @FXML
    Label errorField;

    @FXML
    BorderPane container;

    private final String fxmlURL = "fxml/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        errorField.setVisible(false);
    }


    public void btnConnect(ActionEvent actionEvent) throws IOException, SQLException {
        String name = username.getText();
        String passwd = psw_ad.getText();


        if(checkInputs()) return;

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setUsername(name);
        adminDAO.setPsw_ad(passwd);

        Admin admin = (Admin) adminDAO.select();

        if(admin != null){
            currentAdmin = admin;
            StageManager.replace("fxml/mainActivity/main-activity.fxml", true, true,"home");
        }else {
            errorField.setVisible(true);
        }




    }

    private boolean checkInputs(){
        String us = username.getText();
        String pass  = psw_ad.getText();
        username.setStyle(us.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        psw_ad.setStyle(pass.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        return us.isEmpty() || pass.isEmpty();
    }
    public void btnSignUP(ActionEvent actionEvent) throws IOException {
        StageManager.replace("fxml/login/inscription.fxml", false, false,"inscription");
    }

    public static void main(String[] args) {
        //System.out.println(currentAdmin);

    }


}