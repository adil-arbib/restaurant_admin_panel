package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.controllers.ingredient.IngredientController;
import com.team.restaurant_admin_panel.controllers.serveur.ServeurController;
import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.reservation.Reservation;
import com.team.restaurant_admin_panel.models.reservation.ReservationDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import com.team.restaurant_admin_panel.models.ResourcesManager;
import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
import com.team.restaurant_admin_panel.models.ingredient.IngredientDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteDialogController implements Initializable {
    @FXML
    Label txtConfirmation;
    @FXML
    TextField edtConfirmation;
    @FXML Button btnDelete, btnCancel;



    Bundle bundle;
    String purpose;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        purpose = (String) bundle.get("dialogPurpose");

        switch (purpose){
            case "deleteServeur":
                deleteServeur();
                break;
            case "deletePlat":
                deletePlat();
                break;
            case "deleteIngredient":
                deleteIngredient();
                break;
            case "deleteReservation":
                deleteReservation();
            case "deleteCategorie":
                deleteCategorie();
                break;
        }
    }

    private void deletePlat() {
        Plat delPlat =(Plat) bundle.get("deletedPlat");
        TableView<Plat> tableView = (TableView<Plat>) bundle.get("tableViewPlat");
        ObservableList<Plat> data = (ObservableList<Plat>) bundle.get("listPlat");
        if(delPlat != null){
            txtConfirmation.setText("type "+ delPlat.getNom() +" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                String input = edtConfirmation.getText();
                if(input.equals(delPlat.getNom())){
                    PlatDAO platDAO = new PlatDAO();
                    platDAO.setId(delPlat.getId());
                    try {
                        platDAO.delete();
                        data.remove(delPlat);
                        tableView.setItems(data);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");

                    } catch (SQLException ex) {}
                }else showAlertDialog("error try again");
                ServeurController.deleteServeurOpen = false;
            });

            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                ServeurController.deleteServeurOpen = false;
            });
        }
    }

    private void deleteServeur() {
        Serveur delServeur =(Serveur) bundle.get("deletedServeur");
        TableView<Serveur> tableView = (TableView<Serveur>) bundle.get("tableViewServeur");
        ObservableList<Serveur> data = (ObservableList<Serveur>) bundle.get("listServeur");
        if(delServeur != null){
            txtConfirmation.setText("type "+ delServeur.getUsername() +" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                String input = edtConfirmation.getText();
                if(input.equals(delServeur.getUsername())){
                    ServeurDAO serveurDAO = new ServeurDAO();
                    serveurDAO.setId(delServeur.getId());
                    try {
                        serveurDAO.delete();
                        data.remove(delServeur);
                        tableView.setItems(data);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");

                    } catch (SQLException ex) {}
                }else showAlertDialog("error try again");
                ServeurController.deleteServeurOpen = false;
            });

            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
                ServeurController.deleteServeurOpen = false;
            });
        }
    }
    public void deleteIngredient(){
        Ingredient delIngredient = (Ingredient) bundle.get("deletedIngredient");
        TableView<Ingredient> tableView = (TableView<Ingredient>) bundle.get("tableViewIngredient");
        ObservableList<Ingredient> data = (ObservableList<Ingredient>) bundle.get("listIngredient");
        if(delIngredient != null){
            System.out.println(delIngredient.getId());
            txtConfirmation.setText("type "+ delIngredient.getNom() +" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                String input = edtConfirmation.getText();
                if(input.equals(delIngredient.getNom())){
                    IngredientDAO ingredientDAO = new IngredientDAO();
                    ingredientDAO.setId(delIngredient.getId());
                    try {
                        ingredientDAO.delete();
                        data.remove(delIngredient);
                        tableView.setItems(data);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");

                    } catch (SQLException ex) {}
                }else showAlertDialog("error try again");
                IngredientController.deleteIngredientOpen = false;
            });

        }
        btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
    }

    public void deleteReservation(){
        Reservation delRes = (Reservation) bundle.get("deletedReservation");
        TableView<Reservation> tableViewRes = (TableView<Reservation>) bundle.get("listViewreservations");
        ObservableList<Reservation> dataRes = (ObservableList<Reservation>) bundle.get("listReservations");

        if(delRes!= null){
            txtConfirmation.setText("type "+ delRes.getServeur().getUsername() +" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                System.out.println(delRes);
                String input = edtConfirmation.getText();
                if(input.equals(delRes.getServeur().getUsername())){
                    ReservationDAO res = new ReservationDAO();
                    res.setId(delRes.getId());

                    try {
                        res.delete();
                        dataRes.remove(delRes);
                        tableViewRes.setItems(dataRes);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else showAlertDialog("an error had been occurred");
            });
            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            });
        }
    }

    public void deleteCategorie(){
        Categorie delCat = (Categorie) bundle.get("deletedCat");
        TableView<Categorie> listCats = (TableView<Categorie>) bundle.get("listView");
        ObservableList<Categorie> dataCats = (ObservableList<Categorie>) bundle.get("listCats");

        if (delCat != null){
            txtConfirmation.setText("type "+ delCat.getLibelle() +" to confirm");
            btnDelete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                String input = edtConfirmation.getText();
                if(input.equals(delCat.getLibelle())){
                    CategorieDAO cat = new CategorieDAO();
                    cat.setId(delCat.getId());

                    try {
                        cat.delete();
                        dataCats.remove(delCat);
                        listCats.setItems(dataCats);
                        Stage s = (Stage) btnDelete.getScene().getWindow();
                        s.close();
                        showAlertDialog("deleted Successfully !!");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else showAlertDialog("an error had been occurred");
            });
            btnCancel.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                Stage stage = (Stage) btnCancel.getScene().getWindow();
                stage.close();
            });
        }
    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
