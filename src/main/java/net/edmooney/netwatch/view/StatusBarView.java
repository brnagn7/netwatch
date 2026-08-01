package net.edmooney.netwatch.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class StatusBarView extends HBox {

    private final Label statusLabel = new Label("Monitoring stopped");
    private final Label adapterLabel = new Label("No adapter selected");

    public StatusBarView() {

        setPadding(new Insets(5, 10, 5, 10));
        setSpacing(10);
        getStyleClass().add("status-bar");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        getChildren().addAll(
                statusLabel,
                spacer,
                adapterLabel
        );
    }

    public void monitoringStarted() {
        statusLabel.setText("Monitoring...");
    }

    public void monitoringStopped() {
        statusLabel.setText("Monitoring stopped");
    }

    public void setAdapter(String adapter) {
        adapterLabel.setText(adapter);
    }
}