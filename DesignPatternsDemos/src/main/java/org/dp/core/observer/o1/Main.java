package org.dp.core.observer.o1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}

class Child {
    private boolean cry;
    private List<Observer> observerList = new ArrayList<>();
    {
        observerList.add(new Mom());
        observerList.add(new Dad());
    }
    public Child(boolean cry) {
        this.cry = cry;
    }

    public void wakeup() {
        cry = true;
        WakeupEvent event = new WakeupEvent(System.currentTimeMillis(), "bedroom");
        for (Observer o: observerList) {
            o.actionOnWakeup(event);
        }
    }
}

class WakeupEvent {
    long timestamp;
    String loc;

    public WakeupEvent(long timestamp, String loc) {
        this.timestamp = timestamp;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "WakeupEvent{" +
                "timestamp=" + timestamp +
                ", loc='" + loc + '\'' +
                '}';
    }
}

abstract class Observer {
    abstract void actionOnWakeup(WakeupEvent event);
}

class Mom extends Observer{

    @Override
    void actionOnWakeup(WakeupEvent event) {

        System.out.println("mom action " + event.toString());
    }
}

class Dad extends Observer {

    @Override
    void actionOnWakeup(WakeupEvent event) {
        System.out.println("dad action..." + event.toString());
    }
}