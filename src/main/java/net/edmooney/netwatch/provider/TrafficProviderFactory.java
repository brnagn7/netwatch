package net.edmooney.netwatch.provider;

public final class TrafficProviderFactory {

    private TrafficProviderFactory() {
    }

    public static TrafficProvider create() {

        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return new WindowsTrafficProvider();
        }

        if (os.contains("linux")) {
            return new LinuxTrafficProvider();
        }

        if (os.contains("mac")) {
            return new MacTrafficProvider();
        }

        return new DefaultTrafficProvider();
    }
}