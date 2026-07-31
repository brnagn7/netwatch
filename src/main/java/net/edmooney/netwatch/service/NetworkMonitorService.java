package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.TrafficSnapshot;
import java.time.LocalDateTime;
/**
 * Monitors traffic on the selected network adapter.
 */
public class NetworkMonitorService {

    private boolean monitoring;
    private double upload = 10.0;
    private double download = 80.0;

    public void start() {
        monitoring = true;
    }

    public void stop() {
        monitoring = false;
    }

    public boolean isMonitoring() {
        return monitoring;
    }

    public TrafficSnapshot collectSnapshot() {

        upload += 0.5;
        download += 1.0;

        return new TrafficSnapshot(
                LocalDateTime.now(),
                upload,
                download,
                4,
                18
        );
    }
}