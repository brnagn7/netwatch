package net.edmooney.netwatch.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SidebarView {

    public VBox createView() {

        VBox sidebar = new VBox();

        sidebar.setPrefWidth(250);
        sidebar.getStyleClass().add("side-bar");

        Button dashboardButton = new Button("Dashboard");
        Button interfacesButton = new Button("Interfaces");
        Button hostsButton = new Button("Hosts");
        Button historyButton = new Button("History");
        Button settingsButton = new Button("Settings");

        dashboardButton.getStyleClass().add("sidebar-button");
        interfacesButton.getStyleClass().add("sidebar-button");
        hostsButton.getStyleClass().add("sidebar-button");
        historyButton.getStyleClass().add("sidebar-button");
        settingsButton.getStyleClass().add("sidebar-button");

        sidebar.getChildren().addAll(
                dashboardButton,
                interfacesButton,
                hostsButton,
                historyButton,
                settingsButton
        );

        return sidebar;
    }
}