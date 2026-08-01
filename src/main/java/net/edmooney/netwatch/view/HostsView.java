package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.service.HostDiscoveryService;

public class HostsView extends BorderPane {

    public HostsView() {

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        ListView<String> hostList = new ListView<>();

        HostDiscoveryService service = new HostDiscoveryService();

        for (Host host : service.discoverHosts()) {

            hostList.getItems().add(
                    host.getIpAddress()
                            + "    "
                            + host.getHostName()
                            + "    "
                            + (host.isOnline() ? "Online" : "Offline")
            );
        }

        setTop(title);
        setCenter(hostList);
    }
}