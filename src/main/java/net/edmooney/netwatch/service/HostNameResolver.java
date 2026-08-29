package net.edmooney.netwatch.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class HostNameResolver {

    private static final int THREADS = 20;

    private static final Map<String, String> cache =
            new ConcurrentHashMap<>();

    public Map<String, String> resolveAll(
            List<String> ipAddresses
    ) {

        Map<String, String> results =
                new ConcurrentHashMap<>();

        ExecutorService executor =
                Executors.newFixedThreadPool(
                        THREADS
                );

        List<Callable<Void>> tasks =
                ipAddresses.stream()
                        .map(ip -> (Callable<Void>) () -> {

                            String hostName;

                            if (cache.containsKey(ip)) {

                                hostName =
                                        cache.get(ip);

                            } else {

                                hostName =
                                        resolveWithWindowsPing(ip);

                                cache.put(
                                        ip,
                                        hostName
                                );
                            }

                            results.put(
                                    ip,
                                    hostName
                            );

                            return null;
                        })
                        .toList();

        try {

            executor.invokeAll(
                    tasks,
                    6,
                    TimeUnit.SECONDS
            );

        } catch (InterruptedException e) {

            Thread.currentThread()
                    .interrupt();

        } finally {

            executor.shutdownNow();
        }

        return new HashMap<>(results);
    }

    private String resolveWithWindowsPing(
            String ip
    ) {

        try {

            Process process =
                    new ProcessBuilder(
                            "ping",
                            "-a",
                            "-n",
                            "1",
                            "-w",
                            "200",
                            ip
                    )
                            .redirectErrorStream(true)
                            .start();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line;

            while ((line =
                    reader.readLine()) != null) {

                line = line.trim();

                if (!line.startsWith(
                        "Pinging "
                )) {
                    continue;
                }

                int start =
                        "Pinging ".length();

                int bracket =
                        line.indexOf(" [");

                if (bracket <= start) {
                    continue;
                }

                String hostName =
                        line.substring(
                                start,
                                bracket
                        ).trim();

                if (!hostName.equals(ip)) {
                    return hostName;
                }
            }

        } catch (Exception ignored) {
        }

        return "";
    }
}