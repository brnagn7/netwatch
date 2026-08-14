package net.edmooney.netwatch.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MacVendorService {

    private final Map<String, String> vendors = new HashMap<>();

    public MacVendorService() {
        loadVendors();
    }

    private void loadVendors() {

        InputStream inputStream =
                getClass().getResourceAsStream("/data/oui.csv");

        if (inputStream == null) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        inputStream,
                        StandardCharsets.UTF_8))) {

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",", 2);

                if (parts.length == 2) {

                    vendors.put(
                            parts[0].trim().toUpperCase(),
                            parts[1].trim()
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        System.out.println(
                "MAC: " + macAddress +
                        " -> Vendor: " +
                        vendors.getOrDefault(prefix, "Unknown")
        );

        return vendors.getOrDefault(prefix, "Unknown");
    }
}