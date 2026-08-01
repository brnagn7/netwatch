package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.controller.NavigationController;

public class SidebarView extends VBox {

    public SidebarView(NavigationController navigationController) {

        setPrefWidth(200);
        setSpacing(10);
        setPadding(new Insets(15));
        getStyleClass().add("sidebar");

        Button dashboardButton = new Button("Dashboard");
        Button hostsButton = new Button("Hosts");
        Button portsButton = new Button("Ports");
        Button settingsButton = new Button("Settings");

        dashboardButton.getStyleClass().add("sidebar-button");
        hostsButton.getStyleClass().add("sidebar-button");
        portsButton.getStyleClass().add("sidebar-button");
        settingsButton.getStyleClass().add("sidebar-button");

        dashboardButton.setOnAction(event ->
                navigationController.showDashboard());

        hostsButton.setOnAction(event ->
                navigationController.showHosts());

        portsButton.setOnAction(event ->
                navigationController.showPorts());

        settingsButton.setOnAction(event ->
                navigationController.showSettings());

        getChildren().addAll(
                dashboardButton,
                hostsButton,
                portsButton,
                settingsButton
        );
    }
}