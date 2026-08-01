package net.edmooney.netwatch.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class PlaceholderView extends StackPane {

    public PlaceholderView(String title) {

        setAlignment(Pos.CENTER);

        Label label = new Label(title);
        label.getStyleClass().add("dashboard-title");

        getChildren().add(label);
    }
}