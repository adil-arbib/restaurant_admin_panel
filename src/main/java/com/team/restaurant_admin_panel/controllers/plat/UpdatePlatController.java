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
import java.util.ResourceBundle;

public class UpdatePlatController implements Initializable {

    private CustomPlat customUpdatedPlat;
    private ObservableList<CustomPlat> data;
    private TableView<CustomPlat> tableView;

    @FXML
    TextField edtNom, edtPrix;
    @FXML
    TextArea edtDesc;
    @FXML ComboBox<Categorie> comboCategorie;
    @FXML
    ImageView imageView;
    @FXML
    Label lblSelectImg;

    ObservableList<Categorie> listCategorie = FXCollections.observableArrayList();
    ArrayList<Categorie> categories;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Bundle bundle = Bundle.getInstance();
        customUpdatedPlat = (CustomPlat) bundle.get("customUpdatedPlat");
        data = (ObservableList<CustomPlat>) bundle.get("listPlat");
        tableView = (TableView<CustomPlat>) bundle.get("tableViewPlat");


        try {
            categories = CategorieDAO.getAll();
            listCategorie.addAll(categories);
            comboCategorie.setItems(listCategorie);
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

        if(customUpdatedPlat != null){
            Plat updatedPlat = customUpdatedPlat.getPlat();
            displayInfo(updatedPlat);
            lblSelectImg.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    Image image = new Image(file.toURI().toString());
                    try {
                        InputStream inputStream = new FileInputStream(file.getAbsoluteFile());
                        updatedPlat.setImg(inputStream.readAllBytes());
                    } catch (FileNotFoundException ex) {} catch (IOException ex) {}
                    System.out.println(file.getAbsoluteFile());
                    imageView.setImage(image);

                }

            });
            customUpdatedPlat.setImageView(imageView);
        }
    }

    private void displayInfo(Plat updatedPlat) {
        edtNom.setText(updatedPlat.getNom());
        edtPrix.setText(updatedPlat.getPrice()+"");
        edtDesc.setText(updatedPlat.getDescription());
        int index = findCategorie(categories,updatedPlat.getCategorie().getId());
        if(index != -1) comboCategorie.getSelectionModel().select(index);
        Image img = new Image(new ByteArrayInputStream(updatedPlat.getImg()),200,200,true,true);
        imageView.setImage(img);

    }

    private int findCategorie(ArrayList<Categorie> list, int id){
        int index = 0;
        for(Categorie c : list){
            if(c.getId()==id) return index;
            index++;
        }
        return -1;
    }

    public void btnEventSave(ActionEvent actionEvent) throws SQLException, ParseException {
        if(!inputsREmpty()){
            PlatDAO plat = getInsertedPlat();
            plat.update();
            data.remove(customUpdatedPlat);
            data.add(new CustomPlat(plat, new ImageView(
                    new Image(new ByteArrayInputStream(plat.getImg()),50,30,true,true)
            )));
            tableView.setItems(data);
            Stage stage = (Stage)edtNom.getScene().getWindow();
            stage.close();
            PlatController.updatePlatOpen = false;
            showAlertDialog("updated successfully !!");
        }else showAlertDialog("all fields are required");
    }

    private void showAlertDialog(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


    public void btnEventCancel(ActionEvent actionEvent) {
        Stage stage = (Stage)edtNom.getScene().getWindow();
        stage.close();
        PlatController.updatePlatOpen = false;
    }

    private boolean inputsREmpty(){
        return edtNom.getText().isEmpty() ||
                edtPrix.getText().isEmpty() || edtDesc.getText().isEmpty();
    }

    private PlatDAO getInsertedPlat(){
        return new PlatDAO(
                customUpdatedPlat.getPlat().getId(),
                edtNom.getText(),
                Float.parseFloat(edtPrix.getText()),
                edtDesc.getText(),
                customUpdatedPlat.getPlat().getImg(),
                comboCategorie.getValue()
        );
    }
}
