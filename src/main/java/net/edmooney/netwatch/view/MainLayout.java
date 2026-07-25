package net.edmooney.netwatch.view;

import javafx.scene.layout.BorderPane;

public class MainLayout {

    public BorderPane createView() {

        BorderPane root = new BorderPane();

        root.setTop(new TopBarView());
        root.setLeft(new SidebarView());
        root.setCenter(new DashboardContent());

        return root;
    }
}