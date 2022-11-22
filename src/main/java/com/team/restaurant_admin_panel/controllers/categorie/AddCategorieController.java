package com.team.restaurant_admin_panel.controllers.categorie;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.FXCollections;
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

public class AddCategorieController implements Initializable {

    ObservableList<Categorie> data ;
    TableView<Categorie> tableView;


    @FXML
    TextField inputCat;

    @FXML
    Button btnSave, btnCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Categorie>) bundle.get("listCategories");
        tableView = (TableView<Categorie>) bundle.get("tableViewCat");
    }

    public void btnEventSave(ActionEvent actionEvent) throws SQLException {
        String labelCat = inputCat.getText();

        if(data != null){
            CategorieDAO cat = new CategorieDAO(labelCat);
            data.add(cat);
            tableView.setItems(data);
            cat.add();
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }
    }

    public void btnEventCancel(ActionEvent actionEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
