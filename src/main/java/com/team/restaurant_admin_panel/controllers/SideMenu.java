package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SideMenu implements Initializable {


    private String fxmlURL = "fxml/";

    @FXML
    AnchorPane container;

    @FXML
    Button btnDashboard, btnServeurs, btnPlats, btnIngredients,
           btnReservation, btnStatistics, btnLogout;



    public static void main(String[] args) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "dashboard.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void dashboard(ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "dashboard.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void serveurs(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "serveurs.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void plats(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "plats.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void ingredients(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "ingredients.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void reservation(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "reservation.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void statistics(ActionEvent actionEvent) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "statistics.fxml"));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Logout");
        alert.showAndWait();
    }
}
