package net.edmooney.netwatch.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class NetWatchApplication extends Application {

    @Override
    public void start(Stage stage) {

        DashboardView dashboardView = new DashboardView();
        BorderPane root = dashboardView.createView();
        Scene scene = new Scene(root, 1400, 900);
        scene.getStylesheets().add(
                getClass().getResource("/css/netwatch.css").toExternalForm()
        );
        stage.setTitle("NetWatch");
        stage.setScene(scene);
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}