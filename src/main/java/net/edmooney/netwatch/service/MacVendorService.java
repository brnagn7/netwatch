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
}