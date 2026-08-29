package net.edmooney.netwatch.service;

public class DeviceIdentificationService {

    public String identify(
            String ip,
            String localIp,
            String hostName,
            String vendor
    ) {

        if (ip == null || ip.isBlank()) {
            return "Unknown";
        }

        if (ip.equals(localIp)) {
            return "This PC";
        }

        if (ip.endsWith(".1")) {
            return "Router / Gateway";
        }

        if (hostName != null && !hostName.isBlank()) {
            return "Computer";
        }

        return "Unknown";
    }
}