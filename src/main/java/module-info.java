module com.team.restaurant_admin_panel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires helper;

    opens com.team.restaurant_admin_panel to javafx.fxml;
    opens com.team.restaurant_admin_panel.controllers to javafx.fxml;
    exports com.team.restaurant_admin_panel;
    exports com.team.restaurant_admin_panel.controllers;


}