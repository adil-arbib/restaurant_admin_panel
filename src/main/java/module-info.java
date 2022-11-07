module com.team.restaurant_admin_panel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires helper;

    opens com.team.restaurant_admin_panel to javafx.fxml;
    opens com.team.restaurant_admin_panel.controllers to javafx.fxml;


    opens com.team.restaurant_admin_panel.models to javafx.base;
    opens com.team.restaurant_admin_panel.models.serveur to javafx.base;
    opens com.team.restaurant_admin_panel.models.plat to javafx.base;
    exports com.team.restaurant_admin_panel;
    exports com.team.restaurant_admin_panel.controllers;
}
