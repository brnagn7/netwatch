package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;
import java.util.List;

public class HostDiscoveryService {

    private final HostScannerService scannerService =
            new HostScannerService();

    public List<Host> discoverHosts() {

        return scannerService.scan("192.168.1.");
    }
}