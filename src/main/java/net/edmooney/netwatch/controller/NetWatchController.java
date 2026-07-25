package net.edmooney.netwatch.controller;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.service.NetworkDiscoveryService;

import java.util.List;

/**
 * Coordinates communication between the UI and the services.
 */
public class NetWatchController {

    private final NetworkDiscoveryService discoveryService;

    public NetWatchController() {
        this.discoveryService = new NetworkDiscoveryService();
    }

    public List<NetworkAdapter> getAvailableAdapters() {
        return discoveryService.findAdapters();
    }
}