package com.hjw.a_wxchat_listener.manager;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class BusManager {
    private static Bus bus;

    public static Bus getInstance() {
        if (bus == null) {
            synchronized (BusManager.class) {
                bus = new Bus(ThreadEnforcer.ANY);
            }
        }
        return bus;
    }
}
