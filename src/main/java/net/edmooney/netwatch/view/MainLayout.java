package net.edmooney.netwatch.view;

import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.controller.NetWatchController;

public class MainLayout {

    private final NetWatchController controller;

    public MainLayout(NetWatchController controller) {
        this.controller = controller;
    }

    public BorderPane createView() {

        BorderPane root = new BorderPane();

        root.setTop(new TopBarView(controller));
        root.setLeft(new SidebarView());
        root.setCenter(new DashboardContent());

        return root;
    }
}