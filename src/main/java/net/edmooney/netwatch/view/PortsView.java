package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class PortsView extends BorderPane {

    public PortsView() {

        setPadding(new Insets(20));

        Label title = new Label("Open Ports");
        title.getStyleClass().add("dashboard-title");

        setTop(title);
    }
}