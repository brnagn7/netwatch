package net.edmooney.netwatch.platform;

public final class TrafficProviderFactory {

    private TrafficProviderFactory() {
    }

    private static Platform getPlatform() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return Platform.WINDOWS;
        }

        if (os.contains("linux")) {
            return Platform.LINUX;
        }

        if (os.contains("mac")) {
            return Platform.MAC;
        }

        return Platform.UNKNOWN;
    }

    public static TrafficProvider create() {

        return switch (getPlatform()) {

            case WINDOWS -> new WindowsTrafficProvider();
            case LINUX -> new LinuxTrafficProvider();
            case MAC -> new MacTrafficProvider();

            default -> new DefaultTrafficProvider();
        };
    }

    public static Platform getCurrentPlatform() {
        return getPlatform();
    }
}