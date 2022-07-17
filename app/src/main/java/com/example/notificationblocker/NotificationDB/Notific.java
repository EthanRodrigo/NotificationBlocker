package com.example.notificationblocker.NotificationDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "Notifications")
public class Notific{
    @PrimaryKey
    private int id;
    private String channelID, contentTitle, contentText, appName;
    private byte[] smallIcon;

    public Notific(int id, String channelID, String contentTitle, String contentText, String appName, byte[] smallIcon) {
        this.id = id;
        this.channelID = channelID;
        this.contentTitle = contentTitle;
        this.contentText = contentText;
        this.appName = appName;
        this.smallIcon = smallIcon;
    }

    public int getId() {
        return id;
    }

    public String getChannelID() {
        return channelID;
    }

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public String getAppName() {
        return appName;
    }

    public byte[] getSmallIcon() {
        return smallIcon;
    }
}
