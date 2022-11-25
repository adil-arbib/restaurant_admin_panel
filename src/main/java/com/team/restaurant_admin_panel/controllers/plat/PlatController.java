package com.team.restaurant_admin_panel.controllers.plat;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.ResourceBundle;



public class PlatController implements Initializable {

    public static boolean addPlatOpen = false;
    public static boolean updatePlatOpen = false;
    public static boolean deletePlatOpen = false;

    private Bundle bundle;

    ObservableList<Plat> data = FXCollections.observableArrayList();

    @FXML
    TableView<Plat> tableView;

    @FXML
    TableColumn<Plat,String> clNom;

    @FXML
    TableColumn<Plat,Float> clPrix;

    @FXML
    TableColumn<Plat,String> clDescription;

    @FXML
    TableColumn<Plat, String> clCategorie;

    @FXML
    TableColumn<Plat,ImageView> clImage;

    @FXML
    ImageView icon_add, icon_delete, icon_update, icon_view;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        clNom.setCellValueFactory(new PropertyValueFactory<Plat,String>("nom"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Plat,Float>("price"));
        clDescription.setCellValueFactory(new PropertyValueFactory<Plat,String>("description"));
        clCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().getLibelle()));
        //  clImage.setCellValueFactory(new PropertyValueFactory<Plat,ImageView>());
        // clImage.setCellValueFactory(new Image() );
        // Image img = new Image(new ByteArrayInputStream(p.getImg()),200,200,true,true);

        /*
        try {
            ArrayList<Plat> plats = PlatDAO.getAll();
            ArrayList<Image> images = new ArrayList<>();
            for(Plat p : plats){
                Image img = new Image (new ByteArrayInputStream(p.getImg()),200,200,true,true);
                images.add(img);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

         */



        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clNom.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        clPrix.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        clDescription.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        clCategorie.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
       // clImage.setMaxWidth(1f * Integer.MAX_VALUE * 20);

        bundle = Bundle.getInstance();

        try {
            ArrayList<Plat> plats = PlatDAO.getAll();
            data.addAll(plats);
            tableView.setItems(data);
        } catch (SQLException e) {}



        /*
        tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 2){
                    Plat p = tableView.getSelectionModel().getSelectedItem();
                    Image img = new Image(new ByteArrayInputStream(p.getImg()),200,200,true,true);
                    Stage stage = new Stage();
                    try {
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/showPlat.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    imageView.setImage(img);
                    plat_nom.setText(p.getNom());
                }
            }
        });
         */

        icon_add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(!addPlatOpen){
                try {
                    Stage stage = new Stage();
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/addPlat.fxml"));
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                    addPlatOpen = true;
                    stage.setOnCloseRequest(event  -> addPlatOpen = false);
                } catch (IOException exc) {
                    System.out.println(exc.getMessage());
                }
            }
        });

        icon_update.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!updatePlatOpen){
                Stage stage = new Stage();
                Plat updatedPlat = tableView.getSelectionModel().getSelectedItem();
                if (updatedPlat != null){
                    bundle.put("updatedPlat",updatedPlat);
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    try {
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/updatePlat.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                        updatePlatOpen = true;
                        stage.setOnCloseRequest(e -> updatePlatOpen = false);
                    } catch (IOException e){}

                } else showAlertDialog("Select the Plate you want to modify ");
            }
        });

        icon_delete.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(!deletePlatOpen){
                Plat deletedPlat = tableView.getSelectionModel().getSelectedItem();
                if(deletedPlat != null){
                    bundle.put("dialogPurpose","deletePlat");
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    bundle.put("deletedPlat",deletedPlat);
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/dialog/deleteDialog.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                        deletePlatOpen = true;
                        stage.setOnCloseRequest( e -> deletePlatOpen = false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else showAlertDialog("Select the Plate you want to delete");
            }
        });

        icon_view.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
                Plat viewPlat = tableView.getSelectionModel().getSelectedItem();
                Stage stage = new Stage();
                if (viewPlat != null){
                    bundle.put("selectedPlat",viewPlat);
                    try {
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/showPlat.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e){}
                } else {
                    showAlertDialog("Select the Plate you want to View");
                }
        });
    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}