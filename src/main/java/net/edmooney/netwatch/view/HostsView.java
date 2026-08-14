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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.HostScanTask;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HostsView extends BorderPane {

    private final NetWatchController controller;

    private final TableView<Host> table = new TableView<>();

    private final ProgressIndicator spinner =
            new ProgressIndicator();

    private final Label scanningLabel =
            new Label("Scanning network...");

    private final Label title =
            new Label("Connected Hosts");

    private final Button scanButton =
            new Button("Scan Again");

    private final Label lastScanLabel =
            new Label("Last scan: Never");

    private final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    private Instant scanStartTime;

    private final VBox loadingBox =
            new VBox(
                    10,
                    spinner,
                    scanningLabel
            );

    public HostsView(NetWatchController controller) {

        this.controller = controller;

        setPadding(new Insets(20));

        title.getStyleClass().add("dashboard-title");
        lastScanLabel.getStyleClass().add("last-scan-label");

        scanButton.setOnAction(event ->
                scanHosts()
        );

        HBox header = new HBox(
                15,
                title,
                scanButton,
                lastScanLabel
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

        loadingBox.setAlignment(Pos.CENTER);

        loadingBox.setMouseTransparent(true);

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

        macColumn.setMinWidth(180);

        TableColumn<Host, String> vendorColumn =
                new TableColumn<>("Vendor");

        vendorColumn.setCellValueFactory(
                new PropertyValueFactory<>("vendor")
        );

        vendorColumn.setMinWidth(180);

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

        table.setOnMouseClicked(
                (MouseEvent event) -> {

                    if (event.getClickCount() == 2) {

                        Host selectedHost =
                                table.getSelectionModel()
                                        .getSelectedItem();

                        if (selectedHost != null) {

                            HostDetailsView details =
                                    new HostDetailsView(
                                            selectedHost
                                    );

                            Stage stage =
                                    new Stage();

                            stage.setTitle(
                                    "Host Details - " +
                                            selectedHost.getIpAddress()
                            );

                            stage.setScene(
                                    new Scene(
                                            details,
                                            500,
                                            350
                                    )
                            );

                            stage.show();
                        }
                    }
                }
        );
    }

    private void scanHosts() {

        NetworkAdapter adapter =
                controller.getSelectedMonitoringAdapter();

        if (adapter == null) {

            loadingBox.setVisible(false);

            scanningLabel.setText(
                    "No network adapter selected."
            );

            return;
        }

        table.getItems().clear();

        loadingBox.setVisible(true);

        scanningLabel.setText(
                "Scanning network..."
        );

        scanButton.setDisable(true);

        scanStartTime = Instant.now();

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

            long milliseconds =
                    Duration.between(
                            scanStartTime,
                            Instant.now()
                    ).toMillis();

            double seconds =
                    milliseconds / 1000.0;

            lastScanLabel.setText(
                    "Last scan: " +
                            LocalDateTime.now()
                                    .format(timeFormatter) +
                            "  |  Duration: " +
                            String.format(
                                    "%.1f",
                                    seconds
                            ) +
                            " sec"
            );

            loadingBox.setVisible(false);
            scanButton.setDisable(false);
        });

        task.setOnFailed(event -> {

            loadingBox.setVisible(false);
            scanButton.setDisable(false);

            scanningLabel.setText(
                    "Host scan failed."
            );

            if (task.getException() != null) {
                task.getException()
                        .printStackTrace();
            }
        });

        Thread thread =
                new Thread(task);

        thread.setDaemon(true);
        thread.start();
    }
}