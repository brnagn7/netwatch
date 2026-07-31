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

        List<NetworkIF> interfaces = hardware.getNetworkIFs();

        for (NetworkIF networkIF : interfaces) {

            networkIF.updateAttributes();

            if (networkIF.getDisplayName().equals(adapter.getDisplayName())) {
                selectedInterface = networkIF;
                break;
            }
        }

        if (selectedInterface != null) {

            System.out.println("-------------------------");
            System.out.println("Monitoring: " + selectedInterface.getDisplayName());
            System.out.println("Bytes Received: " + selectedInterface.getBytesRecv());
            System.out.println("Bytes Sent: " + selectedInterface.getBytesSent());

        } else {

            System.out.println("Adapter not found: " + adapter.getDisplayName());
        }

        return new TrafficSnapshot(
                LocalDateTime.now(),
                0.0,
                0.0,
                0,
                0
        );
    }
}