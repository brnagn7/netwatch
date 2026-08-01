package net.edmooney.netwatch.controller;

import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.view.DashboardContent;
import net.edmooney.netwatch.view.HostsView;
import net.edmooney.netwatch.view.PortsView;
import net.edmooney.netwatch.view.SettingsView;

public class NavigationController {

    private final BorderPane root;
    private final NetWatchController controller;

    public NavigationController(BorderPane root,
                                NetWatchController controller) {
        this.root = root;
        this.controller = controller;
    }

    public void showDashboard() {
        root.setCenter(new DashboardContent());
    }

    public void showHosts() {
        root.setCenter(new HostsView(controller));
    }

    public void showPorts() {
        root.setCenter(new PortsView());
    }

    public void showSettings() {
        root.setCenter(new SettingsView());
    }
}