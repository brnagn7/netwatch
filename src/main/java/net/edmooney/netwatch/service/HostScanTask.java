package net.edmooney.netwatch.service;

import javafx.concurrent.Task;
import net.edmooney.netwatch.model.Host;
import net.edmooney.netwatch.model.NetworkAdapter;

import java.util.List;

public class HostScanTask extends Task<List<Host>> {

    private final HostScannerService scanner =
            new HostScannerService();

    private final NetworkAdapter adapter;

    public HostScanTask(NetworkAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected List<Host> call() {

        String ip = adapter.getIpv4Address();

        String subnet = ip.substring(
                0,
                ip.lastIndexOf('.') + 1
        );

        return scanner.scan(
                subnet,
                ip
        );
    }
}