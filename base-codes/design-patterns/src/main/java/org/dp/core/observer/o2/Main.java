package org.dp.core.observer.o2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Child c = new Child(false);
        c.addObserver(new Mom());
        c.addObserver(new Dad());
        c.wakeup();

    }
}

class Child {
    private boolean cry;
    private List<Observer> observerList = new ArrayList<>();
    public Child(boolean cry) {
        this.cry = cry;
    }

    public void addObserver(Observer o) {
        observerList.add(o);
    }

    public void wakeup() {
        cry = true;
        WakeupEvent event = new WakeupEvent(System.currentTimeMillis(), "bedroom", this);
        for (Observer o: observerList) {
            o.actionOnWakeup(event);
        }
    }
}

class WakeupEvent {
    long timestamp;
    String loc;

    Child source;

    public WakeupEvent(long timestamp, String loc, Child source) {
        this.timestamp = timestamp;
        this.loc = loc;
        this.source = source;
    }

    public Child getSource() {
        return source;
    }

    @Override
    public String toString() {
        return "WakeupEvent{" +
                "timestamp=" + timestamp +
                ", loc='" + loc + '\'' +
                '}';
    }
}

abstract interface Observer {
    void actionOnWakeup(WakeupEvent event);
}

class Mom implements Observer {

    @Override
    public void actionOnWakeup(WakeupEvent event) {

        System.out.println("mom action " + event.toString());
    }
}

class Dad implements Observer {

    @Override
    public void actionOnWakeup(WakeupEvent event) {
        System.out.println("dad action..." + event.toString());
    }
}