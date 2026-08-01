package net.edmooney.netwatch.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.HostScanTask;

public class HostsView extends BorderPane {

    public HostsView(NetWatchController controller) {

        System.out.println("HostsView created");

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        TableView<Host> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setPlaceholder(new Label("Scanning network..."));

        TableColumn<Host, String> ipColumn = new TableColumn<>("IP Address");
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        ipColumn.setMinWidth(180);

        TableColumn<Host, String> hostColumn =
                new TableColumn<>("Host Name");
        hostColumn.setCellValueFactory(
                new PropertyValueFactory<>("hostName"));
        hostColumn.setMinWidth(220);

        TableColumn<Host, String> macColumn =
                new TableColumn<>("MAC Address");
        macColumn.setCellValueFactory(
                new PropertyValueFactory<>("macAddress"));
        macColumn.setMinWidth(180);

        TableColumn<Host, String> statusColumn = new TableColumn<>("Status");
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

        ipColumn.setSortable(true);
        hostColumn.setSortable(true);
        statusColumn.setSortable(true);

        table.getSortOrder().add(ipColumn);

        NetworkAdapter adapter = controller.getSelectedMonitoringAdapter();

        if (adapter != null) {

            System.out.println("Scanning subnet from: " + adapter.getIpv4Address());

            HostScanTask task = new HostScanTask(adapter);

            task.setOnRunning(event ->
                    System.out.println("Task running"));

            task.setOnSucceeded(event -> {
                System.out.println("Task succeeded");
                table.getItems().setAll(task.getValue());
                title.setText("Connected Hosts (" + task.getValue().size() + ")");
                table.sort();
            });

            task.setOnFailed(event -> {
                System.out.println("Task failed");

                if (task.getException() != null) {
                    task.getException().printStackTrace();
                }
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();

        } else {

            System.out.println("No adapter selected.");
            table.setPlaceholder(new Label("No network adapter selected."));
        }

        setTop(title);
        setCenter(table);
    }
}