package com.team.restaurant_admin_panel;

import com.team.restaurant_admin_panel.utils.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    private double x,y;
    private int WIDTH = 600, HEIGHT = 400;

    @Override
    public void start(Stage stage) throws Exception {
        StageManager.init(stage,true);
        }

    public static void main(String[] args) {
        launch();
    }
}