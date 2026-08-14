package net.edmooney.netwatch.model;

public class Host {

    private final String ipAddress;
    private final String hostName;
    private final String macAddress;
    private final boolean online;
    private final String vendor;

    public Host(
            String ipAddress,
            String hostName,
            String macAddress,
            String vendor,
            boolean online
    ) {
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.macAddress = macAddress;
        this.vendor = vendor;
        this.online = online;
    }

    public String getVendor() {
        return vendor;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public boolean isOnline() {
        return online;
    }
}