package net.edmooney.netwatch.provider;

import net.edmooney.netwatch.model.NetworkAdapter;
import net.edmooney.netwatch.model.TrafficSnapshot;

public interface TrafficProvider {

    TrafficSnapshot collectSnapshot(NetworkAdapter adapter);

}