package net.edmooney.netwatch.service;

/**
 * Monitors traffic on the selected network adapter.
 */
public class NetworkMonitorService {

    private boolean running;

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}