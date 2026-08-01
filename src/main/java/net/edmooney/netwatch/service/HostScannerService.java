package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.util.ArrayList;
import java.util.List;

public class HostScannerService {

    public List<Host> scan(String subnet) {

        System.out.println("HostScannerService.scan()");

        return new ArpScannerService().scan(subnet + "175");
    }
}