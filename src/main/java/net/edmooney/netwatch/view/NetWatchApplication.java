package net.edmooney.netwatch.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.NetworkDiscoveryService;

public class NetWatchApplication extends Application {

    @Override
    public void start(Stage stage) {

        MainLayout mainLayout = new MainLayout();

        NetWatchController controller = new NetWatchController();

        for (NetworkAdapter adapter : controller.getAvailableAdapters()) {

            System.out.println("-------------------------");
            System.out.println("Name: " + adapter.getName());
            System.out.println("Display: " + adapter.getDisplayName());
            System.out.println("Up: " + adapter.isUp());
            System.out.println("Loopback: " + adapter.isLoopback());
        }

        Scene scene = new Scene(mainLayout.createView(), 1400, 900);

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