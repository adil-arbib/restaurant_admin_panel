package com.team.restaurant_admin_panel.controllers.categorie;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
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

public class UpdateCategorieController implements Initializable {

    ObservableList<Categorie> data;
    TableView<Categorie> tableView;


    @FXML
    TextField inputCat;

    @FXML
    Button btnEdit, btnCancel;

    Categorie upCat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Categorie>) bundle.get("listupCategories");
        tableView = (TableView<Categorie>) bundle.get("tableViewupCat");
        upCat = (Categorie) bundle.get("upCatgorie");
        if(upCat != null){
            inputCat.setText(upCat.getLibelle());
        }
    }

    public void btnEventEdit(ActionEvent actionEvent) throws SQLException {
        CategorieDAO cat = new CategorieDAO(
                upCat.getId(),
                inputCat.getText()
        );
        cat.update();
        data.remove(upCat);
        data.add(cat);
        tableView.setItems(data);

        Stage stage = (Stage) btnEdit.getScene().getWindow();
        stage.close();
    }

    public void btnEventCancel(ActionEvent actionEvent){
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
