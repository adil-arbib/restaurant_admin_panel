package com.team.restaurant_admin_panel.utils;

import com.team.restaurant_admin_panel.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private  static Stage mainStage;
    private static Scene scene;

    public static void init(Stage stage, boolean resizable) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(App.class.getResource("fxml/login/login.fxml"));
        scene = new Scene(loader);
        scene.getStylesheets().add(App.class.getResource("css/style.css").toExternalForm());
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.setTitle("Login");
        mainStage.getIcons().add(new Image(App.class.getResourceAsStream("assets/img/los_palos.png")));
        mainStage.setResizable(false);
        mainStage.show();
    }


    public static void replace(String file, boolean resizable, boolean maximized, String title) throws IOException {
        Parent loader = FXMLLoader.load(App.class.getResource(file));
        mainStage.close();
        scene = new Scene(loader);
        scene.getStylesheets().add(App.class.getResource("css/login.css").toExternalForm());
        mainStage.setScene(scene);
        mainStage.setTitle(title);
        mainStage.setResizable(resizable);
        mainStage.setMaximized(maximized);
        mainStage.show();
    }
}
