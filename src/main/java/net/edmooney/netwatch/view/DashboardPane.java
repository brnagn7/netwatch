package net.edmooney.netwatch.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.view.components.DashboardCard;

public class DashboardPane {

    public VBox createView() {

        VBox dashboard = new VBox();

        dashboard.getStyleClass().add("content-area");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("dashboard-title");

        GridPane grid = new GridPane();

        grid.setHgap(20);
        grid.setVgap(20);

        DashboardCard cardFactory = new DashboardCard();

        grid.add(cardFactory.createCard("Upload", "0 Mbps"), 0, 0);
        grid.add(cardFactory.createCard("Download", "0 Mbps"), 1, 0);

        grid.add(cardFactory.createCard("Connected Hosts", "0"), 0, 1);
        grid.add(cardFactory.createCard("Active Ports", "0"), 1, 1);

        dashboard.getChildren().addAll(title, grid);

        return dashboard;
    }
}