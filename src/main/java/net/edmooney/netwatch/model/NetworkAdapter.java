package net.edmooney.netwatch.model;

/**
 * Represents one network adapter.
 */
public class NetworkAdapter {

    private final String name;
    private final String displayName;
    private final boolean up;
    private final boolean loopback;
    private final String ipv4Address;

    public NetworkAdapter(
            String name,
            String displayName,
            boolean up,
            boolean loopback,
            String ipv4Address
    ) {
        this.name = name;
        this.displayName = displayName;
        this.up = up;
        this.loopback = loopback;
        this.ipv4Address = ipv4Address;
    }

    public String getIpv4Address() {
        return ipv4Address;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isLoopback() {
        return loopback;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
