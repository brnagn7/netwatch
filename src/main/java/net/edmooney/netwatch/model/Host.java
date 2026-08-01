package net.edmooney.netwatch.model;

public class Host {

    private final String ipAddress;
    private final String hostName;
    private final String macAddress;
    private final boolean online;

    public Host(String ipAddress,
                String hostName,
                String macAddress,
                boolean online) {

        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.macAddress = macAddress;
        this.online = online;
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