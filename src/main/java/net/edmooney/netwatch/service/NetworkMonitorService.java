package net.edmooney.netwatch.service;

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
}