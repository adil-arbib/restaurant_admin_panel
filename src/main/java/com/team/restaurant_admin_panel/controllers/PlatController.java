package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.plat.Plat;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;



public class PlatController implements Initializable {


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




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clNom.setCellValueFactory(new PropertyValueFactory<Plat,String>("nom"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Plat,Float>("price"));
        clDescription.setCellValueFactory(new PropertyValueFactory<Plat,String>("description"));
        clCategorie.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategorie().getLibelle()));

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        clNom.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );
        clPrix.setMaxWidth( 1f * Integer.MAX_VALUE * 25);
        clDescription.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );
        clCategorie.setMaxWidth( 1f * Integer.MAX_VALUE * 25 );


        // test ( static data )
        ArrayList<Plat> plats = new ArrayList<>();
        plats.add(new Plat("Buffalo Pizza",100.21f,"desc",null,new Categorie("Pizza")));
        plats.add(new Plat("Tacos poulet",29.99f,"desc",null,new Categorie("Tacos")));
        data.addAll(plats);
        tableView.setItems(data);


    }
}
