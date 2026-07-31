package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.TrafficSnapshot;
import java.time.LocalDateTime;
import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.provider.DefaultTrafficProvider;
import net.edmooney.netwatch.provider.TrafficProvider;
import net.edmooney.netwatch.provider.TrafficProviderFactory;

import java.time.LocalDateTime;
/**
 * Monitors traffic on the selected network adapter.
 */
public class NetworkMonitorService {

    private NetworkAdapter adapter;
    private final TrafficProvider trafficProvider;
    private boolean monitoring;

    public NetworkMonitorService() {
        this.trafficProvider = TrafficProviderFactory.create();
    }

    public void start(NetworkAdapter adapter) {
        this.adapter = adapter;
        monitoring = true;
    }

    public NetworkAdapter getAdapter() {
        return adapter;
    }

    public String getAdapterName() {

        if (adapter == null) {
            return "";
        }

        return adapter.getName();
    }

    public void stop() {
        monitoring = false;
    }

    public boolean isMonitoring() {
        return monitoring;
    }

    public TrafficSnapshot collectSnapshot() {

        if (adapter == null) {
            return new TrafficSnapshot(
                    LocalDateTime.now(),
                    0.0,
                    0.0,
                    0,
                    0
            );
        }

        return trafficProvider.collectSnapshot(adapter);
    }
}