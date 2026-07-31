package net.edmooney.netwatch.platform;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;

import java.time.LocalDateTime;

public class WindowsTrafficProvider implements TrafficProvider {

    @Override
    public TrafficSnapshot collectSnapshot(NetworkAdapter adapter) {

        return new TrafficSnapshot(
                LocalDateTime.now(),
                0.0,
                0.0,
                0,
                0
        );
    }
}