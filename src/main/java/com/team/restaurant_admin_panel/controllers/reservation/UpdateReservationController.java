package com.team.restaurant_admin_panel.controllers.reservation;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.commande.Commande;
import com.team.restaurant_admin_panel.models.commande.CommandeDAO;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.reservation.Reservation;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateReservationController implements Initializable {

    private Bundle bundle;
    TableView<Reservation> tableView;
    ObservableList<Reservation> data;
    @FXML
    DatePicker edit_date;

    @FXML
    TextField edit_prix, edit_server, edit_table;

    @FXML
    ComboBox<Plat> comboPlats;

    @FXML
    ComboBox<Categorie> comboCategories;

    @FXML
    Button btn_edit, btn_cancel;

    ObservableList<Categorie> listCategories = FXCollections.observableArrayList();
    ObservableList<Plat> listPlat = FXCollections.observableArrayList();

    ArrayList<Categorie> categories;

    Reservation res;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        res = (Reservation) bundle.get("editReservation");
        data = (ObservableList<Reservation>) bundle.get("listReservation");
        tableView = (TableView<Reservation>) bundle.get("tableViewReservationEdit");

        System.out.println(res);







        try {
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            listCategories.addAll(categories);
            comboCategories.setItems(listCategories);
           // ArrayList<Plat> listPlats = new ArrayList<>(findPlats(res.getId()));
           // listPlat.addAll(listPlats);
            comboPlats.setItems(listPlat);


            comboCategories.setConverter(new StringConverter<Categorie>() {
                @Override
                public String toString(Categorie cat) {
                    if (cat != null)
                        return cat.getLibelle();
                    return null;
                }

                @Override
                public Categorie fromString(String s) {
                    return null;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            displayInfo(res);
        } catch (ParseException | SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private int findCategorie(ArrayList<Categorie> list, int id){
        int index = 0;
        for(Categorie c : list){
            if(c.getId() == id) return index;
            index++;
        }
        return -1;
    }

    public void displayInfo(Reservation reservation) throws ParseException, SQLException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getDate());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        edit_date.setValue(sqlDate.toLocalDate());
        edit_prix.setText(String.valueOf(res.getPrice()));
        edit_server.setText(res.getServeur().getNom());
        edit_table.setText(String.valueOf(res.getTable().getNum()));

    }
}
