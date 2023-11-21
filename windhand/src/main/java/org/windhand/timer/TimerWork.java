package org.windhand.timer;

import javafx.application.Platform;
import org.windhand.notification.NotificationClass;

import java.util.Random;
import java.util.TimerTask;

public class TimerWork extends TimerTask {

    private static final Random generator = new Random();


    /**
     * переписати логіку мого таймера
     * */
    public void run() {
        while (true) {
            Tips[] tips = Tips.values();

            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();

            while (!((end - start) >= 3000)) {

            }
            String wasteTime = String.valueOf(end - start);

            Platform.runLater(() -> {
                NotificationClass.createNotification(tips[generator.nextInt(tips.length)].getTip() +
                        " you worked for" + wasteTime + "seconds");
            });
        }
    }
}
