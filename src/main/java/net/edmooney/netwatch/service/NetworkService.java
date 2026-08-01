package net.edmooney.netwatch.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class NetworkService {

    public String getLocalSubnet() {

        try {

            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements()) {

                NetworkInterface networkInterface =
                        interfaces.nextElement();

                if (!networkInterface.isUp()
                        || networkInterface.isLoopback()) {
                    continue;
                }

                var addresses = networkInterface.getInetAddresses();

                while (addresses.hasMoreElements()) {

                    InetAddress address = addresses.nextElement();

                    if (address.getHostAddress().contains(".")) {

                        String ip = address.getHostAddress();

                        return ip.substring(0, ip.lastIndexOf('.') + 1);
                    }
                }
            }

        } catch (Exception ignored) {
        }

        return "192.168.1.";
    }
}