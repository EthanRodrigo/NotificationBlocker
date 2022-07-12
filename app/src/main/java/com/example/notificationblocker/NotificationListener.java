package com.example.notificationblocker;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import com.example.notificationblocker.AppDB.AppsDB;

import java.util.List;

public class NotificationListener extends NotificationListenerService{
    Context context;

//    private NotificationsDB notificationsDB;
    private AppsDB appsDB;
    private List<String> appNameList;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
//        notificationsDB = NotificationsDB.getInstance(context);
        appsDB = AppsDB.getInstance(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        appNameList = appsDB.dbDAO().getAllAppNames();
        if(appNameList.contains(getApplicationName(sbn.getPackageName()))){  // if the blocked list has the app name of the notification
            cancelNotification(sbn.getKey());
        }
        if(MainActivity.BLOCK_ALL){
            cancelAllNotifications();
        }
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
//        super.onNotificationRemoved(sbn);
//
//        Bundle extras = sbn.getNotification().extras;
//
//        if(appNameList.contains(sbn.getPackageName())){
//            Notification notification = new Notification(sbn.getId(), sbn.getPackageName(),
//                    extras.get(android.app.Notification.EXTRA_TITLE).toString(),
//                    extras.get(android.app.Notification.EXTRA_TEXT).toString());
//            notificationsDB.dbDAO().insertANotification(notification);
//        }
    }

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
        final String appName = (String) (appInfo != null? pm.getApplicationLabel(appInfo) : "(unknown)");  // if the appInfo is not null get the label of the Application
        return appName;
    }
}
