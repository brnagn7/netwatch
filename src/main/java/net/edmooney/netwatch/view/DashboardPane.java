package net.edmooney.netwatch.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardPane {

    public VBox createView() {

        VBox dashboard = new VBox();

        dashboard.getStyleClass().add("content-area");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("dashboard-title");

        dashboard.getChildren().add(title);

        return dashboard;
    }
}