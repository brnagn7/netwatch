package net.edmooney.netwatch.service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SubnetProbeService {

    private static final int PORT = 9;
    private static final int THREADS = 80;

    public void probe(String localIp) {

        String subnet = getSubnet(localIp);

        if (subnet.isBlank()) {
            return;
        }

        ExecutorService executor =
                Executors.newFixedThreadPool(THREADS);

        for (int i = 1; i <= 254; i++) {

            String ip = subnet + i;

            if (ip.equals(localIp)) {
                continue;
            }

            executor.submit(() -> sendProbe(ip));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(
                    5,
                    TimeUnit.SECONDS
            );

            // Give Windows a moment to finish
            // updating the ARP cache.
            Thread.sleep(300);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sendProbe(String ip) {

        try (DatagramSocket socket =
                     new DatagramSocket()) {

            byte[] data = {0};

            InetAddress address =
                    InetAddress.getByName(ip);

            DatagramPacket packet =
                    new DatagramPacket(
                            data,
                            data.length,
                            address,
                            PORT
                    );

            socket.send(packet);

        } catch (Exception ignored) {
        }
    }

    private String getSubnet(String localIp) {

        if (localIp == null ||
                localIp.isBlank()) {
            return "";
        }

        int lastDot =
                localIp.lastIndexOf('.');

        if (lastDot == -1) {
            return "";
        }

        return localIp.substring(
                0,
                lastDot + 1
        );
    }
}