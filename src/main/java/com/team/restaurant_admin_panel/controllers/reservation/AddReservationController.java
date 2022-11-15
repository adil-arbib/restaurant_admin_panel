package com.team.restaurant_admin_panel.controllers.reservation;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.models.reservation.Reservation;
import com.team.restaurant_admin_panel.models.reservation.ReservationDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.serveur.ServeurDAO;
import com.team.restaurant_admin_panel.models.table.Table;
import com.team.restaurant_admin_panel.models.table.TableDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.util.StringConverter;

import java.lang.invoke.SwitchPoint;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddReservationController implements Initializable {

    private Bundle bundle;
     TableView<Reservation> tableView;
     ObservableList<Reservation> data;

    @FXML
    TextField add_prix;

    @FXML
    ComboBox<Serveur> add_server;

    @FXML
    ComboBox<Table> add_table;

    @FXML
    ComboBox<Plat> comboPlats;

    @FXML
    ComboBox<Categorie> comboCategories;

    @FXML
    DatePicker add_date;

    @FXML
    Button btn_save, btn_cancel;

    ObservableList<Categorie> listCategories = FXCollections.observableArrayList();
    ObservableList<Plat> listPlat = FXCollections.observableArrayList();
    ObservableList<Serveur> listServer = FXCollections.observableArrayList();
    ObservableList<Table> listTable = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        tableView = (TableView<Reservation>) bundle.get("tableViewReservation");
        data = (ObservableList<Reservation>) bundle.get("listReservation1");
        if(data != null) System.out.println("data is not null");
        else System.out.println("data is null");

        try {
            ArrayList<Serveur> serv = ServeurDAO.getAll();
            ArrayList<Table> tables = TableDAO.getAll();
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            ArrayList<Plat> listPlats = new ArrayList<>();
            listServer.addAll(serv);
            add_server.setItems(listServer);
            add_server.getSelectionModel().selectFirst();
            add_server.setConverter(new StringConverter<Serveur>() {
                @Override
                public String toString(Serveur serveur) {
                    if (serveur != null)
                        return serveur.getUsername();
                    return null;
                }

                @Override
                public Serveur fromString(String s) {
                    return null;
                }
            });

            listTable.addAll(tables);
            add_table.setItems(listTable);
            add_table.setConverter(new StringConverter<Table>() {
                @Override
                public String toString(Table table) {
                    if (table != null)
                        return String.valueOf(table.getNum());
                    return null;
                }

                @Override
                public Table fromString(String s) {
                    return null;
                }
            });

            listCategories.addAll(categories);
            comboCategories.setItems(listCategories);
            comboCategories.setConverter(new StringConverter<Categorie>() {
                @Override
                public String toString(Categorie categorie) {
                    if (categorie != null)
                        return categorie.getLibelle();
                    return null;
                }

                @Override
                public Categorie fromString(String s) {
                    return null;
                }
            });
            comboCategories.getSelectionModel().selectedItemProperty().addListener(e -> {

                try {
                    listPlat.clear();
                    listPlat.addAll(PlatDAO.selectPLatByIdCat(
                            comboCategories.getSelectionModel().getSelectedItem().getId()
                    ));
                    comboPlats.setItems(listPlat);
                    comboPlats.setConverter(new StringConverter<Plat>() {
                        @Override
                        public String toString(Plat plat) {
                            if (plat != null)
                                return plat.getNom();
                            return null;
                        }

                        @Override
                        public Plat fromString(String s) {
                            return null;
                        }
                    });

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSave(ActionEvent actionEvent) throws SQLException, ParseException {
            String date = String.valueOf(add_date.getValue());
            String price = add_prix.getText();

            ServeurDAO s = new ServeurDAO();
            Serveur serveur = (Serveur) s.select();


            TableDAO t = new TableDAO();
            Table table = (Table) t.select();

            ArrayList<Plat> plats = new ArrayList<>();
            plats.add(comboPlats.getValue());
            System.out.println(data);

            System.out.println(serveur);
            System.out.println(table);
            System.out.println(date);
            System.out.println(plats);

            if (data != null){
                ReservationDAO reservation = new ReservationDAO(
                        date,
                        Float.parseFloat(price),
                        serveur,
                        table,
                        plats
                );
                System.out.println(reservation);
                data.add(reservation);
                tableView.setItems(data);
                reservation.add();
                System.out.println(reservation);
                Stage stage = (Stage) btn_save.getScene().getWindow();
                stage.close();
            }
    }

    public void btnCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }
}

