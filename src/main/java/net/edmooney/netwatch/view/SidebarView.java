package net.edmooney.netwatch.view;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SidebarView extends VBox {

    public SidebarView() {

        setPrefWidth(250);
        getStyleClass().add("side-bar");

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

        getChildren().addAll(
                dashboardButton,
                interfacesButton,
                hostsButton,
                historyButton,
                settingsButton
        );
    }
}