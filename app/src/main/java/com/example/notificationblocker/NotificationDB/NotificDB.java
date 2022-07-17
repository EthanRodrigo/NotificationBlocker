package com.example.notificationblocker.NotificationDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notificationblocker.AppDB.AppsDB;
import com.example.notificationblocker.AppDB.DBDAO;

@Database(entities = Notific.class, version = 1)
public abstract class NotificDB extends RoomDatabase{
    private static final String dbName = "Notifications";
    private static NotificDB INSTANCE;

    /***
     * getInstance: Checking whether there's a db instance or not
     * @param context
     * @return the instance if it exists or else a new one
     */
    public static NotificDB getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NotificDB.class, dbName)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public abstract NotificDBDAO dbDAO();
}
