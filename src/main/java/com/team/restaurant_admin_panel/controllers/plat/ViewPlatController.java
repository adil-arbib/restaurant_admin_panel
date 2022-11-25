package com.team.restaurant_admin_panel.controllers.plat;

import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewPlatController implements Initializable {

    @FXML
    Label plat_nom;

    @FXML
    Button btnCancelDisplay;

    @FXML
    ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Bundle bundle = Bundle.getInstance();
        Plat p = (Plat) bundle.get("selectedPlat");
        Image img = new Image(new ByteArrayInputStream(p.getImg()),200,200,true,true);
        plat_nom.setText(p.getNom());
        imageView.setImage(img);

    }

    public void btnEventCancel(ActionEvent actionEvent){
        Stage stage = (Stage)btnCancelDisplay.getScene().getWindow();
        stage.close();
    }
}
