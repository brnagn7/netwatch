package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.service.HostDiscoveryService;
import javafx.beans.property.SimpleStringProperty;

public class HostsView extends BorderPane {

    public HostsView() {

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        TableView<Host> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        table.setPlaceholder(new Label("No hosts discovered."));

        TableColumn<Host, String> ipColumn = new TableColumn<>("IP Address");
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        ipColumn.setMinWidth(180);

        TableColumn<Host, String> hostColumn = new TableColumn<>("Host Name");
        hostColumn.setCellValueFactory(new PropertyValueFactory<>("hostName"));
        hostColumn.setMinWidth(250);

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
                statusColumn
        );

        ipColumn.setSortable(true);
        hostColumn.setSortable(true);
        statusColumn.setSortable(true);
        table.getSortOrder().add(ipColumn);

        HostDiscoveryService service = new HostDiscoveryService();

        table.getItems().addAll(service.discoverHosts());
        table.sort();

        setTop(title);
        setCenter(table);
    }
}