package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class HostScannerService {

    public List<Host> scan(String subnet) {

        List<Host> hosts = new ArrayList<>();

        for (int i = 1; i <= 254; i++) {

            String ip = subnet + i;

            try {

                InetAddress address = InetAddress.getByName(ip);

                if (address.isReachable(100)) {

                    hosts.add(new Host(
                            ip,
                            address.getCanonicalHostName(),
                            true
                    ));
                }

            } catch (Exception ignored) {
            }
        }

        return hosts;
    }
}