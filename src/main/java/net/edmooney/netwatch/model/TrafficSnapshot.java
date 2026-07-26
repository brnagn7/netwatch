package net.edmooney.netwatch.model;

import java.time.LocalDateTime;

public class TrafficSnapshot {

    private final LocalDateTime timestamp;

    private final double uploadMbps;
    private final double downloadMbps;

    private final int connectedHosts;
    private final int activePorts;

    public TrafficSnapshot(
            LocalDateTime timestamp,
            double uploadMbps,
            double downloadMbps,
            int connectedHosts,
            int activePorts
    ) {
        this.timestamp = timestamp;
        this.uploadMbps = uploadMbps;
        this.downloadMbps = downloadMbps;
        this.connectedHosts = connectedHosts;
        this.activePorts = activePorts;
    }

    @Override
    public String toString() {
        return "TrafficSnapshot{" +
                "timestamp=" + timestamp +
                ", uploadMbps=" + uploadMbps +
                ", downloadMbps=" + downloadMbps +
                ", connectedHosts=" + connectedHosts +
                ", activePorts=" + activePorts +
                '}';
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getUploadMbps() {
        return uploadMbps;
    }

    public double getDownloadMbps() {
        return downloadMbps;
    }

    public int getConnectedHosts() {
        return connectedHosts;
    }

    public int getActivePorts() {
        return activePorts;
    }
}