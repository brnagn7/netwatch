package net.edmooney.netwatch.controller;

import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.view.*;

public class NavigationController {

    private final BorderPane root;

    public NavigationController(BorderPane root) {
        this.root = root;
    }

    public void showDashboard() {
        root.setCenter(new DashboardContent());
    }

    public void showHosts() {
        root.setCenter(new HostsView());
    }

    public void showPorts() {
        root.setCenter(new PortsView());
    }

    public void showSettings() {
        root.setCenter(new SettingsView());
    }
}