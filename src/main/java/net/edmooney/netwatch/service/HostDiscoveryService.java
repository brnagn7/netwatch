package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.util.ArrayList;
import java.util.List;

public class HostDiscoveryService {

    public List<Host> discoverHosts() {

        List<Host> hosts = new ArrayList<>();

        hosts.add(new Host(
                "192.168.1.1",
                "Router",
                true
        ));

        hosts.add(new Host(
                "192.168.1.10",
                "Desktop",
                true
        ));

        hosts.add(new Host(
                "192.168.1.25",
                "Laptop",
                false
        ));

        return hosts;
    }
}