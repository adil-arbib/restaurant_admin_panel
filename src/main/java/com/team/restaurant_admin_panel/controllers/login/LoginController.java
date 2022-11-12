package com.team.restaurant_admin_panel.controllers.login;

import com.team.restaurant_admin_panel.utils.StageManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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


//        if(checkInputs()) return;
//
//        AdminDAO adminDAO = new AdminDAO();
//        adminDAO.setUsername(name);
//        adminDAO.setPsw_ad(passwd);
//
//        Admin admin = (Admin) adminDAO.select();
//
//        if(admin != null){
//            currentAdmin = admin;
//            StageManager.replace("fxml/main-activity.fxml");
//
//        }else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("username or password incorrect");
//            alert.showAndWait();
//        }


        currentAdmin = new Admin(1,"test","test","test","test","test");
        StageManager.replace("fxml/mainActivity/main-activity.fxml");


    }

    private boolean checkInputs(){
        String us = username.getText();
        String pass  = psw_ad.getText();
        username.setStyle(us.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        psw_ad.setStyle(pass.isEmpty() ? "-fx-border-color: red; fx-border-width : 2px ;" : null);
        return us.isEmpty() || pass.isEmpty();
    }

    public static void main(String[] args) {

    }


}