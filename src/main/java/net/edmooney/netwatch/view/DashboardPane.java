package net.edmooney.netwatch.view;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.view.components.DashboardCard;

public class DashboardPane {

    public VBox createView() {

        VBox dashboard = new VBox();

        dashboard.getStyleClass().add("content-area");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("dashboard-title");

        DashboardCard cardFactory = new DashboardCard();

        dashboard.getChildren().addAll(
                title,
                cardFactory.createCard("Upload", "0 Mbps"),
                cardFactory.createCard("Download", "0 Mbps")
        );

        return dashboard;
    }
}