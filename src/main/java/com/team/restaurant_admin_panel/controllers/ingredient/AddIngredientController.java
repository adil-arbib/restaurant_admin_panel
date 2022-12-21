package com.team.restaurant_admin_panel.controllers.ingredient;

import com.team.restaurant_admin_panel.utils.Bundle;
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
import java.util.ResourceBundle;

public class AddIngredientController implements Initializable {

    @FXML
    TextField edit_nom, edit_qte, edit_price;

    @FXML
    DatePicker edit_date;
    @FXML
    Button btn_save, btn_cancel;

    ObservableList<Ingredient> data;
    TableView<Ingredient> tableView;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        data = (ObservableList<Ingredient>) bundle.get("ingredientList");
        tableView = (TableView<Ingredient>) bundle.get("tableViewIngr");
    }

    public void btnEventSave(ActionEvent action) throws SQLException, ParseException {
        String nom = edit_nom.getText();
        String qte = edit_qte.getText();
        String uPrice = edit_price.getText();
        String date = String.valueOf(edit_date.getValue());
        if(!inputsREmpty()){
            if(data != null){
                IngredientDAO ingredient = new IngredientDAO(
                        nom,
                        date,
                        Float.parseFloat(qte),
                        Float.parseFloat((uPrice))
                );
                data.add(ingredient);
                tableView.setItems(data);
                ingredient.add();
                Stage stage = (Stage) btn_save.getScene().getWindow();
                stage.close();
            }
        } else  showAlertDialog("all fields are required");

    }

    public void btnEventCancel(ActionEvent action){
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
    private boolean inputsREmpty(){
        return  edit_nom.getText().isEmpty() ||
                edit_price.getText().isEmpty() || edit_qte.getText().isEmpty()
                || edit_date.getAccessibleText().isEmpty();
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
