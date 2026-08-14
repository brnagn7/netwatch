package net.edmooney.netwatch.service;

import java.util.Map;

public class MacVendorService {

    private static final Map<String, String> VENDORS = Map.of(
            "44-1C-12", "Unknown"
    );

    public String lookup(String macAddress) {

        if (macAddress == null || macAddress.isBlank()) {
            return "Unknown";
        }

        String normalized = macAddress
                .replace(":", "-")
                .toUpperCase();

        if (normalized.length() < 8) {
            return "Unknown";
        }

        String prefix = normalized.substring(0, 8);

        return VENDORS.getOrDefault(prefix, "Unknown");
    }

    public String format(String macAddress) {

        if (macAddress == null || macAddress.isBlank()) {
            return "";
        }

        String normalized = macAddress
                .replace("-", "")
                .replace(":", "")
                .replace(".", "")
                .toUpperCase();

        if (normalized.length() != 12) {
            return macAddress;
        }

        return normalized.substring(0, 2) + "-"
                + normalized.substring(2, 4) + "-"
                + normalized.substring(4, 6) + "-"
                + normalized.substring(6, 8) + "-"
                + normalized.substring(8, 10) + "-"
                + normalized.substring(10, 12);
    }
}