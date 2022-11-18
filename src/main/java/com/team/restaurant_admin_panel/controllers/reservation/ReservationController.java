package com.team.restaurant_admin_panel.controllers.reservation;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.ingredient.Ingredient;
import com.team.restaurant_admin_panel.models.reservation.Reservation;
import com.team.restaurant_admin_panel.models.reservation.ReservationDAO;
import com.team.restaurant_admin_panel.models.serveur.Serveur;
import com.team.restaurant_admin_panel.models.table.Table;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public  class ReservationController implements Initializable {

    private Bundle bundle;
    ObservableList<Reservation> data = FXCollections.observableArrayList();

    ObservableList<Reservation> searchList = FXCollections.observableArrayList();

    @FXML
    TableView<Reservation> tableView;

    @FXML
    TextField searchBar;
    @FXML
    TableColumn<Reservation, String> clServeur;

    @FXML
    TableColumn<Reservation, Number> clTable;
    @FXML
    TableColumn<Reservation, String> clDateResv;

    @FXML
    TableColumn<Reservation, Float> clPrix;

    @FXML
    TextField edtSearch;

    @FXML
    ImageView add_icon, edit_icon, delete_icon;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getStylesheets().add(App.class.getResource("css/tableView.css").toExternalForm());

        clDateResv.setCellValueFactory(new PropertyValueFactory<Reservation, String>("date"));
        clPrix.setCellValueFactory(new PropertyValueFactory<Reservation, Float>("price"));
        clServeur.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getServeur().getNom()));
        clTable.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getTable().getNum()));

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        clDateResv.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        clPrix.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        clTable.setMaxWidth(1f * Integer.MAX_VALUE * 25);
        clServeur.setMaxWidth(1f * Integer.MAX_VALUE * 25);

        bundle = Bundle.getInstance();


        try {
            ArrayList<Reservation> reservationsList = ReservationDAO.getAll();
            data.addAll(reservationsList);
            tableView.setItems(data);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        searchBar.textProperty().addListener((a, b, c) -> {
            if (c.isEmpty()) {
                tableView.setItems(data);
            } else {
                searchList.clear();
                for (Reservation i : data) {
                    if (i.getDate().toLowerCase().startsWith(c.toLowerCase()) ||
                            String.valueOf(i.getPrice()).startsWith(c.toLowerCase()) ||
                            i.getServeur().getNom().startsWith(c.toLowerCase()) ||
                            String.valueOf(i.getTable().getId()).startsWith(c.toLowerCase())
                    ) {
                        searchList.add(i);
                    }
                }
                tableView.setItems(searchList);
            }
        });

        add_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent -> {

            try {
                bundle.put("listReservation1", data);
                bundle.put("tableViewReservation", tableView);
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(App.class.getResource("fxml/reservation/addReservation.fxml"));
                Scene scene = new Scene(root);
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        edit_icon.addEventHandler(MouseEvent.MOUSE_CLICKED, MouseEvent -> {

            try {
                Reservation reservation = tableView.getSelectionModel().getSelectedItem();
                if(reservation != null){
                    bundle.put("upReservation", reservation);
                    bundle.put("listReservation", data);
                    bundle.put("listViewRes", tableView);
                    Stage stage = new Stage();
                    Parent root = null;
                    root = FXMLLoader.load(App.class.getResource("fxml/reservation/updateReservation.fxml"));
                    Scene scene = new Scene(root);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    showAlertDialog("please select a row");
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
