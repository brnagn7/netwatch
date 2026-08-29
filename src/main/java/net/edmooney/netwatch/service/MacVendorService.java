package net.edmooney.netwatch.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MacVendorService {

    private final Map<String, String> vendors =
            new HashMap<>();

    public MacVendorService() {
        loadVendors();
    }

    private void loadVendors() {

        try {
            InputStream inputStream =
                    getClass().getResourceAsStream(
                            "/data/oui.csv"
                    );

            if (inputStream == null) {
                System.out.println(
                        "OUI database not found."
                );
                return;
            }

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    inputStream
                            )
                    );

            String line;
            boolean firstLine = true;

            while ((line = reader.readLine()) != null) {

                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts =
                        line.split(",", 2);

                if (parts.length < 2) {
                    continue;
                }

                String prefix =
                        parts[0].trim().toUpperCase();

                String vendor =
                        parts[1].trim();

                vendors.put(
                        prefix,
                        vendor
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String lookup(String macAddress) {

        if (macAddress == null ||
                macAddress.isBlank()) {
            return "Unknown";
        }

        String normalized =
                format(macAddress);

        if (normalized.length() < 8) {
            return "Unknown";
        }

        // Check whether this is a locally administered MAC address.
        // These are commonly used for Wi-Fi MAC randomization.
        try {
            int firstByte =
                    Integer.parseInt(
                            normalized.substring(0, 2),
                            16
                    );

            if ((firstByte & 0x02) != 0) {
                return "Private / Randomized";
            }

        } catch (NumberFormatException e) {
            return "Unknown";
        }

        String prefix =
                normalized.substring(0, 8);

        return vendors.getOrDefault(
                prefix,
                "Unknown"
        );
    }

    public String format(String macAddress) {

        if (macAddress == null ||
                macAddress.isBlank()) {
            return "";
        }

        String normalized =
                macAddress
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