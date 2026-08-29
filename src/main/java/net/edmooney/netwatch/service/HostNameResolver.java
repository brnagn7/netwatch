package net.edmooney.netwatch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HostNameResolver {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(10);

    public String resolve(String ip) {

        String hostName = resolveWithJava(ip);

        if (!hostName.isBlank()) {
            return hostName;
        }

        return resolveWithWindowsPing(ip);
    }

    private String resolveWithJava(String ip) {

        try {
            Future<String> future = executor.submit(() ->
                    InetAddress
                            .getByName(ip)
                            .getCanonicalHostName()
            );

            String hostName =
                    future.get(1, TimeUnit.SECONDS);

            if (hostName.equals(ip)) {
                return "";
            }

            return hostName;

        } catch (Exception ignored) {
            return "";
        }
    }

    private String resolveWithWindowsPing(String ip) {

        try {
            Process process = new ProcessBuilder(
                    "ping",
                    "-a",
                    "-n",
                    "1",
                    "-w",
                    "500",
                    ip
            ).start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                line = line.trim();

                if (!line.startsWith("Pinging ")) {
                    continue;
                }

                int start = "Pinging ".length();
                int bracket = line.indexOf(" [");

                if (bracket <= start) {
                    continue;
                }

                String hostName =
                        line.substring(start, bracket).trim();

                if (!hostName.equals(ip)) {
                    return hostName;
                }
            }

        } catch (Exception ignored) {
        }

        return "";
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}