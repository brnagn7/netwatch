package net.edmooney.netwatch.provider;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;

import java.time.LocalDateTime;

public class DefaultTrafficProvider implements TrafficProvider {

    private double upload = 10.0;
    private double download = 80.0;

    @Override
    public TrafficSnapshot collectSnapshot(NetworkAdapter adapter) {

        upload += 0.5;
        download += 1.0;

        return new TrafficSnapshot(
                LocalDateTime.now(),
                upload,
                download,
                4,
                18
        );
    }
}