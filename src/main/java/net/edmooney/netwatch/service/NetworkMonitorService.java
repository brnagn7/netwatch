package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.TrafficSnapshot;
import java.time.LocalDateTime;
/**
 * Monitors traffic on the selected network adapter.
 */
public class NetworkMonitorService {

    private boolean monitoring;

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

        return new TrafficSnapshot(
                LocalDateTime.now(),
                12.5,
                87.3,
                4,
                18
        );
    }
}