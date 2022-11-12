package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ServeurController implements Initializable {

    public static boolean addServeurOpen = false;
    public static boolean deleteServeurOpen = false;
    public static boolean updateServeurOpen = false;

    ObservableList<Serveur> data = FXCollections.observableArrayList();
    ObservableList<Serveur> searchList = FXCollections.observableArrayList();

    ObservableList<Serveur> newList = FXCollections.observableArrayList();

    @FXML
    TableView<Serveur> tableView;

    @FXML
    TableColumn<Serveur,String> clCin;

    @FXML
    TableColumn<Serveur,String> clUsername;

    @FXML
    TableColumn<Serveur,String> clNom;

    @FXML
    TableColumn<Serveur,String> clPrenom;

    @FXML
    TableColumn<Serveur,Float> clSalaire;

    @FXML
    ImageView icon_add, icon_update, icon_delete;

    @FXML
    TextField searchBar;

    Bundle bundle;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bundle = Bundle.getInstance();

        clCin.setCellValueFactory(new PropertyValueFactory<Serveur,String>("cin"));
        clUsername.setCellValueFactory(new PropertyValueFactory<Serveur,String>("username"));
        clNom.setCellValueFactory(new PropertyValueFactory<Serveur,String>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<Serveur,String>("prenom"));
        clSalaire.setCellValueFactory(new PropertyValueFactory<Serveur,Float>("salaire"));

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clCin.setMaxWidth( 1f * Integer.MAX_VALUE * 20 ); // 50% width
        clNom.setMaxWidth( 1f * Integer.MAX_VALUE * 20 ); // 30% width
        clPrenom.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        clUsername.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );// 20% width
        clSalaire.setMaxWidth( 1f * Integer.MAX_VALUE * 20 ); // 20% width



        try {
            ArrayList<Serveur> serveurList =  ServeurDAO.getAll();
            data.addAll(serveurList);
            tableView.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }

       searchBar.textProperty().addListener((a,b,c) -> {
                   if (c.isEmpty()) {
                       tableView.setItems(data);
                   } else {
                       searchList.clear();
                       for (Serveur s : data) {
                           if (s.getCin().toLowerCase().startsWith(c.toLowerCase()) ||
                                   s.getNom().toLowerCase().startsWith(c.toLowerCase()) ||
                                   s.getPrenom().toLowerCase().startsWith(c.toLowerCase()) ||
                                   s.getUsername().toLowerCase().startsWith(c.toLowerCase())
                           ) {
                               searchList.add(s);
                           }
                       }
                       tableView.setItems(searchList);
                   }
               });

       icon_add.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           if(!addServeurOpen){
               try {
                   Stage stage = new Stage();
                   bundle.put("listServeur",data);
                   bundle.put("tableViewServeur",tableView);
                   Parent root = FXMLLoader.load(App.class.getResource("fxml/AddServeur.fxml"));
                   Scene scene = new Scene(root);
                   stage.setResizable(false);
                   stage.setScene(scene);
                   stage.show();
                   addServeurOpen = true;
                    stage.setOnCloseRequest(e -> addServeurOpen = false);
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }
           }


       });
       icon_update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           if(!updateServeurOpen){
               Stage stage = new Stage();
               Serveur updatedServer = tableView.getSelectionModel().getSelectedItem();
               if (updatedServer != null){
                   bundle.put("updatedServer",updatedServer);
                   bundle.put("listServeur",data);
                   bundle.put("tableViewServeur",tableView);
                   Parent root;
                   try {
                       root = FXMLLoader.load(App.class.getResource("fxml/updateServeur.fxml"));
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
                   Scene scene = new Scene(root);
                   stage.setResizable(false);
                   stage.setScene(scene);
                   stage.show();
                   updateServeurOpen = true;
                   stage.setOnCloseRequest(e -> updateServeurOpen = false);
               }
           }
       });
       icon_delete.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           if(!deleteServeurOpen){
               Serveur deleteServer =tableView.getSelectionModel().getSelectedItem();
               if(deleteServer != null){
                   bundle.put("dialogPurpose","deleteServeur");
                   bundle.put("listServeur",data);
                   bundle.put("tableViewServeur",tableView);
                   bundle.put("deletedServeur",deleteServer);
                   try {
                       Stage stage = new Stage();
                       Parent root = FXMLLoader.load(App.class.getResource("fxml/deleteDialog.fxml"));
                       Scene scene = new Scene(root);
                       stage.setResizable(false);
                       stage.setScene(scene);
                       stage.show();
                       deleteServeurOpen = true;
                       stage.setOnCloseRequest( e -> deleteServeurOpen = false);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }

               }
           }
        });
    }



}
