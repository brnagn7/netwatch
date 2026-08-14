package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ArpScannerService {

    private final MacVendorService vendorService =
            new MacVendorService();

    private final HostNameResolver hostNameResolver =
            new HostNameResolver();

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
            localHostName = InetAddress
                    .getLocalHost()
                    .getHostName();

        } catch (Exception e) {
            localHostName = "This PC";
        }

        String localMacAddress =
                getLocalMacAddress(localIp);

        hosts.add(new Host(
                localIp,
                localHostName,
                localMacAddress,
                "Local",
                "This PC",
                true
        ));

        try {

            Process process =
                    new ProcessBuilder("arp", "-a").start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line;
            boolean inCorrectInterface = false;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.startsWith("Interface:")) {

                    inCorrectInterface =
                            line.contains(localIp);

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

                if (parts.length < 3) {
                    continue;
                }

                String ip = parts[0];

                if (ip.endsWith(".255")) {
                    continue;
                }

                if (ip.startsWith("224.")
                        || ip.startsWith("239.")
                        || ip.equals("255.255.255.255")) {
                    continue;
                }

                String macAddress = vendorService.format(parts[1]);

                boolean online =
                        parts[2].equalsIgnoreCase("dynamic");

                String hostName =
                        hostNameResolver.resolve(ip);

                String vendor =
                        vendorService.lookup(macAddress);

                hosts.add(new Host(
                        ip,
                        hostName,
                        macAddress,
                        vendor,
                        determineDeviceType(
                                ip,
                                localIp
                        ),
                        online
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hosts;
    }

    private String getLocalMacAddress(String localIp) {

        try {
            InetAddress address =
                    InetAddress.getByName(localIp);

            java.net.NetworkInterface networkInterface =
                    java.net.NetworkInterface.getByInetAddress(address);

            if (networkInterface == null) {
                return "";
            }

            byte[] mac =
                    networkInterface.getHardwareAddress();

            if (mac == null) {
                return "";
            }

            StringBuilder result = new StringBuilder();

            for (int i = 0; i < mac.length; i++) {

                if (i > 0) {
                    result.append("-");
                }

                result.append(
                        String.format("%02X", mac[i])
                );
            }

            return result.toString();

        } catch (Exception e) {
            return "";
        }
    }
}