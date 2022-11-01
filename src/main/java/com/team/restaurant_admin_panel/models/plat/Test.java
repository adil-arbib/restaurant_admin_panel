package com.team.restaurant_admin_panel.models.plat;

import com.team.restaurant_admin_panel.models.ResourcesManager;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        ResourcesManager.connect();

        PlatDAO platDAO = new PlatDAO(101,"pizza",49.99);

        platDAO.add();

        ResourcesManager.close();
    }
}
