package net.edmooney.netwatch.view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.view.components.DashboardCard;
import net.edmooney.netwatch.model.TrafficSnapshot;

public class DashboardContent extends VBox {

    private final DashboardCard uploadCard =
            new DashboardCard("Upload", "0 Mbps");

    private final DashboardCard downloadCard =
            new DashboardCard("Download", "0 Mbps");

    private final DashboardCard hostsCard =
            new DashboardCard("Connected Hosts", "0");

    private final DashboardCard portsCard =
            new DashboardCard("Active Ports", "0");

    public DashboardContent() {

        setSpacing(20);
        getStyleClass().add("content-area");

        Label title = new Label("Dashboard");
        title.getStyleClass().add("dashboard-title");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(uploadCard, 0, 0);
        grid.add(downloadCard, 1, 0);
        grid.add(hostsCard, 0, 1);
        grid.add(portsCard, 1, 1);

        getChildren().addAll(title, grid);
    }

    public void update(TrafficSnapshot snapshot) {

        uploadCard.setValue(String.format("%.2f Mbps", snapshot.getUploadMbps()));
        downloadCard.setValue(String.format("%.2f Mbps", snapshot.getDownloadMbps()));
        hostsCard.setValue(String.valueOf(snapshot.getConnectedHosts()));
        portsCard.setValue(String.valueOf(snapshot.getActivePorts()));
    }
}