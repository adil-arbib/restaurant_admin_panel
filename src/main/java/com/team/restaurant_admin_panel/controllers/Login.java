package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.StageManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import com.team.restaurant_admin_panel.models.admin.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {

    private static Admin currentAdmin;


    public  static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public Button btnConnect;
    @FXML
    TextField username, psw_ad;

    @FXML
    BorderPane container;

    private String fxmlURL = "fxml/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void btnConnect(ActionEvent actionEvent) throws IOException, SQLException {
        String name = username.getText();
        String passwd = psw_ad.getText();

        if (name.isEmpty()|| passwd.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("enter login informations");
            alert.showAndWait();
            return;
        }

        AdminDAO adminDAO = new AdminDAO();
        adminDAO.setUsername(name);
        adminDAO.setPsw_ad(passwd);

        Admin admin = (Admin) adminDAO.select();

        if(admin != null){
            currentAdmin = admin;
            StageManager.replace("fxml/side-menu.fxml");

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("username or password incorrect");
            alert.showAndWait();
        }

    }

    public static void main(String[] args) {

    }


}