package com.example.notificationlistener;

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class NotificationService extends NotificationListenerService {

    private static final String TAG = "notification";

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        String pkgName = statusBarNotification.getPackageName();

        Intent intent = new  Intent("com.example.notificationlistener");
        intent.putExtra("Package Name", pkgName);
        intent.putExtra("count", statusBarNotification.getNotification().number);
        sendBroadcast(intent);

        StatusBarNotification[] activeNotifications = this.getActiveNotifications();
        if (activeNotifications != null && activeNotifications.length > 0) {
            for (StatusBarNotification active : activeNotifications) {
                switch (active.getPackageName()) {
                    case "com.google.android.apps.messaging":
                        Log.i(TAG, "Message --");
                        break;
                    case "com.android.dialer":
                        Log.i(TAG, "Dialer --");
                        break;
                    case "com.google.android.apps.calhistory":
                        Log.i(TAG, "CallHistory--");
                        break;
                    case "com.google.android.apps.other":
                        Log.i(TAG, "Message--");
                        break;
                }
                if (active.getPackageName().contentEquals(pkgName)) {
                    Log.i(TAG, "Pkg : " + pkgName);
                }
                Log.i(TAG, "Key     : " + active.getKey());
                Log.i(TAG, "Pkg     : " + active.getPackageName());
                Log.i(TAG, "Tag     : " + active.getTag());
                Log.i(TAG, "Content : " + active.describeContents());
                Log.i(TAG, "------------- EOTags");
            }
        }
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
