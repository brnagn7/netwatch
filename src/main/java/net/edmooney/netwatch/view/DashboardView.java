package net.edmooney.netwatch.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class DashboardView {

    public BorderPane createView() {

        BorderPane root = new BorderPane();

        root.setTop(createTopBar());
        root.setLeft(createSidebar());
        root.setCenter(createDashboard());
        root.setBottom(createStatusBar());

        return root;
    }

    private HBox createTopBar() {

        HBox topBar = new HBox();
        topBar.setPrefHeight(70);
        topBar.getStyleClass().add("top-bar");

        Label title = new Label("NetWatch");
        title.getStyleClass().add("title");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Label interfaceLabel = new Label("No Interface");
        interfaceLabel.getStyleClass().add("interface-lable");

        Button startButton = new Button("Start");
        startButton.getStyleClass().add("start-button");

        topBar.getChildren().addAll(
                title,
                spacer,
                interfaceLabel,
                startButton
        );

        return topBar;
    }

    private VBox createSidebar() {

        VBox sidebar = new VBox();
        sidebar.setPrefWidth(250);
        sidebar.getStyleClass().add("side-bar");

        return sidebar;
    }

    private VBox createDashboard() {

        VBox dashboard = new VBox();
        dashboard.getStyleClass().add("content-area");

        return dashboard;
    }

    private HBox createStatusBar() {

        HBox statusBar = new HBox();
        statusBar.setPrefHeight(30);
        statusBar.getStyleClass().add("status-bar");

        return statusBar;
    }
}