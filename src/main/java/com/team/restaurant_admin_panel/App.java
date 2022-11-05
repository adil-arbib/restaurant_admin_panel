package com.team.restaurant_admin_panel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    private double x,y;
    private int WIDTH = 600, HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        String url = "fxml/login.fxml";
        Parent root = FXMLLoader.load(App.class.getResource(url));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}