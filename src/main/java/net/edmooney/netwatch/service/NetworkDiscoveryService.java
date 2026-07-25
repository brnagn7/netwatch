package net.edmooney.netwatch.service;
import net.edmooney.netwatch.model.NetworkAdapter;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Responsible for discovering network resources.
 */
public class NetworkDiscoveryService {

    /**
     * findAdapters()
     * │
     * ├── create empty list
     * ├── find adapter #1
     * ├── add it to the list
     * ├── find adapter #2
     * ├── add it to the list
     * └── return the completed list
     * @return
     */
    public List<NetworkAdapter> findAdapters() {

        List<NetworkAdapter> adapters = new ArrayList<>();

        try {

            Enumeration<NetworkInterface> interfaces =
                    NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {

                NetworkInterface networkInterface = interfaces.nextElement();

            }

        } catch (SocketException e) {
            e.printStackTrace();
        }

        return adapters;
    }
}