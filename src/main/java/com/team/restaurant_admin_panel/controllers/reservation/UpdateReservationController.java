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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UpdateReservationController implements Initializable {

    private Bundle bundle;
    TableView<Reservation> tableView;
    ObservableList<Reservation> data;
    @FXML
    DatePicker edit_date;

    @FXML
    ComboBox<Serveur> edit_server;

    @FXML
    ComboBox<Table> edit_table;

    @FXML
    Label edit_prix;

    @FXML
    ComboBox<Plat> comboPlats;

    @FXML
    ComboBox<Categorie> comboCategorie;

    @FXML
    Button btn_edit, btn_cancel;

    @FXML
     ListView<String> list_plats;

    @FXML
    ImageView delete_plat;

    ObservableList<Categorie> listCategories = FXCollections.observableArrayList();
    ObservableList<Plat> listPlat = FXCollections.observableArrayList();
    ObservableList<String> displayPlats = FXCollections.observableArrayList();

    ObservableList<Serveur> listServer = FXCollections.observableArrayList();

    ObservableList<Table> listTable = FXCollections.observableArrayList();


    ArrayList<Plat> allPlats;
    ArrayList<Categorie> categories;

    ArrayList<String> platsNames = new ArrayList<>();

    Reservation res;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        res = (Reservation) bundle.get("upReservation");
        data = (ObservableList<Reservation>) bundle.get("listReservation");
        tableView = (TableView<Reservation>) bundle.get("listViewRes");

        System.out.println(res);
        edit_date.setDisable(true);
        try {
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            allPlats = res.getListPlat();
            listCategories.addAll(categories);



            for(Plat p : res.getListPlat()){
                platsNames.add(p.getNom());
            }
            displayPlats.addAll(platsNames);
            list_plats.setItems(displayPlats);

            comboCategorie.setConverter(new StringConverter<Categorie>() {
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
            comboCategorie.setItems(listCategories);

            comboCategorie.getSelectionModel().selectedItemProperty().addListener(e -> {
                try {
                    listPlat.clear();
                    listPlat.addAll(PlatDAO.selectPLatByIdCat(
                            comboCategorie.getSelectionModel().getSelectedItem().getId()
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
            comboPlats.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Plat>() {
                @Override
                public void changed(ObservableValue<? extends Plat> observableValue, Plat plat, Plat t1) {
                    platsNames.clear();
                    if (allPlats.size() == 0)
                        edit_prix.setText("0");
                    if(t1 != null){
                        allPlats.add(new Plat(t1));
                        comboPlats.getSelectionModel().clearSelection();
                        platsNames.add(t1.getNom());
                        System.out.println(allPlats);
                        edit_prix.setText(getTotalPrice(allPlats)+ " dh");
                    }
                    displayPlats.addAll(platsNames);
                    platsNames.clear();
                }
            });
            list_plats.setItems(displayPlats);
            platsNames.clear();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            displayInfo(res);
        } catch (ParseException | SQLException e) {
            throw new RuntimeException(e);
        }

        delete_plat.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(list_plats.getSelectionModel().getSelectedItem() != null) {
                String remName = list_plats.getSelectionModel().getSelectedItem();
                int index = list_plats.getSelectionModel().getSelectedIndex();
                //listAddPlats.removeIf(p -> Objects.equals(p.getNom(), remName));
                if(index != -1){
                    allPlats.remove(index);
                    platsNames.remove(remName);
                }

            }else showAlertDialog("Please select a plat");
            displayPlats.remove(list_plats.getSelectionModel().getSelectedItem());
            displayPlats.addAll(platsNames);
            if(allPlats.size() == 0 ) {edit_prix.setText("0 dh");}
            edit_prix.setText(getTotalPrice(allPlats) + " dh");
        });
        list_plats.setItems(displayPlats);
    }
    public void displayInfo(Reservation reservation) throws ParseException, SQLException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(reservation.getDate());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        edit_prix.setText(String.valueOf(res.getPrice()));
        edit_date.setValue(sqlDate.toLocalDate());

        ArrayList<Serveur> serv = ServeurDAO.getAll();
        ArrayList<Table> tables = TableDAO.getAll();

        listServer.addAll(serv);
        edit_server.setItems(listServer);
        edit_server.getSelectionModel().select(res.getServeur());
        edit_server.setConverter(new StringConverter<Serveur>() {
            @Override
            public String toString(Serveur serveur) {
                if(serveur != null)
                    return serveur.getUsername();
                return null;
            }

            @Override
            public Serveur fromString(String s) {
                return null;
            }
        });

        listTable.addAll(tables);
        edit_table.setItems(listTable);
        edit_table.getSelectionModel().select(res.getTable());
        edit_table.setConverter(new StringConverter<Table>() {
            @Override
            public String toString(Table table) {
                if(table != null)
                    return String.valueOf(table.getNum());
                return null;
            }

            @Override
            public Table fromString(String s) {
                return null;
            }
        });

    }

    public Float getTotalPrice(ArrayList<Plat> listPlats){
        float sum = 0;
        for(Plat p : listPlats){
            sum += p.getPrice();
        }
        return sum;
    }
    public void btnEdit(ActionEvent actionEvent) throws ParseException, SQLException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(res.getDate());
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        edit_date.setValue(sqlDate.toLocalDate());


        edit_prix.setText(getTotalPrice(allPlats) + " dh");
        Serveur serveur = edit_server.getValue();
        Table table = edit_table.getValue();

        ReservationDAO reservation = new ReservationDAO(
                res.getId(),
                res.getDate(),
                getTotalPrice(allPlats),
                serveur,
                table,
                allPlats
        );
        if(serveur != null && table != null && allPlats != null){
            reservation.update();
            System.out.println(reservation);
            data.remove(res);
            data.add(reservation);
            tableView.setItems(data);
            if(!reservation.update()){
                showAlertDialog("something went wrong");
                System.out.println(reservation.update());
                return;
            }
                Stage stage = (Stage)btn_edit.getScene().getWindow();
                stage.close();
                showAlertDialog("updated successfully !!");
                System.out.println(reservation.update());
        }
        else{ showAlertDialog("all fields are required");}

    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    public void btnCancel(ActionEvent actionEvent){
        Stage stage = (Stage)btn_cancel.getScene().getWindow();
        stage.close();
    }

}
