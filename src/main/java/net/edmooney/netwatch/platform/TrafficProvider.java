package net.edmooney.netwatch.platform;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;

public interface TrafficProvider {

    TrafficSnapshot collectSnapshot(NetworkAdapter adapter);

}