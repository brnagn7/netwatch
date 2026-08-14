package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;
import java.util.List;

public class HostDiscoveryService {

    private final HostScannerService scannerService =
            new HostScannerService();
}