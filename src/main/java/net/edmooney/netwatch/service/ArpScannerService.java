package net.edmooney.netwatch.service;

import net.edmooney.netwatch.model.Host;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ArpScannerService {

    public List<Host> scan(String interfaceIp) {

        List<Host> hosts = new ArrayList<>();

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

                    inCorrectInterface = line.contains(interfaceIp);
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

                    hosts.add(new Host(
                            ip,
                            "",
                            parts[1],
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