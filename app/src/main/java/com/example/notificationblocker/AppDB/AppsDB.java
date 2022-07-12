package com.example.notificationblocker.AppDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = App.class, version = 1)
public abstract class AppsDB extends RoomDatabase{
    private static final String dbName = "Apps";
    private static AppsDB INSTANCE;

    /***
     * getInstance: Checking whether there's a db instance or not
     * @param context
     * @return the instance if it exists or else a new one
     */
    public static AppsDB getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppsDB.class, dbName)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
    public abstract DBDAO dbDAO();
}
