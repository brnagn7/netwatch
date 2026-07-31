package net.edmooney.netwatch.platform;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDateTime;

public class LinuxTrafficProvider implements TrafficProvider {
    private static final String NETWORK_STATS = "/proc/net/dev";

    private void readNetworkStats() {

        try {

            Files.lines(Path.of(NETWORK_STATS))
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public TrafficSnapshot collectSnapshot(NetworkAdapter adapter) {
        readNetworkStats();
        return new TrafficSnapshot(
                LocalDateTime.now(),
                0.0,
                0.0,
                0,
                0
        );
    }
}