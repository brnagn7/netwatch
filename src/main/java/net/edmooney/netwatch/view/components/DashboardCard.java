package net.edmooney.netwatch.view.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardCard extends VBox {

    private final String title;
    private final String value;

    public DashboardCard(String title, String value) {

        this.title = title;
        this.value = value;

        setSpacing(10);
        setPadding(new Insets(15));
        setPrefSize(220, 120);

        getStyleClass().add("dashboard-card");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("card-title");

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("card-value");

        getChildren().addAll(titleLabel, valueLabel);
    }
}