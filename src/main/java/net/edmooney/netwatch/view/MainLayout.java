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

        DashboardContent dashboard = new DashboardContent();
        dashboard.update(controller.collectSnapshot());

        root.setTop(new TopBarView(controller, dashboard));
        root.setLeft(new SidebarView());
        root.setCenter(dashboard);

        return root;
    }
}