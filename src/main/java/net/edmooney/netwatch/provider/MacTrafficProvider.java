package net.edmooney.netwatch.provider;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;

import java.time.LocalDateTime;

public class MacTrafficProvider implements TrafficProvider {

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