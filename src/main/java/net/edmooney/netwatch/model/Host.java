package net.edmooney.netwatch.model;

public class Host {

    private final String ipAddress;
    private final String hostName;
    private final boolean online;

    public Host(String ipAddress, String hostName, boolean online) {
        this.ipAddress = ipAddress;
        this.hostName = hostName;
        this.online = online;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public boolean isOnline() {
        return online;
    }
}