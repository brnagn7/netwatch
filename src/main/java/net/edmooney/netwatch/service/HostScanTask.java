package net.edmooney.netwatch.service;

import javafx.concurrent.Task;
import net.edmooney.netwatch.model.Host;

import java.util.List;

public class HostScanTask extends Task<List<Host>> {

    private final HostScannerService scanner =
            new HostScannerService();

    private final String subnet;

    public HostScanTask(String subnet) {
        this.subnet = subnet;
    }

    @Override
    protected List<Host> call() {

        return scanner.scan(subnet);
    }
}