package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.StageManager;
import com.team.restaurant_admin_panel.models.admin.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideMenu implements Initializable {

    private Admin currentAdmin;

    private String fxmlURL = "fxml/";
    private String oldColor = "-fx-background-color: #495F75;";
    private String newColor = "-fx-background-color: #34495E";

    @FXML
    StackPane container;


    @FXML
    Button btnDashboard, btnServeurs, btnPlats, btnIngredients,
           btnReservation, btnStatistics, btnLogout;

    Button clickedButton;

    @FXML
    Label txtAdminUsername;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentAdmin = Login.getCurrentAdmin();
        if(currentAdmin != null){
            txtAdminUsername.setText(currentAdmin.getUsername());
        }
        try{
            load("dashboard.fxml");
            clickedButton = btnDashboard;
            clickedButton.setStyle(newColor);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void dashboard(ActionEvent actionEvent) throws IOException{
        load("dashboard.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnDashboard;
        clickedButton.setStyle(newColor);
    }

    public void serveurs(ActionEvent actionEvent) throws IOException {
        load("serveurs.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnServeurs;
        clickedButton.setStyle(newColor);
    }

    public void plats(ActionEvent actionEvent) throws IOException {
        load("plats.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnPlats;
        clickedButton.setStyle(newColor);
    }

    public void ingredients(ActionEvent actionEvent) throws IOException {
        load("ingredients.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnIngredients;
        clickedButton.setStyle(newColor);
    }

    public void reservation(ActionEvent actionEvent) throws IOException {
        load("reservation.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnReservation;
        clickedButton.setStyle(newColor);
    }

    public void statistics(ActionEvent actionEvent) throws IOException {
        load("statistics.fxml");
        clickedButton.setStyle(oldColor);
        clickedButton = btnStatistics;
        clickedButton.setStyle(newColor);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logout");
        alert.showAndWait();

        clickedButton.setStyle(oldColor);
        clickedButton = btnLogout;
        clickedButton.setStyle(newColor);
    }

    private void load(String file) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + file));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }
}
