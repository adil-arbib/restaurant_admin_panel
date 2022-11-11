package com.team.restaurant_admin_panel.Utils;

import com.team.restaurant_admin_panel.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageManager {
    private  static Stage mainStage;
    private static Scene scene;

    public static void init(Stage stage, boolean resizable) throws IOException {
        mainStage = stage;
        Parent loader = FXMLLoader.load(App.class.getResource("fxml/login.fxml"));
        scene = new Scene(loader);
        mainStage.setResizable(resizable);
        mainStage.setScene(scene);
        mainStage.setResizable(false);
        mainStage.show();
    }

    public static void replace(String file) throws IOException {
        Parent loader = FXMLLoader.load(App.class.getResource(file));
        scene = null;
        scene = new Scene(loader);
        mainStage.setScene(scene);
        mainStage.setResizable(true);
        mainStage.setMaximized(true);
        mainStage.show();
    }
}
