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
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UpdateOthersController implements Initializable {

    ObservableList<Categorie> data;
    TableView<Categorie> tableView;

    ObservableList<Table> dataTable;

    TableView<Table> tableViewTable;


    @FXML
    TextField inputCat, inputTable;

    @FXML
    Button btnEdit, btnCancel, btnEditTable;

    Categorie upCat;
    Table upTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Categorie>) bundle.get("listupCategories");
        tableView = (TableView<Categorie>) bundle.get("tableViewupCat");

        dataTable = (ObservableList<Table>) bundle.get("listUpTables");
        tableViewTable = (TableView<Table>) bundle.get("tableViewUpTable");


        upCat = (Categorie) bundle.get("upCatgorie");
        upTable = (Table) bundle.get("upTable");
        if(upCat != null ){
            inputCat.setText(upCat.getLibelle());
        } else if (upTable != null){
            inputTable.setText(String.valueOf(upTable.getNum()));
        }

    }

    public void btnEventEditCat(ActionEvent actionEvent) throws SQLException {
        CategorieDAO cat = new CategorieDAO(
                upCat.getId(),
                inputCat.getText()
        );
        cat.update();
        data.remove(upCat);
        data.add(cat);
        tableView.setItems(data);
    }

    public void btnEventEditTable(ActionEvent actionEvent) throws SQLException {

        TableDAO table = new TableDAO(
                upTable.getId(),
                Integer.parseInt(inputTable.getText())
        );

        table.update();
        dataTable.remove(upTable);
        dataTable.add(table);
        tableViewTable.setItems(dataTable);
    }

    public void btnEventCancel(ActionEvent actionEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
