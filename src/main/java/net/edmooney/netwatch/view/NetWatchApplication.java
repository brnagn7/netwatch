package net.edmooney.netwatch.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.edmooney.netwatch.controller.NetWatchController;
import net.edmooney.netwatch.platform.TrafficProviderFactory;

public class NetWatchApplication extends Application {

    @Override
    public void start(Stage stage) {

        NetWatchController controller = new NetWatchController();

        System.out.println(
                "Platform: " +
                        TrafficProviderFactory.getCurrentPlatform()
        );

        MainLayout mainLayout = new MainLayout(controller);

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