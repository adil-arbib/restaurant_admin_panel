package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.controllers.login.LoginController;
import com.team.restaurant_admin_panel.models.admin.Admin;
import com.team.restaurant_admin_panel.utils.StageManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainActivityController implements Initializable {

    private Admin currentAdmin;

    private String fxmlURL = "fxml/";
    private String oldColor = "-fx-background-color: #495F75;";
    private String newColor = "-fx-background-color: #34495E";

    @FXML
    StackPane container;

    @FXML
    HBox hbox_dashboard, hbox_serveurs, hbox_plats, hbox_ingredients, hbox_reservations,
            hbox_statistiques , hbox_logout, hbox_categories;

    @FXML
    Label lbl_dashboard, lbl_serveurs, lbl_plats, lbl_ingredients, lbl_reservations,
        lbl_statistiques, lbl_categorie ;

    @FXML
    ImageView
        img_dashboard, img_serveur, img_plat, img_ingredient, img_reservation, img_statistique, img_cat;

    @FXML
        Pane pane_dash, pane_serv, pane_plat, pane_ing, pane_res, pane_stat, pane_cat;

    @FXML
    Label admin_username, admin_name;

    private ImageView recent_img;
    private Label recent_label;
    private Pane recent_pane;
    private String recent_img_name = "dashboard.png";

    private Color orange = Color.rgb(255, 161, 108);
    private Color black = Color.rgb(42, 42, 42);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentAdmin = LoginController.getCurrentAdmin();
        admin_name.setText(currentAdmin.getUsername());
        admin_username.setText(currentAdmin.getNom());


        try{
            load("dashboard/dashboard.fxml");
            recent_img = img_dashboard;
            recent_label = lbl_dashboard;
            recent_pane = pane_dash;
            replace(pane_dash, img_dashboard, lbl_dashboard, "dashboard.png");

        } catch (IOException e){
            e.printStackTrace();
        }

        hbox_dashboard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("dashboard/dashboard.fxml");
                replace(pane_dash, img_dashboard, lbl_dashboard, "dashboard.png");
            } catch (IOException ex) {}
        });

        hbox_serveurs.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("serveur/serveurs.fxml");
                replace(pane_serv, img_serveur, lbl_serveurs, "serveur.png");
            } catch (IOException ex) {}
        });

        hbox_plats.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("plat/plats.fxml");
                replace(pane_plat, img_plat, lbl_plats, "plat.png");
            } catch (IOException ex) {}
        });

        hbox_ingredients.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            try {
                load("ingredient/ingredients.fxml");
                replace(pane_ing, img_ingredient, lbl_ingredients, "ingredients.png");
            } catch (IOException ex) {ex.printStackTrace();}
        });

        hbox_reservations.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("reservation/reservation.fxml");
                replace(pane_res, img_reservation, lbl_reservations, "reservation.png");
            } catch (IOException ex) {}
        });

        hbox_statistiques.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("statistics/statistics.fxml");
                replace(pane_stat, img_statistique, lbl_statistiques, "statistics.png");
            } catch (IOException ex) {}
        });

        hbox_categories.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                load("categories/others.fxml");
                replace(pane_cat, img_cat, lbl_categorie, "ingredients.png");
            } catch (IOException ex) {}
        });


        hbox_logout.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                logout();
                //StageManager.replace("fxml/login/login.fxml");

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


    }


    public void logout() throws IOException {

        StageManager.replace("fxml/login/login.fxml", false, false);
    }

    private void load(String file) throws IOException {
        Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + file));
        container.getChildren().removeAll();
        container.getChildren().setAll(fxml);
    }

    private void replace(Pane pane, ImageView imageView, Label label, String img_name){
        recent_pane.setVisible(false);
        pane.setVisible(true);
        recent_pane = pane;
        recent_label.setTextFill(black);
        label.setTextFill(orange);
        recent_label = label;
        recent_img.setImage(new Image(String.valueOf(App.class.getResource("assets/img/icon/"+recent_img_name))));
        imageView.setImage(new Image(String.valueOf(App.class.getResource("assets/img/icon_clicked/"+img_name))));
        recent_img = imageView;
        recent_img_name = img_name;
    }
}
