package net.edmooney.netwatch.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.HostScanTask;


public class HostsView extends BorderPane {

    private final NetWatchController controller;
    private final TableView<Host> table = new TableView<>();
    private final ProgressIndicator spinner = new ProgressIndicator();
    private final Label scanningLabel = new Label("Scanning network...");
    private final Label title = new Label("Connected Hosts");
    private final Button scanButton = new Button("Scan Again");

    public HostsView(NetWatchController controller) {

        this.controller = controller;

        setPadding(new Insets(20));

        title.getStyleClass().add("dashboard-title");

        scanButton.setOnAction(event -> scanHosts());

        HBox header = new HBox(
                15,
                title,
                scanButton
        );

        header.setAlignment(Pos.CENTER_LEFT);

        table.setColumnResizePolicy(
                TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS
        );

        table.setPlaceholder(
                new Label("No hosts discovered.")
        );

        createColumns();

        spinner.setPrefSize(45, 45);

        scanningLabel.getStyleClass().add(
                "scanning-label"
        );

        VBox loadingBox = new VBox(
                10,
                spinner,
                scanningLabel
        );

        loadingBox.setAlignment(Pos.CENTER);

        StackPane content = new StackPane(
                table,
                loadingBox
        );

        setTop(header);
        setCenter(content);

        scanHosts();
    }

    private void createColumns() {

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

        TableColumn<Host, String> vendorColumn =
                new TableColumn<>("Vendor");

        vendorColumn.setCellValueFactory(
                new PropertyValueFactory<>("vendor")
        );

        vendorColumn.setMinWidth(180);

        macColumn.setMinWidth(180);

        TableColumn<Host, String> deviceTypeColumn =
                new TableColumn<>("Device Type");

        deviceTypeColumn.setCellValueFactory(
                new PropertyValueFactory<>("deviceType")
        );

        deviceTypeColumn.setMinWidth(140);

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
                vendorColumn,
                deviceTypeColumn,
                statusColumn
        );

        table.getSortOrder().add(ipColumn);
    }

    private void scanHosts() {

        NetworkAdapter adapter =
                controller.getSelectedMonitoringAdapter();

        if (adapter == null) {

            spinner.setVisible(false);
            scanningLabel.setText(
                    "No network adapter selected."
            );

            return;
        }

        table.getItems().clear();

        spinner.setVisible(true);
        scanningLabel.setText("Scanning network...");
        scanButton.setDisable(true);

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

            spinner.setVisible(false);
            scanButton.setDisable(false);
        });

        task.setOnFailed(event -> {

            spinner.setVisible(false);
            scanButton.setDisable(false);

            scanningLabel.setText(
                    "Host scan failed."
            );

            task.getException().printStackTrace();
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
}