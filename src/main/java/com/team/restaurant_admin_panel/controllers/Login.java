package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public Button btnConnect;
    @FXML
    TextField username, psw_ad;

    @FXML
    BorderPane container;

    private String fxmlURL = "fxml/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        try{
            Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "side-menu.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
        } catch (IOException e){
            e.printStackTrace();
        }

         */

    }


    public void sideMenu(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "side-menu.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void btnConnect(ActionEvent actionEvent) throws IOException {

    }

    public static void main(String[] args) {

    }


}
