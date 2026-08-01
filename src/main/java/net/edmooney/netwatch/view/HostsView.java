package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.service.HostDiscoveryService;

public class HostsView extends BorderPane {

    public HostsView() {

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        TableView<Host> table = new TableView<>();

        TableColumn<Host, String> ipColumn = new TableColumn<>("IP Address");
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ipAddress"));
        ipColumn.setPrefWidth(180);

        TableColumn<Host, String> hostColumn = new TableColumn<>("Host Name");
        hostColumn.setCellValueFactory(new PropertyValueFactory<>("hostName"));
        hostColumn.setPrefWidth(250);

        TableColumn<Host, Boolean> statusColumn = new TableColumn<>("Online");
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("online"));
        statusColumn.setPrefWidth(100);

        table.getColumns().addAll(
                ipColumn,
                hostColumn,
                statusColumn
        );

        HostDiscoveryService service = new HostDiscoveryService();

        table.getItems().addAll(service.discoverHosts());

        setTop(title);
        setCenter(table);
    }
}