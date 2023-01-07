package com.team.restaurant_admin_panel.controllers.cuisinier;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.cuisinier.Cuisinier;
import com.team.restaurant_admin_panel.models.cuisinier.CuisinierDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CuisinierController implements Initializable {

    public static boolean addCuisinierOpen = false;
    public static boolean deleteCuisinierOpen = false;
    public static boolean updateCuisinierOpen = false;
    ObservableList<Cuisinier> data = FXCollections.observableArrayList();
    ObservableList<Cuisinier> searchList = FXCollections.observableArrayList();

    ObservableList<Cuisinier> newList = FXCollections.observableArrayList();

    @FXML
    TableView<Cuisinier> tableView;

    @FXML
    TableColumn<Cuisinier,String> clCin;

    @FXML
    TableColumn<Cuisinier,String> clNom;

    @FXML
    TableColumn<Cuisinier,String> clPrenom;

    @FXML
    TableColumn<Cuisinier,Float> clSalaire;

    @FXML
    ImageView icon_add, icon_update, icon_delete;

    @FXML
    TextField searchBar;

    Bundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        bundle = Bundle.getInstance();
        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        clCin.setCellValueFactory(new PropertyValueFactory<Cuisinier,String>("cin"));
        clNom.setCellValueFactory(new PropertyValueFactory<Cuisinier,String>("nom"));
        clPrenom.setCellValueFactory(new PropertyValueFactory<Cuisinier,String>("prenom"));
        clSalaire.setCellValueFactory(new PropertyValueFactory<Cuisinier,Float>("salaire"));

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clCin.setMaxWidth( 1f * Integer.MAX_VALUE * 25 ); // 50% width
        clNom.setMaxWidth( 1f * Integer.MAX_VALUE * 25 ); // 30% width
        clPrenom.setMaxWidth( 1f * Integer.MAX_VALUE * 25);
        clSalaire.setMaxWidth( 1f * Integer.MAX_VALUE * 25 ); // 20% width

        new Thread(() -> {
            try {
                ArrayList<Cuisinier> cuisinierList = CuisinierDAO.getAll();
                data.addAll(cuisinierList);
                tableView.setItems(data);
            } catch (SQLException e) {}

        }).start();

        searchBar.textProperty().addListener((a,b,c) -> {
            if (c.isEmpty()) {
                tableView.setItems(data);
            } else {
                searchList.clear();
                for (Cuisinier d : data) {
                    if (d.getCin().toLowerCase().startsWith(c.toLowerCase()) ||
                            d.getNom().toLowerCase().startsWith(c.toLowerCase()) ||
                            d.getPrenom().toLowerCase().startsWith(c.toLowerCase())
                    ) {
                        searchList.add(d);
                    }
                }
                tableView.setItems(searchList);
            }
        });

        icon_add.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(!addCuisinierOpen){
                try {
                    Stage stage = new Stage();
                    bundle.put("listCuisnier",data);
                    bundle.put("tableViewCuisinier",tableView);
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/cuisinier/addCuisinier.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    addCuisinierOpen = true;
                    stage.setOnCloseRequest(e -> addCuisinierOpen = false);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        icon_update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(!updateCuisinierOpen){
                Stage stage = new Stage();
                Cuisinier updatedCuisinier = tableView.getSelectionModel().getSelectedItem();
                if (updatedCuisinier != null){
                    bundle.put("updatedCuisinier",updatedCuisinier);
                    bundle.put("listCuisinier",data);
                    bundle.put("tableViewCuisinier",tableView);
                    Parent root;
                    try {
                        root = FXMLLoader.load(App.class.getResource("fxml/cuisinier/updateCuisinier.fxml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    updateCuisinierOpen = true;
                    stage.setOnCloseRequest(e -> updateCuisinierOpen = false);
                } else showAlertDialog("Select the cook you want to modify");
            }
        });
        icon_delete.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
           // if(!deleteCuisinierOpen){
                //System.out.println("clicked");
                Cuisinier deleteCuisinier = tableView.getSelectionModel().getSelectedItem();
                //System.out.println(deleteCuisinier);
                if(deleteCuisinier != null){
                    bundle.put("dialogPurpose","deleteCuisinier");
                    bundle.put("listCuisinier",data);
                    bundle.put("tableViewCuisinier",tableView);
                    bundle.put("deletedCuisinier",deleteCuisinier);
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/dialog/deleteDialog.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
             //           deleteCuisinierOpen = true;
               //         stage.setOnCloseRequest(e -> deleteCuisinierOpen = false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else showAlertDialog("Select the cook u want to delete");
         //x }
        });

    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}

