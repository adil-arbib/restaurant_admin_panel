package com.team.restaurant_admin_panel.controllers.ingredient;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.utils.Bundle;
import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
import com.team.restaurant_admin_panel.models.ingredient.IngredientDAO;
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


public class IngredientController implements Initializable {

    ObservableList<Ingredient> data = FXCollections.observableArrayList();
    ObservableList<Ingredient> searchList = FXCollections.observableArrayList();

    public static boolean deleteIngredientOpen = false;

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
    @FXML
    TextField search_bar;

    @FXML
    ImageView icon_add , icon_update, icon_delete;


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
        search_bar.textProperty().addListener((a,b,c) -> {
            if (c.isEmpty()) {
                tableView.setItems(data);
            } else {
                searchList.clear();
                for (Ingredient i : data) {
                    if (i.getNom().toLowerCase().startsWith(c.toLowerCase()) ||
                            i.getDate().toLowerCase().startsWith(c.toLowerCase())
                    ) {
                        searchList.add(i);
                    }
                }
                tableView.setItems(searchList);
            }

        });

        icon_add.addEventHandler(MouseEvent.MOUSE_CLICKED , e -> {
            try {
                Stage stage = new Stage();
                Bundle bundle = Bundle.getInstance();
                bundle.put("ingredientList",data);
                bundle.put("tableViewIngr",tableView);
                Parent root = null;
                root = FXMLLoader.load(App.class.getResource("fxml/ingredient/addIngredient.fxml"));
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        icon_update.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Ingredient ingredient = tableView.getSelectionModel().getSelectedItem();
            if(ingredient != null){
                Stage stage = new Stage();
                Bundle bundle = Bundle.getInstance();
                bundle.put("updatedIngredient",ingredient);
                bundle.put("ingredientList",data);
                bundle.put("tableViewIngr",tableView);
                Parent root = null;
                try {
                    root = FXMLLoader.load(App.class.getResource("fxml/ingredient/updateIngredient.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                //System.out.println(ingredient);
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("please select a row");
                alert.showAndWait();
            }
        });

        icon_delete.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(!deleteIngredientOpen) {
                Ingredient deleteIngredient = tableView.getSelectionModel().getSelectedItem();
                if (deleteIngredient != null) {
                    Bundle bundle = Bundle.getInstance();
                    bundle.put("dialogPurpose", "deleteIngredient");
                    bundle.put("listIngredient", data);
                    bundle.put("tableViewIngredient", tableView);
                    bundle.put("deletedIngredient", deleteIngredient);
                    try {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(App.class.getResource("fxml/deleteDialog.fxml"));
                        Scene scene = new Scene(root);
                        stage.setResizable(false);
                        stage.setScene(scene);
                        stage.show();
                        deleteIngredientOpen = true;
                        stage.setOnCloseRequest(e -> deleteIngredientOpen = false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("please select a row");
                    alert.showAndWait();
                }
            }
        });

    }

}
