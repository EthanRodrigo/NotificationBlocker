package com.example.notificationblocker;


import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import androidx.annotation.RequiresApi;

import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.AppDB.AppsDB;
import com.example.notificationblocker.NotificationDB.Notific;
import com.example.notificationblocker.NotificationDB.NotificDB;

import java.util.ArrayList;
import java.util.List;

public class NotificationListener extends NotificationListenerService{
    Context context;

    private NotificDB notificationsDB;
    private AppsDB appsDB;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        notificationsDB = NotificDB.getInstance(context);
        appsDB = AppsDB.getInstance(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        List<String> appNameList = appsDB.dbDAO().getAllAppNames();
        if(appNameList.contains(getApplicationName(sbn.getPackageName()))){  // if the blocked list has the app name of the notification
            cancelNotification(sbn.getKey());
        }
        if(MainActivity.BLOCK_ALL){
            cancelAllNotifications();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        // Just put the notification into the DB

        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        Notific notific = new Notific(sbn.getId(),
                notification.getChannelId(),
                "" + extras.get(Notification.EXTRA_TITLE),
                "" + extras.get(Notification.EXTRA_TEXT),
                getApplicationName(sbn.getPackageName()),
                BitmapUtility.getBytes(notification.getSmallIcon().loadDrawable(context)));
        notificationsDB.dbDAO().insertANotific(notific);
    };

    /***
     * getApplicationName: get the application name of the notification belongs to
     * @param pack: the package name; a StatusBarNotification.getPackageName()
     * @return
     */
    private String getApplicationName(String pack){
        final PackageManager pm = context.getPackageManager();
        ApplicationInfo appInfo;
        try{
            appInfo = pm.getApplicationInfo(pack, 0);  // getting the application info
        } catch(PackageManager.NameNotFoundException e){
            appInfo = null;
        }
        return (String) (appInfo != null? pm.getApplicationLabel(appInfo) : "(unknown)");
    }
}
