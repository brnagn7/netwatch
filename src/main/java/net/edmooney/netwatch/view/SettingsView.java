package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SettingsView extends BorderPane {

    public SettingsView() {

        setPadding(new Insets(20));

        Label title = new Label("Settings");
        title.getStyleClass().add("dashboard-title");

        setTop(title);
    }
}