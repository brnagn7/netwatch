package net.edmooney.netwatch.service;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HostNameResolver {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(10);

    public String resolve(String ip) {

        try {

            Future<String> future = executor.submit(() ->
                    InetAddress
                            .getByName(ip)
                            .getCanonicalHostName()
            );

            String hostname =
                    future.get(1, TimeUnit.SECONDS);

            if (hostname.equals(ip)) {
                return "";
            }

            return hostname;

        } catch (Exception ignored) {

            return "";

        }
    }

    public void shutdown() {
        executor.shutdownNow();
    }
}