package net.edmooney.netwatch.view.components;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardCard {

    public VBox createCard(String title, String value) {

        VBox card = new VBox(10);

        card.setPadding(new Insets(15));
        card.setPrefSize(220, 120);

        card.getStyleClass().add("dashboard-card");

        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("card-title");

        Label valueLabel = new Label(value);
        valueLabel.getStyleClass().add("card-value");

        card.getChildren().addAll(titleLabel, valueLabel);

        return card;
    }
}