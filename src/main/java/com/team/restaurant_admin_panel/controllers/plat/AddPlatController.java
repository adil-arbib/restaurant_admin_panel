package com.team.restaurant_admin_panel.controllers.plat;

import com.team.restaurant_admin_panel.models.categorie.Categorie;
import com.team.restaurant_admin_panel.models.categorie.CategorieDAO;
import com.team.restaurant_admin_panel.models.plat.CustomPlat;
import com.team.restaurant_admin_panel.models.plat.Plat;
import com.team.restaurant_admin_panel.models.plat.PlatDAO;
import com.team.restaurant_admin_panel.utils.Bundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class AddPlatController implements Initializable {

    private byte[] img;
    private Bundle bundle;
    private TableView<CustomPlat> tableView;
    private ObservableList<CustomPlat> data;

    @FXML
    TextField edtNom, edtPrix;
    @FXML
    TextArea edtDesc;
    @FXML
    ComboBox<Categorie> comboCategorie;

    @FXML
    Label lblSelectImg;

    @FXML
    ImageView imageView;

    ObservableList<Categorie> listCategorie = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bundle = Bundle.getInstance();
        tableView = (TableView<CustomPlat>) bundle.get("tableViewPlat");
        data = (ObservableList<CustomPlat>) bundle.get("listPlat");
        try {
            ArrayList<Categorie> categories = CategorieDAO.getAll();
            listCategorie.addAll(categories);
            comboCategorie.setItems(listCategorie);
            comboCategorie.getSelectionModel().selectFirst();
            comboCategorie.setConverter(new StringConverter<Categorie>() {
                @Override
                public String toString(Categorie categorie) {
                    if(categorie != null) return categorie.getLibelle();
                    return null;
                }

                @Override
                public Categorie fromString(String s) {
                    return null;
                }
            });

        } catch (SQLException e) {}

        lblSelectImg.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
//            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                Image image = new Image(file.toURI().toString());
                try {
                    InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
                    img = inputStream.readAllBytes();
                } catch (FileNotFoundException ex) {} catch (IOException ex) {}
                System.out.println(file.getAbsoluteFile());
                imageView.setImage(image);

            }

        });
    }

    public void btnEventCancel(ActionEvent actionEvent) {
        Stage stage = (Stage)edtNom.getScene().getWindow();
        stage.close();
        PlatController.addPlatOpen = false;
    }

    public void btnEventSave(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!inputsREmpty()){
            PlatDAO plat = getInsertedPlat();
            int id = plat.add();
            plat.setId(id);
            data.add(new CustomPlat(plat, new ImageView(
                    new Image(new ByteArrayInputStream(plat.getImg()),50,30,true,true)
            )));
            tableView.setItems(data);
            Stage stage = (Stage)edtNom.getScene().getWindow();
            stage.close();
            PlatController.addPlatOpen = false;
            showAlertDialog(plat.getNom()+" added successfully !!");
        } else showAlertDialog("all fields are required");
    }
    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private boolean inputsREmpty(){
        return img == null || edtNom.getText().isEmpty() ||
                edtPrix.getText().isEmpty() || edtDesc.getText().isEmpty();
    }

    private PlatDAO getInsertedPlat(){
        return new PlatDAO(
                edtNom.getText(),
                Float.parseFloat(edtPrix.getText()),
                edtDesc.getText(),
                img,
                comboCategorie.getValue()
        );
    }
}
