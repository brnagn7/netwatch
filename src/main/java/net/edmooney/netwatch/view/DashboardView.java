package net.edmooney.netwatch.view;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

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

    private HBox createStatusBar() {

        HBox statusBar = new HBox();
        statusBar.setPrefHeight(30);
        statusBar.getStyleClass().add("status-bar");

        return statusBar;
    }
}