package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.model.Host;

public class HostDetailsView extends VBox {

    public HostDetailsView(Host host) {

        setPadding(new Insets(20));
        setSpacing(15);

        Label title = new Label("Host Details");
        title.getStyleClass().add("dashboard-title");

        GridPane details = new GridPane();

        details.setHgap(20);
        details.setVgap(12);

        addRow(details, 0, "IP Address", host.getIpAddress());
        addRow(details, 1, "Host Name", host.getHostName());
        addRow(details, 2, "MAC Address", host.getMacAddress());
        addRow(details, 3, "Vendor", host.getVendor());
        addRow(details, 4, "Device Type", host.getDeviceType());
        addRow(
                details,
                5,
                "Status",
                host.isOnline() ? "Online" : "Offline"
        );

        getChildren().addAll(
                title,
                details
        );
    }

    private void addRow(
            GridPane grid,
            int row,
            String label,
            String value
    ) {

        Label nameLabel = new Label(label + ":");
        nameLabel.getStyleClass().add("detail-label");

        Label valueLabel = new Label(
                value == null || value.isBlank()
                        ? "Unknown"
                        : value
        );

        grid.add(nameLabel, 0, row);
        grid.add(valueLabel, 1, row);
    }
}