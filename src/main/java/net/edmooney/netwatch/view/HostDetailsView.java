package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.model.Host;

public class HostDetailsView extends VBox {

    public HostDetailsView(Host host) {

        setPadding(new Insets(25));
        setSpacing(20);

        Label title = new Label("Host Details");
        title.getStyleClass().add("dashboard-title");

        Label subtitle = new Label(
                host.getIpAddress()
        );

        subtitle.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-text-fill: #888888;"
        );

        GridPane details = new GridPane();

        details.setHgap(25);
        details.setVgap(15);

        addRow(
                details,
                0,
                "IP Address",
                host.getIpAddress()
        );

        addRow(
                details,
                1,
                "Host Name",
                host.getHostName()
        );

        addRow(
                details,
                2,
                "MAC Address",
                host.getMacAddress()
        );

        addRow(
                details,
                3,
                "Vendor",
                host.getVendor()
        );

        addRow(
                details,
                4,
                "Device Type",
                host.getDeviceType()
        );

        addRow(
                details,
                5,
                "Status",
                host.isOnline()
                        ? "Online"
                        : "Offline"
        );

        getChildren().addAll(
                title,
                subtitle,
                details
        );

        setAlignment(Pos.TOP_LEFT);
    }

    private void addRow(
            GridPane grid,
            int row,
            String label,
            String value
    ) {

        Label nameLabel =
                new Label(label);

        nameLabel.setStyle(
                "-fx-font-weight: bold;"
        );

        Label valueLabel =
                new Label(
                        value == null || value.isBlank()
                                ? "Unknown"
                                : value
                );

        grid.add(
                nameLabel,
                0,
                row
        );

        grid.add(
                valueLabel,
                1,
                row
        );
    }
}