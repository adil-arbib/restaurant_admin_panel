package com.team.restaurant_admin_panel.controllers.categorie;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CategorieController implements Initializable {

    ObservableList<Categorie> data = FXCollections.observableArrayList();

    ObservableList<Categorie> searchList = FXCollections.observableArrayList();

    @FXML
    TableView<Categorie> tableView;

    @FXML
    ImageView add_icon, edit_icon, delete_icon;

    @FXML
    TextField searchBar;

    @FXML
    TableColumn<Categorie,String> cat_Label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        cat_Label.setCellValueFactory(new PropertyValueFactory<Categorie,String>("libelle"));
        tableView.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        cat_Label.setMaxWidth( 1f * Integer.MAX_VALUE * 100);

        try {
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            data.addAll(categories);
            System.out.println(categories);
            tableView.setItems(data);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        searchBar.textProperty().addListener((a,b,c) -> {
            if (c.isEmpty()) {
                tableView.setItems(data);
            } else {
                searchList.clear();
                for (Categorie i : data) {
                    if (i.getLibelle().toLowerCase().startsWith(c.toLowerCase())) {
                        searchList.add(i);
                    }
                }
                tableView.setItems(searchList);
            }
        });

        add_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                Stage stage = new Stage();
                Bundle bundle = Bundle.getInstance();
                bundle.put("listCategories",data);
                bundle.put("tableViewCat",tableView);
                Parent root = FXMLLoader.load(App.class.getResource("fxml/categories/addCategorie.fxml"));
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        edit_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {

            Categorie upCat =  tableView.getSelectionModel().getSelectedItem();

            try {
                if(upCat != null) {
                    Stage stage = new Stage();
                    Bundle bundle = Bundle.getInstance();
                    bundle.put("upCatgorie", upCat);
                    bundle.put("listupCategories", data);
                    bundle.put("tableViewupCat", tableView);
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/categories/updateCategorie.fxml"));
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } else {
                        showAlertDialog("Select the category you want to modify");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        delete_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Categorie delCat = tableView.getSelectionModel().getSelectedItem();
            if(delCat != null) {
                Bundle bundle = Bundle.getInstance();
                bundle.put("deletedCat", delCat);
                bundle.put("listCats", data);
                bundle.put("listView", tableView);
                bundle.put("dialogPurpose", "deleteCategorie");
                try {
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/dialog/deleteDialog.fxml"));
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Select the Category you want to delete");
                alert.showAndWait();

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
