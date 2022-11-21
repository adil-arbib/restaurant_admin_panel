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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddReservationController implements Initializable {

    private Bundle bundle;
    private String date;
     TableView<Reservation> tableView;
     ObservableList<Reservation> data;

    @FXML
    ComboBox<Serveur> add_server;

    @FXML
    ComboBox<Table> add_table;

    @FXML
    ComboBox<Plat> comboPlats;

    @FXML
    ComboBox<Categorie> comboCategories;

    @FXML
    Label add_date, add_prix;

    @FXML
    Button btn_save, btn_cancel;

    @FXML
    ListView<String> added_plats;

    @FXML
    ImageView delete_plat;

    ObservableList<Categorie> listCategories = FXCollections.observableArrayList();
    ObservableList<Plat> listPlat = FXCollections.observableArrayList();
    ObservableList<Serveur> listServer = FXCollections.observableArrayList();
    ObservableList<Table> listTable = FXCollections.observableArrayList();

    ObservableList<String> listAddedPlats = FXCollections.observableArrayList();

    ArrayList<Plat> listAddPlats = new ArrayList<>();
    ArrayList<String> platsNames = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_prix.setText("0 dh");
        bundle = Bundle.getInstance();
        tableView = (TableView<Reservation>) bundle.get("tableViewReservation");
        data = (ObservableList<Reservation>) bundle.get("listReservation1");
        if(data != null) System.out.println("data is not null");
        else System.out.println("data is null");

        try {
            ArrayList<Serveur> serv = ServeurDAO.getAll();
            ArrayList<Table> tables = TableDAO.getAll();
            ArrayList<Categorie> categories = CategorieDAO.getAll();

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
        //  listener for Multiple Selections from plats combo-box
        comboPlats.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Plat>() {
            @Override
            public void changed(ObservableValue<? extends Plat> observableValue, Plat plat, Plat t1) {
                if(listAddPlats.size() == 0 ) {add_prix.setText("0 dh");}
                if(t1 != null){
                    listAddPlats.add(new Plat(t1));
                    comboPlats.getSelectionModel().select(null);
                   // System.out.println(listAddPlats);
                    platsNames.add(t1.getNom());

                }
                //listAddedPlats.addAll(String.valueOf(listAddPlats));
                listAddedPlats.addAll(platsNames);
                platsNames.clear();
                add_prix.setText(getTotalPrice(listAddPlats) + " dh");
            }
        });
        added_plats.setItems(listAddedPlats);


        delete_plat.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if(added_plats.getSelectionModel().getSelectedItem() != null) {

                String remName = added_plats.getSelectionModel().getSelectedItem();
                int index = added_plats.getSelectionModel().getSelectedIndex();
                //listAddPlats.removeIf(p -> Objects.equals(p.getNom(), remName));
                if(index != -1){
                    listAddPlats.remove(index);
                    platsNames.remove(remName);
                }
            }else showAlertDialog("select the plate you want to delete ");
            listAddedPlats.remove(added_plats.getSelectionModel().getSelectedItem());
            listAddedPlats.addAll(platsNames);
            if(listAddPlats.size() == 0 ) add_prix.setText("0 dh");
            add_prix.setText(getTotalPrice(listAddPlats) + " dh ");
        });
        added_plats.setItems(listAddedPlats);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        date = formatter.format(now);
        add_date.setText(date);

    }

    public void btnSave(ActionEvent actionEvent) throws SQLException, ParseException {
            Serveur serveur = add_server.getValue();
            Table table = add_table.getValue();

            if (data != null){
                ReservationDAO reservation = new ReservationDAO(
                        date,
                        getTotalPrice(listAddPlats),
                        serveur,
                        table,
                        listAddPlats
                );
                data.add(reservation);
                tableView.setItems(data);
                int id = reservation.add();
                reservation.setId(id);
                System.out.println(reservation);
                Stage stage = (Stage) btn_save.getScene().getWindow();
                stage.close();
            }
    }

    public void btnCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btn_cancel.getScene().getWindow();
        stage.close();
    }

    public Float getTotalPrice(ArrayList<Plat> listPlats){
        float sum = 0;
        for(Plat p : listPlats){
            sum += p.getPrice();
        }
        return sum;
    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}



