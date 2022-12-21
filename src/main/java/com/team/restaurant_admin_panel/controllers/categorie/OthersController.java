package com.team.restaurant_admin_panel.controllers.categorie;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.table.Table;
import com.team.restaurant_admin_panel.models.table.TableDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OthersController implements Initializable {

    ObservableList<Categorie> dataCat = FXCollections.observableArrayList();
    ObservableList<Table> dataTables = FXCollections.observableArrayList();

    ObservableList<Categorie> searchList = FXCollections.observableArrayList();

    @FXML
    TableView<Categorie> tableView;

    @FXML
    TableView<Table> tableViewTa;

    @FXML
    ImageView add_icon, edit_icon, delete_icon;

    @FXML
    TextField searchBar;

    @FXML
    TableColumn<Categorie,String> cat_Label;

    @FXML
    TableColumn<Table, Integer> table_Num;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());
        tableViewTa.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        cat_Label.setCellValueFactory(new PropertyValueFactory<Categorie,String>("libelle"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY );
        //cat_Label.setMaxWidth( 1f * Integer.MAX_VALUE * 99);

        table_Num.setCellValueFactory(new PropertyValueFactory<Table,Integer>("num"));
        tableViewTa.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        //table_Num.setMaxWidth( 1f * Integer.MAX_VALUE * 1);

        try {
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            ArrayList<Table> tables = TableDAO.getAll();

            dataCat.addAll(categories);
            tableView.setItems(dataCat);

            dataTables.addAll(tables);
            tableViewTa.setItems(dataTables);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        searchBar.textProperty().addListener((a,b,c) -> {
            if (c.isEmpty()) {
                tableView.setItems(dataCat);
                searchList.clear();
                for (Categorie i : dataCat) {
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
                stage.getIcons().add(new Image(App.class.getResourceAsStream("assets/img/los_palos.png")));
                Bundle bundle = Bundle.getInstance();
                bundle.put("listCategories",dataCat);
                bundle.put("tableViewCat",tableView);
                bundle.put("listTables",dataTables);
                bundle.put("viewTables",tableViewTa);
                Parent root = FXMLLoader.load(App.class.getResource("fxml/categories/addOthers.fxml"));
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
            Table upTable = tableViewTa.getSelectionModel().getSelectedItem();

            try {
                if(upCat != null || upTable != null) {
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image(App.class.getResourceAsStream("assets/img/los_palos.png")));
                    Bundle bundle = Bundle.getInstance();
                    bundle.put("upCatgorie", upCat);
                    bundle.put("listupCategories", dataCat);
                    bundle.put("tableViewupCat", tableView);

                    bundle.put("upTable", upTable);
                    bundle.put("listUpTables", dataTables);
                    bundle.put("tableViewUpTable",tableViewTa);
                    tableViewTa.getSelectionModel().clearSelection();
                    tableView.getSelectionModel().clearSelection();
                    Parent root = FXMLLoader.load(App.class.getResource("fxml/categories/updateOthers.fxml"));
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();

                } else {
                        showAlertDialog("Select the category or the table you want to modify");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        delete_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Categorie delCat = tableView.getSelectionModel().getSelectedItem();
            Table delTable = tableViewTa.getSelectionModel().getSelectedItem();
            Bundle bundle = Bundle.getInstance();
            if(delCat != null) {
                bundle.put("deletedCat", delCat);
                bundle.put("dataCats", dataCat);
                bundle.put("listViewCat", tableView);
                bundle.put("dialogPurpose", "deleteCategorie");
                System.out.println(delCat);

                tableView.getSelectionModel().clearSelection();
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
            } else if(delTable != null){
                bundle.put("deletedTable", delTable);
                bundle.put("listTables",dataTables);
                bundle.put("listViewTable",tableViewTa);
                bundle.put("dialogPurpose", "deleteTable");

                tableViewTa.getSelectionModel().clearSelection();
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
                alert.setContentText("Select the Category or the Table you want to delete");
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
