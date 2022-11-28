module com.team.restaurant_admin_panel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires helper;
    requires com.jfoenix;


    opens com.team.restaurant_admin_panel to javafx.fxml;
    opens com.team.restaurant_admin_panel.controllers to javafx.fxml;


    opens com.team.restaurant_admin_panel.models to javafx.base;
    opens com.team.restaurant_admin_panel.models.serveur to javafx.base;
    opens com.team.restaurant_admin_panel.models.plat to javafx.base;
    opens com.team.restaurant_admin_panel.models.ingredient to javafx.base;
    opens com.team.restaurant_admin_panel.models.categorie to javafx.base;
    opens com.team.restaurant_admin_panel.models.reservation to javafx.base;
    opens com.team.restaurant_admin_panel.models.table to javafx.base;
    opens com.team.restaurant_admin_panel.models.statistics to javafx.base;


    exports com.team.restaurant_admin_panel;
    exports com.team.restaurant_admin_panel.controllers;
    exports com.team.restaurant_admin_panel.utils;
    exports com.team.restaurant_admin_panel.models.ingredient;
    exports com.team.restaurant_admin_panel.models.reservation;
    exports com.team.restaurant_admin_panel.models.plat;

    opens com.team.restaurant_admin_panel.utils to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.dashboard;
    opens com.team.restaurant_admin_panel.controllers.dashboard to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.ingredient;
    opens com.team.restaurant_admin_panel.controllers.ingredient to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.serveur;
    opens com.team.restaurant_admin_panel.controllers.serveur to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.plat;
    opens com.team.restaurant_admin_panel.controllers.plat to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.reservation;
    opens com.team.restaurant_admin_panel.controllers.reservation to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.login;
    opens com.team.restaurant_admin_panel.controllers.login to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.categorie;
    opens com.team.restaurant_admin_panel.controllers.categorie to javafx.fxml;
    exports com.team.restaurant_admin_panel.controllers.statistics;
    opens com.team.restaurant_admin_panel.controllers.statistics to javafx.fxml;

}
