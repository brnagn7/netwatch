package net.edmooney.netwatch.view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.NetworkAdapter;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TopBarView extends HBox {

    public TopBarView(NetWatchController controller,
                      DashboardContent dashboard) {

        setPrefHeight(70);
        getStyleClass().add("top-bar");

        Label title = new Label("NetWatch");
        title.getStyleClass().add("title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        ComboBox<NetworkAdapter> adapterComboBox = new ComboBox<>();
        adapterComboBox.setPrefWidth(320);

        adapterComboBox.getItems().addAll(
                controller.getAvailableAdapters()
        );

        // Select the first adapter if one exists
        if (!adapterComboBox.getItems().isEmpty()) {
            adapterComboBox.getSelectionModel().selectFirst();
        }

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");

        startButton.setOnAction(event -> {

            if (controller.isMonitoring()) {

                controller.stopMonitoring();

                startButton.setText("Start");

            } else {

                controller.startMonitoring();
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.seconds(1), e ->
                                dashboard.update(controller.collectSnapshot()))
                );

                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();

                dashboard.update(controller.collectSnapshot());
                startButton.setText("Stop");
            }
            System.out.println(controller.collectSnapshot());
        });

        getChildren().addAll(
                title,
                spacer,
                adapterComboBox,
                startButton
        );
    }
}