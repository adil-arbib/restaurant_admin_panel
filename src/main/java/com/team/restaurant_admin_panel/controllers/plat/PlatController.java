package com.team.restaurant_admin_panel.controllers.plat;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.plat.CustomPlat;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.ResourceBundle;



public class PlatController implements Initializable {

    public static boolean addPlatOpen = false;
    public static boolean updatePlatOpen = false;
    public static boolean deletePlatOpen = false;

    private Bundle bundle;

    ObservableList<CustomPlat> data = FXCollections.observableArrayList();

    @FXML
    TableView<CustomPlat> tableView;

    @FXML
    TableColumn<CustomPlat,String> clNom;

    @FXML
    TableColumn<CustomPlat,String> clPrix;

    @FXML
    TableColumn<CustomPlat,String> clDescription;

    @FXML
    TableColumn<CustomPlat, String> clCategorie;

    @FXML
    TableColumn<CustomPlat,ImageView> clImage;

    @FXML
    ImageView icon_add, icon_delete, icon_update;





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        clNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlat().getNom()));
        clPrix.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlat().getPrice()+""));
        clDescription.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlat().getDescription()));
        clCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlat().getCategorie().getLibelle()));
        clImage.setCellValueFactory(new PropertyValueFactory<>("imageView"));

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clNom.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        clPrix.setMaxWidth( 1f * Integer.MAX_VALUE * 20);
        clDescription.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        clCategorie.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );
        clImage.setMaxWidth( 1f * Integer.MAX_VALUE * 20 );


        bundle = Bundle.getInstance();

        try {
            ArrayList<Plat> plats = PlatDAO.getAll();
            convert(plats);
            tableView.setItems(data);
        } catch (SQLException e) {}


        icon_add.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if(!addPlatOpen){
                try {
                    Stage stage = new Stage();
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/addPlat.fxml"));
                    Scene scene = new Scene(root);
                    scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
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
                CustomPlat customUpdatedPlat = tableView.getSelectionModel().getSelectedItem();
                if (customUpdatedPlat != null){
                    bundle.put("customUpdatedPlat",customUpdatedPlat);
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    try {
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/plat/updatePlat.fxml"));
                        Scene scene = new Scene(root);
                        scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
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
                CustomPlat customDeletedPlat = tableView.getSelectionModel().getSelectedItem();
                if(customDeletedPlat != null){
                    bundle.put("dialogPurpose","deletePlat");
                    bundle.put("listPlat",data);
                    bundle.put("tableViewPlat",tableView);
                    bundle.put("customDeletedPlat",customDeletedPlat);
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


    }


    private void convert(ArrayList<Plat> plats) {
        for(Plat plat : plats) {
            Image img = new Image(new ByteArrayInputStream(plat.getImg()),50,30,true,true);
            data.add(new CustomPlat(plat, new ImageView(img)));
        }
    }



    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}