package net.edmooney.netwatch.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {

    public BorderPane createView() {

        BorderPane root = new BorderPane();

        TopBarView topBar = new TopBarView();

        root.setTop(topBar.createView());
        root.setLeft(createSidebar());
        root.setCenter(createDashboard());
        root.setBottom(createStatusBar());

        return root;
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