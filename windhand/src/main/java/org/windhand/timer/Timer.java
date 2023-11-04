package org.windhand.timer;

import javafx.application.Platform;
import org.windhand.notification.NotificationClass;

import java.util.Random;

public class Timer extends Thread {

    private static final Random generator = new Random();

    @Override
    public void run() {
        while (true) {
            Tips[] tips = Tips.values();

            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();

            while (!((end - start) >= 3000)) {
                try {
                    sleep(3000);
                    end = System.currentTimeMillis();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Platform.runLater(() -> {
                NotificationClass.createNotification(tips[generator.nextInt(tips.length)].getTip() +
                        " you worked for 3 seconds");
            });
        }
    }
}
