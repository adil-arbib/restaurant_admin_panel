package com.team.restaurant_admin_panel.controllers.categorie;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.table.Table;
import com.team.restaurant_admin_panel.models.table.TableDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddOthersController implements Initializable {

    ObservableList<Categorie> data;
    TableView<Categorie> tableView;

    ObservableList<Table> dataTable;
    TableView<Table> tableViewTables;


    @FXML
    TextField inputCat, inputTable;

    @FXML
    Button btnSave, btnSaveTable, btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Categorie>) bundle.get("listCategories");
        tableView = (TableView<Categorie>) bundle.get("tableViewCat");

        dataTable = (ObservableList<Table>) bundle.get("listTables");
        tableViewTables = (TableView<Table>) bundle.get("viewTables");


    }

    public void btnEventSaveCat(ActionEvent actionEvent) throws SQLException {
        String labelCat = inputCat.getText();

        if (data != null) {
            CategorieDAO cat = new CategorieDAO(labelCat);
            data.add(cat);
            tableView.setItems(data);
            cat.add();
            if (cat.add() != -1){
                showAlertDialog("Category added successfully ");
            }
        }
    }
    public void btnEventSaveTable(ActionEvent actionEvent) throws SQLException {
        int num = Integer.parseInt(inputTable.getText());
        if(dataTable != null){
            TableDAO table = new TableDAO(num);
            dataTable.add(table);
            tableViewTables.setItems(dataTable);
            table.add();
            if (table.add() != -1){
                showAlertDialog("Table added successfully ");
            }
        }

    }

    public void btnEventCancel(ActionEvent actionEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}


