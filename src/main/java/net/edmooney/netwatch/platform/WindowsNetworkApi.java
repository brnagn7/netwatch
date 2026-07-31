package net.edmooney.netwatch.platform;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface WindowsNetworkApi extends Library {

    WindowsNetworkApi INSTANCE =
            Native.load("Iphlpapi", WindowsNetworkApi.class);

    int GetNumberOfInterfaces(int[] numberOfInterfaces);

}