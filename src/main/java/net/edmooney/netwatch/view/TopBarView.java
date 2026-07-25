package net.edmooney.netwatch.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class TopBarView extends HBox {

    public TopBarView() {

        setPrefHeight(70);
        getStyleClass().add("top-bar");

        Label title = new Label("NetWatch");
        title.getStyleClass().add("title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label interfaceLabel = new Label("No Interface");
        interfaceLabel.getStyleClass().add("interface-label");

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");

        getChildren().addAll(
                title,
                spacer,
                interfaceLabel,
                startButton
        );
    }
}