package com.example.notificationlistener;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

public class NotificationService extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        String pkgName = statusBarNotification.getPackageName();
        StatusBarNotification[] activeNotifications = this.getActiveNotifications();

        Intent intent = new  Intent("com.example.notificationlistener");
        intent.putExtra("Package Name", pkgName);
        intent.putExtra("count", activeNotifications.length);
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        String pkgName = statusBarNotification.getPackageName();

        StatusBarNotification[] activeNotifications = this.getActiveNotifications();

        if (activeNotifications != null && activeNotifications.length > 0) {
            for (int i = 0; i < activeNotifications.length; i++) {

                Intent intent = new Intent("com.example.notificationlistener");
                intent.putExtra("Package Name", pkgName);
                sendBroadcast(intent);
                break;

            }
        }
    }

}
