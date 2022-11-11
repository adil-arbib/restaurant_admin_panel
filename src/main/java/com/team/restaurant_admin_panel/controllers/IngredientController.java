package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
import com.team.restaurant_admin_panel.models.ingredient.IngredientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class IngredientController implements Initializable {

    ObservableList<Ingredient> data = FXCollections.observableArrayList();

    @FXML
    TableView<Ingredient> tableView;

    @FXML
    TableColumn<Ingredient,String> clNom;

    @FXML
    TableColumn<Ingredient,Float> clQte;

    @FXML
    TableColumn<Ingredient,Float> clPrice;

    @FXML
    TableColumn<Ingredient, String> clDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        clNom.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("nom"));
        clQte.setCellValueFactory(new PropertyValueFactory<Ingredient,Float>("qte"));
        clPrice.setCellValueFactory(new PropertyValueFactory<Ingredient,Float>("unitPrice"));
        clDate.setCellValueFactory(new PropertyValueFactory<Ingredient,String>("date"));

        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );

        try {
            ArrayList<Ingredient> ingredientList = IngredientDAO.getAll();
            data.addAll(ingredientList);
            tableView.setItems(data);

       } catch (SQLException e){
           e.printStackTrace();
        }

    }
}
