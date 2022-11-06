package com.team.restaurant_admin_panel.controllers;

import com.team.restaurant_admin_panel.App;
import com.team.restaurant_admin_panel.models.admin.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class Login implements Initializable {

    public Button btnConnect;
    @FXML
    TextField username, psw_ad;

    @FXML
    BorderPane container;

    private String fxmlURL = "fxml/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*
        try{
            Parent fxml = FXMLLoader.load(App.class.getResource(fxmlURL + "side-menu.fxml"));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
        } catch (IOException e){
            e.printStackTrace();
        }
         */
    }

    public void sideMenu(ActionEvent actionEvent) throws IOException {

    }

    public void btnConnect(ActionEvent actionEvent) throws IOException, SQLException {
        // its just a test :(
        AdminDAO adminTest = new AdminDAO("lastName", "firstName", "R12334", "user1" , "1234");
        String name = username.getText();
        String passwd = psw_ad.getText();
        AdminDAO admin = new AdminDAO();
        admin.setUsername(name);
        admin.setPsw(passwd);

        /*
        if (name.isEmpty()|| passwd.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("enter login informations");
            alert.showAndWait();
            return;

        }
        if(Objects.equals(adminTest.getUsername(), name) && Objects.equals(adminTest.getPsw(), passwd)){
                Parent fxml = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxmlURL + "side-menu.fxml")));
                container.getChildren().removeAll();
                container.getChildren().setAll(fxml);

        } else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("username or password are incorrect");
                alert.showAndWait();
            }
        }


*/
        if (name.isEmpty()|| passwd.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("enter login informations");
            alert.showAndWait();
            return;
        }
        if (admin.select()){
            Parent fxml = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxmlURL + "side-menu.fxml")));
            container.getChildren().removeAll();
            container.getChildren().setAll(fxml);
            }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("username or password are incorrect");
            alert.showAndWait();
        }

    }





    public static void main(String[] args) {

    }


}
