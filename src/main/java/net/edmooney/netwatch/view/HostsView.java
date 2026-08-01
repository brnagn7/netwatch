package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class HostsView extends BorderPane {

    public HostsView() {

        setPadding(new Insets(20));

        Label title = new Label("Connected Hosts");
        title.getStyleClass().add("dashboard-title");

        setTop(title);
    }
}