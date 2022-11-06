package com.team.restaurant_admin_panel;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private  static Stage mainStage;
    private static Scene scene;

    public static void init(Stage stage, int width, int height, boolean resizable) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(App.class.getResource("fxml/login.fxml"));
        scene = new Scene(loader, width, height);
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void replace(String file) throws IOException {
        Parent loader = FXMLLoader.load(App.class.getResource(file));
        scene = null;
        scene = new Scene(loader);
        mainStage.setScene(scene);
        mainStage.show();
    }
}
