package org.windhand.notification;

import com.github.plushaze.traynotification.notification.Notification;
import com.github.plushaze.traynotification.notification.Notifications;
import com.github.plushaze.traynotification.notification.TrayNotification;
import javafx.util.Duration;

public class NotificationClass {
    private static final String TITLE = "WindHand";

    public static void createNotification(String text) {
        Notification notification = Notifications.SUCCESS;

        TrayNotification tray = new TrayNotification();
        tray.setTitle(TITLE);
        tray.setMessage(text);
        tray.setNotification(notification);
        tray.showAndDismiss(Duration.seconds(2));
    }
}
