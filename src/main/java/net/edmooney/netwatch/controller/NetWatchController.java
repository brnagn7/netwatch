package net.edmooney.netwatch.controller;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;
import net.edmooney.netwatch.service.NetworkDiscoveryService;
import net.edmooney.netwatch.service.NetworkMonitorService;

import java.util.List;

/**
 * Coordinates communication between the UI and the services.
 */
public class NetWatchController {

    private final NetworkDiscoveryService discoveryService;
    private final NetworkMonitorService monitorService;

    public NetWatchController() {
        this.discoveryService = new NetworkDiscoveryService();
        this.monitorService = new NetworkMonitorService();
    }

    public List<NetworkAdapter> getAvailableAdapters() {
        return discoveryService.findAdapters();
    }

    public void startMonitoring() {
        monitorService.start();
    }

    public void stopMonitoring() {
        monitorService.stop();
    }

    public boolean isMonitoring() {
        return monitorService.isMonitoring();
    }

    public TrafficSnapshot collectSnapshot() {
        return monitorService.collectSnapshot();
    }

}