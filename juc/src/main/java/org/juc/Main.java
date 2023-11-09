package org.juc;

import java.util.Objects;


public class Main {
    public static void main(String[] args) {
        testJUC();
    }

    static String message = "C";
    public static void testJUC() {
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (!Objects.equals(message, "C"))  {}
                System.out.println("A");
                message = "A";
            }

        });
        Thread tb = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (!Objects.equals(message, "A")) { }
                System.out.println("B");
                message = "B";
            }
        });
        Thread tc = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                while (!Objects.equals(message, "B")) {
                }
                System.out.println("C");
                message = "C";
            }
        });
        ta.start();
        tb.start();
        tc.start();
    }
}
