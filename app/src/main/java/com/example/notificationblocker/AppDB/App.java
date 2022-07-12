package com.example.notificationblocker.AppDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="BlockedApps")
public class App{
    @PrimaryKey @NonNull
    private String appName;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)  // gonna store a BLOB
    private byte[] appIcon;

    private boolean isSelected;

    public App(@NonNull String appName, byte[] appIcon, boolean isSelected) {
        this.appName = appName;
        this.appIcon = appIcon;
        this.isSelected = isSelected;
    }

    @NonNull
    public String getAppName() {
        return appName;
    }

    public byte[] getAppIcon() {
        return appIcon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}