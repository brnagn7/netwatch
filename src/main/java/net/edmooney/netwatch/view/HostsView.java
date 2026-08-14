package net.edmooney.netwatch.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.HostScanTask;

public class HostsView extends BorderPane {

    public HostsView(NetWatchController controller) {

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        TableView<Host> table = new TableView<>();
        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
        );

        table.setPlaceholder(
                new Label("No hosts discovered.")
        );

        TableColumn<Host, String> ipColumn =
                new TableColumn<>("IP Address");

        ipColumn.setCellValueFactory(
                new PropertyValueFactory<>("ipAddress")
        );

        ipColumn.setMinWidth(180);

        TableColumn<Host, String> hostColumn =
                new TableColumn<>("Host Name");

        hostColumn.setCellValueFactory(
                new PropertyValueFactory<>("hostName")
        );

        hostColumn.setMinWidth(220);

        TableColumn<Host, String> macColumn =
                new TableColumn<>("MAC Address");

        macColumn.setCellValueFactory(
                new PropertyValueFactory<>("macAddress")
        );

        macColumn.setMinWidth(180);

        TableColumn<Host, String> statusColumn =
                new TableColumn<>("Status");

        statusColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().isOnline()
                                ? "Online"
                                : "Offline"
                )
        );

        statusColumn.setMinWidth(120);

        table.getColumns().addAll(
                ipColumn,
                hostColumn,
                macColumn,
                statusColumn
        );

        table.getSortOrder().add(ipColumn);

        ProgressIndicator spinner =
                new ProgressIndicator();

        spinner.setPrefSize(45, 45);

        Label scanningLabel =
                new Label("Scanning network...");

        scanningLabel.getStyleClass().add(
                "scanning-label"
        );

        javafx.scene.layout.VBox loadingBox =
                new javafx.scene.layout.VBox(
                        10,
                        spinner,
                        scanningLabel
                );

        loadingBox.setAlignment(Pos.CENTER);

        StackPane content = new StackPane(
                table,
                loadingBox
        );

        setTop(title);
        setCenter(content);

        NetworkAdapter adapter =
                controller.getSelectedMonitoringAdapter();

        if (adapter == null) {

            loadingBox.setVisible(false);

            table.setPlaceholder(
                    new Label("No network adapter selected.")
            );

            return;
        }

        HostScanTask task =
                new HostScanTask(adapter);

        task.setOnSucceeded(event -> {

            table.getItems().setAll(
                    task.getValue()
            );

            title.setText(
                    "Connected Hosts (" +
                            task.getValue().size() +
                            ")"
            );

            table.sort();

            loadingBox.setVisible(false);
        });

        task.setOnFailed(event -> {

            loadingBox.setVisible(false);

            table.setPlaceholder(
                    new Label("Host scan failed.")
            );

            task.getException().printStackTrace();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}