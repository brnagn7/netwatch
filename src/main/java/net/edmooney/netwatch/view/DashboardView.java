package net.edmooney.netwatch.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DashboardView {

    public BorderPane createView() {

        BorderPane root = new BorderPane();

        TopBarView topBar = new TopBarView();
        SidebarView sidebarView = new SidebarView();
        DashboardPane dashboardPane = new DashboardPane();

        root.setTop(topBar.createView());
        root.setLeft(sidebarView.createView());
        root.setCenter(dashboardPane.createView());
        root.setBottom(createStatusBar());

        return root;
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