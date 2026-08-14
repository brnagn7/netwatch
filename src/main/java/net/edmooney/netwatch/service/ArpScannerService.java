package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArpScannerService {

    private final MacVendorService vendorService =
            new MacVendorService();

    private String determineDeviceType(
            String ip,
            String localIp
    ) {

        if (ip.equals(localIp)) {
            return "This PC";
        }

        if (ip.endsWith(".1")) {
            return "Router / Gateway";
        }

        return "Unknown";
    }

    public List<Host> scan(String localIp) {

        List<Host> hosts = new ArrayList<>();
        String localHostName;

        try {
            localHostName = java.net.InetAddress
                    .getLocalHost()
                    .getHostName();
        } catch (Exception e) {
            localHostName = "This PC";
        }

        hosts.add(new Host(
                localIp,
                localHostName,
                "",
                "Local",
                "This PC",
                true
        ));

        try {

            Process process = new ProcessBuilder("arp", "-a").start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            boolean inCorrectInterface = false;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("Interface:")) {

                    inCorrectInterface = line.contains(localIp);
                    continue;
                }

                if (!inCorrectInterface) {
                    continue;
                }

                if (line.isBlank()
                        || line.startsWith("Internet Address")) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts.length >= 3) {

                    String ip = parts[0];

                    if (ip.endsWith(".255")) {
                        continue;
                    }

                    if (ip.startsWith("224.")
                            || ip.startsWith("239.")
                            || ip.equals("255.255.255.255")) {
                        continue;
                    }

                    String hostName;

                    try {
                        hostName = java.net.InetAddress
                                .getByName(ip)
                                .getCanonicalHostName();

                    } catch (Exception e) {
                        hostName = "";
                    }

                    String macAddress = parts[1];

                    String vendor = vendorService.lookup(macAddress);

                    hosts.add(new Host(
                            ip,
                            hostName,
                            macAddress,
                            vendor,
                            determineDeviceType(ip, localIp),
                            true
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hosts;
    }
}