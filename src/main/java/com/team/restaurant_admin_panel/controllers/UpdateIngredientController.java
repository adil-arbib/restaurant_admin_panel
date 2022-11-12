package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.Utils.Bundle;
import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
import com.team.restaurant_admin_panel.models.ingredient.IngredientDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;


public class UpdateIngredientController implements Initializable {

    ObservableList<Ingredient> data;
    TableView<Ingredient> tableView;
    @FXML
    TextField edit_nom , edit_qte, edit_price;

    @FXML
    Button btn_edit, btn_cancel;

    @FXML
    DatePicker edit_date;

    Ingredient upIngredient;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Ingredient>) bundle.get("ingredientList");
        tableView = (TableView<Ingredient>) bundle.get("tableViewIngr");
        upIngredient = (Ingredient) bundle.get("updatedIngredient");
        //System.out.println(upIngredient);
        if(upIngredient != null){
            displayInfo(upIngredient);
        }
    }

    public void displayInfo(Ingredient i){
        edit_nom.setText(i.getNom());
        edit_qte.setText(String.valueOf(i.getQte()));
        edit_price.setText(String.valueOf(i.getUnitPrice()));
        try {
            java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(i.getDate());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            edit_date.setValue(sqlDate.toLocalDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnEdit(ActionEvent actionEvent) throws SQLException, ParseException {
        String nom = edit_nom.getText();
        String qte = edit_qte.getText();
        String price = edit_price.getText();
        String date = String.valueOf(edit_date.getValue());

        IngredientDAO ingredient = new IngredientDAO(
                upIngredient.getId(),
                nom,
                date,
                Float.parseFloat(qte),
                Float.parseFloat(price)
        );
        ingredient.update();
        data.remove(upIngredient);
        data.add(ingredient);
        tableView.setItems(data);

        Stage stage = (Stage) btn_edit.getScene().getWindow();
        stage.close();
    }

    public void btnCancel(ActionEvent actionEvent){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}
