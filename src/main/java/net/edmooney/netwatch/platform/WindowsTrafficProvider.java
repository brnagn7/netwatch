package net.edmooney.netwatch.platform;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.time.LocalDateTime;
import java.util.List;

public class WindowsTrafficProvider implements TrafficProvider {

    private final SystemInfo systemInfo = new SystemInfo();
    private final HardwareAbstractionLayer hardware = systemInfo.getHardware();
    private long previousBytesReceived = -1;
    private long previousBytesSent = -1;

    @Override
    public TrafficSnapshot collectSnapshot(NetworkAdapter adapter) {

        NetworkIF selectedInterface = null;

        for (NetworkIF networkIF : hardware.getNetworkIFs()) {

            networkIF.updateAttributes();

            if (networkIF.getDisplayName().equals(adapter.getDisplayName())) {
                selectedInterface = networkIF;
                break;
            }
        }

        if (selectedInterface == null) {

            return new TrafficSnapshot(
                    LocalDateTime.now(),
                    0.0,
                    0.0,
                    0,
                    0
            );
        }

        long currentBytesReceived = selectedInterface.getBytesRecv();
        long currentBytesSent = selectedInterface.getBytesSent();

        double downloadMbps = 0.0;
        double uploadMbps = 0.0;

        if (previousBytesReceived >= 0) {

            downloadMbps =
                    (currentBytesReceived - previousBytesReceived) * 8.0
                            / 1_000_000.0;
        }

        if (previousBytesSent >= 0) {

            uploadMbps =
                    (currentBytesSent - previousBytesSent) * 8.0
                            / 1_000_000.0;
        }

        previousBytesReceived = currentBytesReceived;
        previousBytesSent = currentBytesSent;

        int connectedHosts = 0;
        int activePorts = 0;

        return new TrafficSnapshot(
                LocalDateTime.now(),
                Math.round(uploadMbps * 100.0) / 100.0,
                Math.round(downloadMbps * 100.0) / 100.0,
                connectedHosts,
                activePorts
        );
    }
}