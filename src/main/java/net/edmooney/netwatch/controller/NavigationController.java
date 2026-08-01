package net.edmooney.netwatch.controller;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import net.edmooney.netwatch.view.DashboardContent;
import net.edmooney.netwatch.view.PlaceholderView;

public class NavigationController {

    private final BorderPane root;

    public NavigationController(BorderPane root) {
        this.root = root;
    }

    public void showDashboard() {
        root.setCenter(new DashboardContent());
    }

    public void showHosts() {
        root.setCenter(new PlaceholderView("Hosts"));
    }

    public void showPorts() {
        root.setCenter(new PlaceholderView("Ports"));
    }

    public void showSettings() {
        root.setCenter(new PlaceholderView("Settings"));
    }
}