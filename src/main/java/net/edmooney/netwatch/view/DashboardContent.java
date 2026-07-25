package net.edmooney.netwatch.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.view.components.DashboardCard;

public class DashboardContent extends VBox {

    public DashboardContent() {

        setSpacing(20);
        getStyleClass().add("content-area");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("dashboard-title");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);

        DashboardCard uploadCard = new DashboardCard("Upload", "0 Mbps");
        DashboardCard downloadCard = new DashboardCard("Download", "0 Mbps");
        DashboardCard hostsCard = new DashboardCard("Connected Hosts", "0");
        DashboardCard portsCard = new DashboardCard("Active Ports", "0");

        grid.add(uploadCard, 0, 0);
        grid.add(downloadCard, 1, 0);
        grid.add(hostsCard, 0, 1);
        grid.add(portsCard, 1, 1);

        getChildren().addAll(title, grid);
    }
}