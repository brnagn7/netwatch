package net.edmooney.netwatch.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.util.Duration;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.NetworkAdapter;

public class TopBarView extends HBox {

    private final Timeline timeline;

    public TopBarView(NetWatchController controller,
                      DashboardContent dashboard,
                      StatusBarView statusBar) {

        setPrefHeight(70);
        getStyleClass().add("top-bar");

        Label title = new Label("NetWatch");
        title.getStyleClass().add("title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ComboBox<NetworkAdapter> adapterComboBox = new ComboBox<>();
        adapterComboBox.setPrefWidth(320);

        adapterComboBox.getItems().addAll(controller.getAvailableAdapters());


        if (!adapterComboBox.getItems().isEmpty()) {
            adapterComboBox.getSelectionModel().selectFirst();

            NetworkAdapter adapter = adapterComboBox.getValue();
            controller.setSelectedAdapter(adapter);
            statusBar.setAdapter(adapter.getDisplayName());
        }

        adapterComboBox.setOnAction(event -> {

            NetworkAdapter adapter = adapterComboBox.getValue();

            if (adapter != null) {
                controller.setSelectedAdapter(adapter);
                statusBar.setAdapter(adapter.getDisplayName());
            }
        });

        timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> dashboard.update(controller.collectSnapshot())
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");

        startButton.setOnAction(event -> {

            if (controller.isMonitoring()) {

                controller.stopMonitoring();
                timeline.stop();
                statusBar.monitoringStopped();
                startButton.setText("Start");

            } else {

                controller.startMonitoring();
                dashboard.update(controller.collectSnapshot());
                timeline.play();
                statusBar.monitoringStarted();
                startButton.setText("Stop");
            }
        });

        getChildren().addAll(
                title,
                spacer,
                adapterComboBox,
                startButton
        );
    }
}