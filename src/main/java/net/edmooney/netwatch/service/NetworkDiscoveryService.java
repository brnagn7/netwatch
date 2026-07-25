package net.edmooney.netwatch.service;
import net.edmooney.netwatch.model.NetworkAdapter;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Discovers the network adapters available on the local machine.
 *
 * @return a list of discovered network adapters
 */
public class NetworkDiscoveryService {

    public List<NetworkAdapter> findAdapters() {

        List<NetworkAdapter> adapters = new ArrayList<>();

        try {

            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {

                NetworkInterface networkInterface = interfaces.nextElement();

                if (shouldInclude(networkInterface)) {

                    NetworkAdapter adapter = new NetworkAdapter(
                            networkInterface.getName(),
                            networkInterface.getDisplayName(),
                            networkInterface.isUp(),
                            networkInterface.isLoopback()
                    );

                    adapters.add(adapter);
                }
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

        return adapters;
    }

    private boolean shouldInclude(NetworkInterface networkInterface) {

        return true;
    }
}